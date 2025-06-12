package com.jfbrother.baseserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.LoginResultEnum;
import com.jfbrother.baseserver.enums.LoginTypeEnum;
import com.jfbrother.baseserver.jwt.JwtUser;
import com.jfbrother.baseserver.model.JwtModel;
import com.jfbrother.baseserver.model.LoginUserModel;
import com.jfbrother.baseserver.model.SysUsersModel;
import com.jfbrother.baseserver.model.UserInfoInRedisModel;
import com.jfbrother.baseserver.model.param.SysLogLoginModelParam;
import com.jfbrother.baseserver.service.*;
import com.jfbrother.baseserver.service.impl.SysUsersServiceImpl;
import com.jfbrother.baseserver.utils.*;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormReleaseModelExtend;
import com.jfbrother.customForm.model.search.CustomFormReleaseModelSearch;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.customForm.service.CustomFormReleaseService;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuParentsBindHistoryModelParam;
import com.jfbrother.digitalPortraitSnap.service.DigitalPortraitSnapService;
import com.querydsl.core.Tuple;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"系统接口-1.0.0-20181122"})
@ApiSort(-10)
@RestController("system")
@RequestMapping("/api/{version}/system")
public class LoginController {
    @Autowired
    private SysUsersService sysUsersService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysRolesService sysRolesService;
    @Autowired
    private SysLogLoginService sysLogLoginService;
    @Value("${system.cas.token.expire-time}")
    private Long expireTime;
    @Autowired
    private LoginService loginService;
    @Autowired
    private CustomFormReleaseService customFormReleaseService;
    @Autowired
    private CustomFormFillService customFormFillService;
    @Autowired
    private WeChatUtils weChatUtils;
    @Autowired
    private DigitalPortraitSnapService digitalPortraitSnapService;

    /**
     * 该类仅为了让swagger捕获到，真正执行登录的在JWTAuthenticationFilter类
     *
     * @param loginUser
     */
    @ApiOperation(value = "登录", notes = "登录接口", position = 1)
    @ApiVersion(1)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(@RequestBody LoginUserModel loginUser) {
        loginService.login(loginUser);
    }

    @ApiOperation(value = "登出", notes = "登出接口", position = 2)
    @ApiVersion(1)
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result logout(HttpServletRequest request) {
        //记录登陆日志
        SysLogLoginModelParam model = new SysLogLoginModelParam();
        Boolean disableToken = (Boolean) request.getAttribute("disableToken");

        Object disableUsername = request.getAttribute("disableUsername");
        Object disableTokenText = request.getAttribute("disableTokenText");
        Object disableUserId = request.getAttribute("disableUserId");

        model.setIp(IpUtils.getIpAddress(request));
        //登陆类型-1为登出操作
        model.setLoginTypeEnum(LoginTypeEnum.LOGOUT);
        model.setUsername(disableUsername != null ? disableUsername.toString() : null);
        model.setToken(disableTokenText != null ? disableTokenText.toString() : null);
        model.setUserId(disableUserId != null ? disableUserId.toString() : null);

        if (disableToken) {
            //表示成功
            model.setLoginResultEnum(LoginResultEnum.LOGOUT_SUCCESS);
            sysLogLoginService.post(model);
            return ResultGenerator.genSuccessResult().setMessage("Logout success.");
        } else {
            //表示失败
            model.setLoginResultEnum(LoginResultEnum.LOGOUT_ERROR);
            sysLogLoginService.post(model);
            return ResultGenerator.genFailResult("Logout fail.");
        }
    }

    @ApiOperation(value = "当前登陆人信息获取", notes = "当前登陆人信息获取接口", position = 3)
    @ApiVersion(1)
    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public Result<JwtModel> userInfo(HttpServletRequest request) {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        JwtModel jwtModel = new JwtModel();

        String userId = JwtTokenUtils.getId(token);
        jwtModel.setId(userId);
        jwtModel.setName(JwtTokenUtils.getName(token));

        jwtModel.setMenus(sysRolesService.getMenusByUserId(jwtModel.getId()));

        UserInfoInRedisModel permitsFromRedis = sysUsersService.getPermitsFromRedis(userId);
        jwtModel.setName(permitsFromRedis.getName());
        jwtModel.setCompanyId(permitsFromRedis.getCompanyId());
        jwtModel.setUsername(sysUsersService.get(userId).getUsername());
        String permits = permitsFromRedis.getPermits();
        if (!StringUtils.isEmpty(permits)) {
            List<String> listPermit = new ArrayList<>();
            for (String p : permits.split(",")) {
                if (!Objects.equals(p, "null")) {
                    listPermit.add(p);
                }
            }
            jwtModel.setRoles(listPermit);
        }

        return ResultGenerator.genSuccessResult(jwtModel);
    }

    @ApiOperation(value = "cas登录接口", notes = "cas登录接口", position = 4)
    @ApiVersion(1)
    @GetMapping(value = "cas")
    public void cas(String redirect, HttpServletRequest request, HttpServletResponse response) {
        String loginName="";
        Assertion assertion = AssertionHolder.getAssertion();
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            if (principal != null) {
                loginName = principal.getName();
            }
        }
        log.info("用户名_loginName【{}】",loginName);
        log.info("cas_redirect【{}】",redirect);
        log.info("cas_requestUri【{}】",request.getRequestURI());
        
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            //获得认证中心传过来的其它用户属性。一般为空
            Map<String, Object> attributemap = principal.getAttributes();
            //获得认证中心传过来的认证名，一般为用户登录名
            String loginName_ = principal.getName();
            attributemap.put("loginName", loginName_);

            attributemap.put("remoteUser", request.getRemoteUser());
            if (loginName != null && loginName.trim().length() > 0) {
                log.info("登录成功，欢迎您：" + loginName);
                attributemap.put("loginName_", loginName);
            } else {
                //System.out.println("登录失败");
                log.info("登录失败！");
            }

            SysUsersServiceImpl sysUsersServiceImpl = (SysUsersServiceImpl) sysUsersService;
            JwtUser jwtUser;
            try {
                jwtUser = sysUsersServiceImpl.loadUserByUsername(loginName_);
            } catch (ServiceException e) {
                log.debug("用户不存在，需要创建");
                SysUsersModel sysUsersModel = new SysUsersModel();
                sysUsersModel.setUsername(loginName_);
                sysUsersModel.setName("cas自动生成");
                sysUsersService.post(sysUsersModel);

                jwtUser = sysUsersServiceImpl.loadUserByUsername(loginName_);
            }

            //进行token的生成
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtTokenUtils.ID, jwtUser.getId());
            claims.put(JwtTokenUtils.NAME, jwtUser.getName());
            String token = JwtTokenUtils.createToken(jwtUser.getUsername(), claims, false);

            //存入redis，返回脱敏的key值（以后使用key来获取该token）
            String key = UUID.randomUUID().toString();
            redisService.set(key, token, expireTime);

            UrlUtils urlUtils = new UrlUtils(redirect);
            urlUtils.removeParams("key");
            urlUtils.addParam("key", key);
            String url = response.encodeRedirectURL(urlUtils.build());
            try {
                //记录登陆日志
                SysLogLoginModelParam model = new SysLogLoginModelParam();
                model.setIp(IpUtils.getIpAddress(request));
                //登陆类型2为cas方式登录
                model.setLoginTypeEnum(LoginTypeEnum.LOGIN_CAS);
                model.setUsername(jwtUser.getUsername());
                model.setUserId(jwtUser.getId());
                model.setToken(token);
                model.setLoginResultEnum(LoginResultEnum.LOGIN_SUCCESS);
                sysLogLoginService.post(model);

                response.sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "使用uuid获取token", notes = "使用uuid获取token接口", position = 5)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token is success."),
            @ApiResponse(code = 406, message = "The key has expired.")
    })
    @GetMapping(value = "cas/{key}/token")
    public Result casToken(@PathVariable String key, HttpServletResponse response) {
        String token = (String) redisService.get(key);
        redisService.remove(key);
        if (token != null && !"".equals(token)) {
            response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
            // 开放token
            response.setHeader("Access-Control-Expose-Headers", "token,Content-Disposition");
            ResultGenerator.genSuccessResult().setMessage("Token is success.");
        }
        return ResultGenerator.genFailResult(ResultCode.NOT_ACCEPTABLE, "The key has expired.");
    }

    private String decode(String str) throws Exception {
        if (str != null) {
            str = URLDecoder.decode(str, "UTF-8");
        }
        return str;
    }
    @ApiOperation(value = "校务服务登录并跳转接口", notes = "校务服务登录并跳转接口", position = 4)
    @ApiVersion(1)
    @GetMapping(value = "xwfwsso")
    public void xwfwsso(HttpServletRequest request, HttpServletResponse response) {
        String baseUrl="http://zdtyc.zjbti.net.cn/#/";
        String loginName="";
        Assertion assertion = AssertionHolder.getAssertion();
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            if (principal != null) {
                loginName = principal.getName();
            }
        }
        log.info("loginName【{}】",loginName);
        String requestSource = request.getParameter("request-source");
        String innerId = request.getParameter("inner-id");
        String targetUrl = request.getParameter("targetUrl");
        String domId = request.getParameter("domId");
        String fillId = request.getParameter("instance-code");
        log.info("xwfwsso_target【{}】",request.getParameter("target"));
        log.info("xwfwsso_request-source【{}】", requestSource);
        log.info("xwfwsso_inner-id【{}】", innerId);
        log.info("xwfwsso_targetUrl【{}】", targetUrl);
        log.info("xwfwsso_domId【{}】", domId);
        log.info("xwfwsso_instance-code【{}】", fillId);

        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            //获得认证中心传过来的认证名，一般为用户登录名
            String loginName_ = principal.getName();
            log.info("用户名_loginName【{}】",loginName_);
            SysUsersServiceImpl sysUsersServiceImpl = (SysUsersServiceImpl) sysUsersService;
            JwtUser jwtUser;
            try {
                jwtUser = sysUsersServiceImpl.loadUserByUsername(loginName_);
            } catch (ServiceException e) {
                log.debug("用户不存在，需要创建");
                SysUsersModel sysUsersModel = new SysUsersModel();
                sysUsersModel.setUsername(loginName_);
                sysUsersModel.setName("cas自动生成");
                sysUsersService.post(sysUsersModel);

                jwtUser = sysUsersServiceImpl.loadUserByUsername(loginName_);
            }

            //进行token的生成
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtTokenUtils.ID, jwtUser.getId());
            claims.put(JwtTokenUtils.NAME, jwtUser.getName());
            String token = JwtTokenUtils.createToken(jwtUser.getUsername(), claims, false);
            //存入redis，返回脱敏的key值（以后使用key来获取该token）
            String key = UUID.randomUUID().toString();
            redisService.set(key, token, expireTime);
            baseUrl = calcaulateRedirectUrl(baseUrl, requestSource, domId, fillId,innerId);
            log.info("最终的baseUrl{}",baseUrl);
            UrlUtils urlUtils = new UrlUtils(baseUrl);
            urlUtils.removeParams("key");
            urlUtils.addParam("key", key);
            String url = response.encodeRedirectURL(urlUtils.build());
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String calcaulateRedirectUrl(String baseUrl, String requestSource, String domId, String fillId,String innerId) {
        if(requestSource.equals("portal-item")){
            log.info("enter potal item------");
            //如果是通过校务服务点击我要办理进来的，则跳转到填报板块的具体,myFillList/edit/
            Pageable pageable = PageRequest.of(0, 1000, Sort.by("createTime") );
            List<CustomFormReleaseModelExtend> fillList = customFormReleaseService.getFill(new CustomFormReleaseModelSearch(), pageable)
                    .getResult().stream().filter(item->!StringUtils.isEmpty(item.getItemDomId())&&item.getItemDomId().equals(domId)).collect(Collectors.toList());
            if(fillList.size()>0){
                String releaseId = fillList.get(0).getId();
                if(!StringUtils.isEmpty(releaseId)){
                    log.info("relaeseid");
                    baseUrl=baseUrl.concat("myFillList/edit/").concat(releaseId);
                    log.info(baseUrl);
                }
            }else{
                baseUrl=baseUrl.concat("myFillList/index/");
            }
        }else if(requestSource.equals("portal_item_instance")){
            //customFormFill/view/  myFill/edit/{status} status=1已提交 status=0保存
            if(!StringUtils.isEmpty(fillId)){
                CustomFormFillModelExtend one = customFormFillService.getOneById(fillId);
                Integer statusNum = one.getStatusNum();
                baseUrl=baseUrl.concat("myFill/edit/").concat(fillId).concat("/"+statusNum);
            }

        }else if(requestSource.equals("portal_personal_todo_assignee")){
            //fillTodo/edit/:taskId?/:taskName?/:requestId流程id?   taskName=开始
            String[] split = innerId.split("_");
            if(split.length>2){
                String flowId = split[0];
                String taskId = split[1];
                String taskName = split[2];
                try {
                    baseUrl=baseUrl.concat("fillTodo/edit/").concat(taskId)
                            .concat("/").concat(URLEncoder.encode(taskName,"UTF-8"))
                            .concat("/").concat(flowId);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }else {
                baseUrl=baseUrl+"fillTodo/index/";
            }
        }else {
            baseUrl=baseUrl+"dashboard";
        }
        return baseUrl;
    }

    @ApiOperation(value = "微信绑定接口", notes = "微信绑定接口", position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "/wxBlind")
    public JSONObject wxBlind(@RequestBody WXBlindParam body,HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();

        String code = body.getCode();
        Object openId = weChatUtils.getOpenIDOrUnionId(code);
        if(StringUtils.isEmpty(openId)){
            jsonObject.put("code", "error");
            jsonObject.put("msg", "openId为空");
            return jsonObject;
        }

        GsStuParentsBindHistoryModelParam gsStuParentsBindHistoryModelParam = new GsStuParentsBindHistoryModelParam();
        String loginType = body.getLoginType();
        if(loginType.equals("1")){
            Map<String,Object> m = digitalPortraitSnapService.verifyParentIdentity(null,body.getParentPhone());
            if(m.get("code").equals("2")){
                Tuple tuple = (Tuple)m.get("data");
                gsStuParentsBindHistoryModelParam = CopyUtils.copyBean(tuple,GsStuParentsBindHistoryModelParam.class);
                gsStuParentsBindHistoryModelParam.setOpenId(openId.toString());
                gsStuParentsBindHistoryModelParam.setBindType("1");
                gsStuParentsBindHistoryModelParam.setDeleteFlag("0");
            }else if(m.get("code").equals("3")){
                jsonObject.put("code", "tourist");
                jsonObject.put("msg", "当前用户是游客");
                return jsonObject;
            }else if(m.get("code").equals("4")){
                jsonObject.put("code", "repeat");
                jsonObject.put("msg", "当前手机号已绑定");
                return jsonObject;
            }
        }else {
            gsStuParentsBindHistoryModelParam.setStuCode(body.getStuCode());
            gsStuParentsBindHistoryModelParam.setParentName(body.getParentName());
            gsStuParentsBindHistoryModelParam.setParentPhone(body.getParentPhone());
            gsStuParentsBindHistoryModelParam.setRelation(body.getRelation());
            gsStuParentsBindHistoryModelParam.setOpenId(openId.toString());
            gsStuParentsBindHistoryModelParam.setBindType("0");
            gsStuParentsBindHistoryModelParam.setDeleteFlag("0");
        }
        digitalPortraitSnapService.addStuParentsBindHistory(gsStuParentsBindHistoryModelParam);

        redisService.set(code,openId,5*60L);
        SysUsersModel usersModel = null;
        try {
            usersModel = sysUsersService.getByUserName(body.getParentPhone());
        } catch (Exception e) {
            //根据openId获取用户信息
            JSONObject userInfo = weChatUtils.getUserInfoByOpenId(openId.toString());
            Object finalOpenId = openId;
            usersModel = sysUsersService.post(new SysUsersModel() {{
                setName(body.getParentName());
                setUsername(body.getStuCode() + "_" + body.getParentPhone());
                setOpenId(finalOpenId.toString());
            }});
            e.printStackTrace();
        }
        //进行token的生成
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtTokenUtils.ID, usersModel.getId());
        claims.put(JwtTokenUtils.NAME, usersModel.getName());

        String token = JwtTokenUtils.createToken(usersModel.getUsername(), claims, false);

        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
        // 开放token
        response.setHeader("Access-Control-Expose-Headers", "token,Content-Disposition");
        //记录登陆日志
        SysLogLoginModelParam model = new SysLogLoginModelParam();
        model.setIp(IpUtils.getIpAddress(request));
        //登陆类型2为cas方式登录
        model.setLoginTypeEnum(LoginTypeEnum.LOGIN_WX_H5);
        model.setUsername(usersModel.getUsername());
        model.setUserId(usersModel.getId());
        model.setToken(token);
        model.setLoginResultEnum(LoginResultEnum.LOGIN_SUCCESS);
        sysLogLoginService.post(model);

        jsonObject.put("code", "success");
        jsonObject.put("msg", "绑定成功");
        return jsonObject;
    }

    @Data
   static class WXBlindParam{
       private String code;
       private String stuCode;
       private String parentName;
       private String parentPhone;
       private String relation;
       //登录方式 0-我的绑定 1-首页登录
       private String loginType;
    }

    @ApiOperation(value = "微信openid直接登录", notes = "微信openid直接登录接口", position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "string")
    })
    @ApiVersion(1)
    @PostMapping(value = "/wxLogin/{code}")
    public JSONObject wxLogin(@PathVariable String code,HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        Object openId = redisService.get(code);
        //如果code过期了，则重新放置openid
        if(StringUtils.isEmpty(openId)){
            //code有效期为5分钟，且code只能使用一次
            openId = weChatUtils.getOpenIDOrUnionId(code);
            //if(StringUtils.isEmpty(openId)){
            //    return;
            //}
            //redisService.set(code,openId,5*60L);
        }
        Map<String,Object> m = digitalPortraitSnapService.verifyParentIdentity(openId.toString(),null);
        if(m.get("code").equals("1")){
            jsonObject.put("code", "unbind");
            jsonObject.put("msg", "家长未绑定学生信息");
            return jsonObject;
        }
        SysUsersModel usersModel = sysUsersService.getByOpenId(openId.toString());
        String token="";
        if(usersModel.getId()!=null){
            //进行token的生成
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtTokenUtils.ID, usersModel.getId());
            claims.put(JwtTokenUtils.NAME, usersModel.getName());

             token = JwtTokenUtils.createToken(usersModel.getUsername(), claims, false);

            response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
            // 开放token
            response.setHeader("Access-Control-Expose-Headers", "token,Content-Disposition");
        }
        //记录登陆日志
        SysLogLoginModelParam model = new SysLogLoginModelParam();
        model.setIp(IpUtils.getIpAddress(request));
        //登陆类型2为cas方式登录
        model.setLoginTypeEnum(LoginTypeEnum.LOGIN_WX_H5);
        model.setUsername(usersModel.getUsername());
        model.setUserId(usersModel.getId());
        model.setToken(token);
        model.setLoginResultEnum(LoginResultEnum.LOGIN_SUCCESS);
        sysLogLoginService.post(model);

        jsonObject.put("code", "success");
        jsonObject.put("msg", "免登成功");
        return jsonObject;
    }

}
