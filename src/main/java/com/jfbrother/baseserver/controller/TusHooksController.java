package com.jfbrother.baseserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.service.TusHooksService;
import com.jfbrother.baseserver.utils.JwtTokenUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.baseserver.version.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Tus hook
 *
 * @author chenyl
 */
@ApiSort(10)
@Api(tags = {"Tus hook-1.0.0-20210701"})
@Validated
@RestController
@RequestMapping("/api/v1/system/tus_hook")
@Slf4j
public class TusHooksController {
    @Autowired
    private TusHooksService tusHooksService;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1)
    @ApiOperation(value = "Hook逻辑")
    @ApiVersion(1)
    @RequestMapping
    public ResponseEntity<Object> hooks(HttpServletRequest request, HttpServletResponse response) {
        log.info("enter hook controller");
        String body = getBody(request);
        JSONObject bodyObject = JSONObject.parseObject(body);
        JSONObject header = bodyObject.getJSONObject("HTTPRequest").getJSONObject("Header");

        String hookName = request.getHeader("hook-name");
        log.info("hooname:"+hookName);
        if ("pre-create".equals(hookName)) {
            log.info("pre-create");
            String id = getId(header);
            if (StringUtils.isEmpty(id)) {
//                log.error("禁止上传:\n{}", body);
                //表示未登录
                return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        } else if ("pre-finish".equals(hookName)) {
            log.info("pre-finish");
            JSONObject upload = bodyObject.getJSONObject("Upload");
            JSONObject metaData = upload.getJSONObject("MetaData");
            //写入系统临时文件表
            tusHooksService.insertTempInfo(
                    getId(header),
                    upload.getString("ID"),
                    metaData.getString("filename"),
                    metaData.getString("filetype"),
                    upload.getLong("Size")
            );
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 从jwt中解析用户id
     *
     * @param header
     * @return
     */
    private String getId(JSONObject header) {
        //校验凭证，判断是否可以上传
        JSONArray authorizationArr = header.getJSONArray("Authorization");
        if (authorizationArr == null || authorizationArr.size() == 0) {
            return null;
        }

        String token = authorizationArr.get(0).toString().replace("Bearer ", "");
        log.info("token:"+token);
        String id = null;
        try {
            id = JwtTokenUtils.getId(token);
            log.info("token:"+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
