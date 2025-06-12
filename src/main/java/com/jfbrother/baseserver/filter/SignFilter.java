package com.jfbrother.baseserver.filter;

import com.jfbrother.baseserver.configurer.properties.SystemConfigProperties;
import com.jfbrother.baseserver.configurer.properties.system.SignFilterProperties;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.filter.ErrorUtils;
import com.jfbrother.baseserver.filter.HttpServletUtils;
import com.jfbrother.baseserver.filter.wrapper.ContentCachingRequestWrapper;
import com.jfbrother.baseserver.model.SysAppSecretModel;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.SysAppSecretService;
import com.jfbrother.baseserver.utils.JwtTokenUtils;
import com.jfbrother.baseserver.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 签名认证拦截器
 *
 * @author chenyl@jfbrother.com
 */
@Slf4j
@Component
public class SignFilter extends OncePerRequestFilter implements Ordered {
    @Autowired
    private SystemConfigProperties systemConfigProperties;
    @Autowired
    private SysAppSecretService secretService;
    @Autowired
    private RedisService redisService;

    /**
     * api前缀
     */
    private static final String NEED_TRACE_PATH_PREFIX = "/api";
    /**
     * 接口请求时间和服务器时间最大允许误差值(3分钟)
     */
    private static final Long SERVER_TIME_DIFFERENCE = 3 * 60 * 1000L;
    /**
     * 重放攻击存储的redis前缀
     */
    private static final String REDIS_PREX_FOR_NONCE = "nonce_";

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 40;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //是否启用拦截
        SignFilterProperties filterProperties = systemConfigProperties.getFilter().getSign();
        if (!filterProperties.getEnable()) {
            filterChain.doFilter(request, response);
            return;
        }

        //是否是文件上传
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        if (multipartContent) {
            //如果是文件流则不处理
            filterChain.doFilter(request, response);
            return;
        }

        //是否显示日志
        Boolean showLog = filterProperties.getLog();
        if (showLog) {
            log.debug("签名验证过滤器");
        }

        ContentCachingRequestWrapper requestWrapper = HttpServletUtils.convertToContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = HttpServletUtils.convertToContentCachingResponseWrapper(response);

        //请求路径
        String path = request.getRequestURI();

        /**
         * 过滤hook请求
         */
        if (path.indexOf("/api/v1/system/tus_hook") == -1&&path.indexOf("/api/v1/system/cas")==-1) {
            //签名校验
            if (path.startsWith(NEED_TRACE_PATH_PREFIX)) {
                //只校验api请求的签名参数
                if (!checkSign(requestWrapper, responseWrapper, showLog)) {
                    responseWrapper.copyBodyToResponse();
                    return;
                }
            }
        }


        //如果签名校验通过，则继续执行
        filterChain.doFilter(requestWrapper, responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

    /**
     * 签名校验
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @SuppressWarnings("DuplicatedCode")
    private boolean checkSign(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, boolean showLog) throws IOException {
        //依次检查各变量是否存在
        String appId = request.getHeader("appId");
        if (appId == null || "".equals(appId)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "签名无效");
            return false;
        }
        String timestampStr = request.getHeader("timestamp");
        if (timestampStr == null || "".equals(timestampStr)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "签名无效");
            return false;
        }
        String sign = request.getHeader("sign");
        if (sign == null || "".equals(sign)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "签名无效");
            return false;
        }
        String nonce = request.getHeader("nonce");
        if (nonce == null || "".equals(nonce)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "签名无效");
            return false;
        }

        //获取请求体
        String requestBody = HttpServletUtils.getRequestBody(request);
        //获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        //获取数据库中的密钥信息
        SysAppSecretModel appSecretModel = secretService.getByAppId(appId);

        if (appSecretModel.getExpireTime() != null && appSecretModel.getExpireTime() <= System.currentTimeMillis()) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "密钥已失效");
            return false;
        }

        //token获取
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        //加密次数
        int count = appSecretModel.getCountNum();

        //通过签名工具生成签名
        SignUtils signUtils = SignUtils.builder()
                .appId(appId)
                .appSecret(appSecretModel.getAppSecret())
                .count(count)
                .url(request.getRequestURI())
                .params(parameterMap)
                .requestBody(requestBody)
                .token(token)
                .timestamp(Long.valueOf(timestampStr))
                .nonce(Integer.valueOf(nonce))
                .showLog(showLog)
                .build();
        String encryStr = signUtils.buildSignNoTime();

        //签名校验
        if (!encryStr.equals(sign)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "签名无效");
            return false;
        }

        //前端的时间戳与服务器当前时间戳相差如果大于180，判定当前请求的timestamp无效
        if (Math.abs(Long.valueOf(timestampStr) - System.currentTimeMillis()) > SERVER_TIME_DIFFERENCE) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "请求已失效");
            return false;
        }

        //nonce是否存在于redis中，检查当前请求是否是重复请求
        String nonceKey = REDIS_PREX_FOR_NONCE + timestampStr + nonce;
        if (redisService.exists(nonceKey)) {
            ErrorUtils.throwResponse(response, ResultCode.NOT_EXTENDED, "二次非法请求");
            return false;
        }
        //存储重放攻击随机数
        redisService.set(nonceKey, nonce, SERVER_TIME_DIFFERENCE);

        //sign校验无问题,放行
        return true;
    }
}
