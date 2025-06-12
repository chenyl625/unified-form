package com.jfbrother.baseserver.service.impl;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.jfbrother.baseserver.configurer.properties.SystemConfigProperties;
import com.jfbrother.baseserver.configurer.properties.system.LoginProperties;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.LoginResultEnum;
import com.jfbrother.baseserver.enums.LoginTypeEnum;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.jpa.SysUsers;
import com.jfbrother.baseserver.model.LoginUserModel;
import com.jfbrother.baseserver.model.param.SysLogLoginModelParam;
import com.jfbrother.baseserver.service.LoginService;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.SysLogLoginService;
import com.jfbrother.baseserver.utils.IpUtils;
import com.jfbrother.baseserver.utils.JwtTokenUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyl
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private SystemConfigProperties systemConfigProperties;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogLoginService sysLogLoginService;

    /**
     * 错误登录次数的redis前缀
     */
    private static final String ERROR_LOGIN_COUNT_PRE = "ErrorLoginCOUNT_";

    QSysUsers qSysUsers = QSysUsers.sysUsers;

    @Override
    public void login(LoginUserModel loginUserModel) {
        String username = loginUserModel.getUsername();
        String password = loginUserModel.getPassword();
        LoginProperties loginProperties = getLoginProperties();

        defaultLogin(username, password, LoginTypeEnum.LOGIN_WEB);


    }

    /**
     * 默认登录
     *
     * @param username 支持用户名/手机号
     * @param password 密码
     */
    private void defaultLogin(String username, String password, LoginTypeEnum loginTypeEnum) {
        //查询用户
        SysUsers sysUsers = queryFactory.select(qSysUsers).from(qSysUsers)
                .where(qSysUsers.username.eq(username).or(qSysUsers.telephone.eq(username)))
                .fetchFirst();
        if (sysUsers == null) {
            createLoginLog(username, loginTypeEnum, LoginResultEnum.PASSWORD_ERROR);
            throw new ServiceException(ResultCode.LOGIN_FAILED, "用户名或者密码错误");
        }

        String key = ERROR_LOGIN_COUNT_PRE + username;
        if (bCryptPasswordEncoder.matches(password, sysUsers.getPassword())) {
            //登录成功
            //清空错误次数记录
            redisService.remove(key);
            createLoginLog(username, loginTypeEnum, LoginResultEnum.LOGIN_SUCCESS, sysUsers.getId(), login(sysUsers));
            return;
        }

        LoginProperties loginProperties = getLoginProperties();
        //如果登录错误，则将该用户名的登录错误次数+1
        Long incr = redisService.incr(key);
        //设置错误次数自动重置时间(单位使用小时)
        redisService.expire(key, loginProperties.getResetErrorTime(), TimeUnit.HOURS);

        //错误响应处理
        ServiceException serviceException = new ServiceException(ResultCode.LOGIN_FAILED, "用户名或者密码错误");
        //如果错误次数比一级错误大了，则需要在响应数据中添加，错误等级
        if (incr >= loginProperties.getErrLevelFirst()) {
            //错误等级为一级
            serviceException.setData(LoginProperties.ErrorLevelEnum.FIRST);
        }

        createLoginLog(username, loginTypeEnum, LoginResultEnum.LOGIN_SUCCESS, sysUsers.getId(), null);
        throw serviceException;
    }

    /**
     * 使用验证码进行登录
     *
     * @param username
     * @param password
     * @param verificationCode
     */
    private void verificationLogin(String username, String password, String verificationCode, LoginTypeEnum loginTypeEnum) {
        if (verificationCode == null || "".equals(verificationCode)) {
            //如果验证码没有设置
            throw new ServiceException(ResultCode.LOGIN_FAILED, "必须设置验证码", LoginProperties.ErrorLevelEnum.FIRST);
        }

        LoginProperties loginProperties = getLoginProperties();

        //根据验证码类型，分别进行不同方式的校验
        if (loginProperties.getValidType() == LoginProperties.ValidTypeEnum.CAPTCHA) {
            //验证码校验
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(verificationCode);
            ResponseModel res = captchaService.verification(captchaVO);
            if (res.isSuccess() == false) {
                //验证码校验失败，返回信息告诉前端
                //repCode  0000  无异常，代表成功
                //repCode  9999  服务器内部异常
                //repCode  0011  参数不能为空
                //repCode  6110  验证码已失效，请重新获取
                //repCode  6111  验证失败
                //repCode  6112  获取验证码失败,请联系管理员
                createLoginLog(username, loginTypeEnum, LoginResultEnum.VERIFICATION_ERROR);
                throw new ServiceException(ResultCode.LOGIN_FAILED, "验证码错误");
            }
        } else if (loginProperties.getValidType() == LoginProperties.ValidTypeEnum.GRAPHIC) {
            createLoginLog(username, loginTypeEnum, LoginResultEnum.VERIFICATION_ERROR);
            throw new ServiceException(ResultCode.LOGIN_FAILED, "暂不支持图形验证码");
        } else {
            createLoginLog(username, loginTypeEnum, LoginResultEnum.VERIFICATION_ERROR);
            throw new ServiceException(ResultCode.LOGIN_FAILED, "缺少验证码校验方案");
        }

        //验证码校验成功后，继续使用默认的用户名密码登录方案
        defaultLogin(username, password, loginTypeEnum);
    }

    /**
     * 记录登录日志
     *
     * @param username
     * @param loginTypeEnum
     */
    private void createLoginLog(String username, LoginTypeEnum loginTypeEnum, LoginResultEnum loginResultEnum) {
        createLoginLog(username, loginTypeEnum, loginResultEnum, null, null);
    }

    /**
     * 记录错误的登录日志
     *
     * @param username
     * @param loginTypeEnum
     * @param loginResultEnum
     * @param userId
     * @param token
     */
    private void createLoginLog(String username, LoginTypeEnum loginTypeEnum, LoginResultEnum loginResultEnum, String userId, String token) {
        //记录登录日志
        SysLogLoginModelParam model = new SysLogLoginModelParam();
        model.setIp(IpUtils.getIpAddress(request));
        model.setUsername(username);
        model.setLoginTypeEnum(loginTypeEnum);
        model.setLoginResultEnum(loginResultEnum);
        if (!StringUtils.isEmpty(userId)) {
            model.setUserId(userId);
        }
        if (!StringUtils.isEmpty(token)) {
            model.setToken(token);
        }
        sysLogLoginService.post(model);
    }

    /**
     * 使用用户对象进行登录并创建token，设置响应头
     *
     * @param sysUsers
     */
    private String login(SysUsers sysUsers) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        String token = createToken(sysUsers);
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
        // 开放token
        response.setHeader("Access-Control-Expose-Headers", "token,Content-Disposition");
        return token;
    }

    /**
     * 生成token
     *
     * @param sysUsers
     * @return
     */
    private String createToken(SysUsers sysUsers) {
        //进行token的生成
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtTokenUtils.ID, sysUsers.getId());
        claims.put(JwtTokenUtils.NAME, sysUsers.getName());

        return JwtTokenUtils.createToken(sysUsers.getUsername(), claims, false);
    }

    private LoginProperties getLoginProperties() {
        return systemConfigProperties.getLogin();
    }
}
