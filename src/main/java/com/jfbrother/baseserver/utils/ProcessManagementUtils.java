package com.jfbrother.baseserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProcessManagementUtils {

    public final String CLIENT_ID = "******";
    public final String SECRET_KEY = "*****";
    public final String BMP_URL = "http://*******";

    @Autowired
    private RedisService redisService;

    public String createToken() {
        String token = null;

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.set("client_id", CLIENT_ID);
        param.set("secret", SECRET_KEY);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity("http://xinoa.zjbti.net.cn/wh/client/bpm/dispatcher" + "?cmd=token&client_id={1}&secret={2}", String.class, CLIENT_ID, SECRET_KEY);
        JSONObject body = JSON.parseObject(res.getBody());
        if (body.get("queryObj") != null) {
            JSONObject queryObj = JSONObject.parseObject(body.get("queryObj").toString());
            token = queryObj.getString("token");
        }

        return token;
    }

    public String getToken() {
        String token;

        Object obj = redisService.get(RedisKeyConstant.iBMP_TOKEN);
        if (obj == null) {
            token = createToken();

            //access_token的有效期为7200秒（2小时）
            redisService.set(RedisKeyConstant.iBMP_TOKEN, token, 2 * 60 * 60L);
        } else {
            token = String.valueOf(obj);
        }

        return token;
    }

}
