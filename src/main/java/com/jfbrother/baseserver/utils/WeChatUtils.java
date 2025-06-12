package com.jfbrother.baseserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeChatUtils {

    @Autowired
    private RedisService redisService;

    public final String BASE_URL = "https://api.weixin.qq.com";
    public final String APPID = "*******";
    public final String APPSECRET = "******";


    public String createAccessToken() {
        String url=BASE_URL+"/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        JSONObject result = JSON.parseObject(body);
        String accessToken = result.get("access_token").toString();
        return accessToken;
    }

    public String getAccessToken() {
        String accessToken=null;
        Object obj = redisService.get(RedisKeyConstant.WECHAT_ACCESS_TOKEN);
        if (obj == null) {
            accessToken = createAccessToken();
            //access_token的有效期为7200秒（2小时）
            redisService.set(RedisKeyConstant.WECHAT_ACCESS_TOKEN, accessToken, 7200L);
        } else {
            accessToken = String.valueOf(obj);
        }

        return accessToken;
    }

    public String getTicket(){
        String ticket=null;
        String url=BASE_URL+"/cgi-bin/ticket/getticket?access_token="+getAccessToken()+"&type=jsapi";
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        JSONObject result = JSON.parseObject(body);
        if("0".equals(result.get("errcode"))){
            ticket = result.get("ticket").toString();
        }
        return ticket;
    }

    public String getSign(){
        String ticket = getTicket();
        long timestamp = System.currentTimeMillis();
        String url="jsapi_ticket="+ticket+"&noncestr="+11+"&timestamp="+timestamp+"&url=http://mp.weixin.qq.com?params=value";
        String signature = DigestUtils.sha1Hex(url);
        return signature;
    }

    public JSONObject getUserInfoByOpenId(String openId){
        String url=BASE_URL+"/cgi-bin/user/info?access_token="+getAccessToken()+"&openid="+openId;
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        JSONObject result = JSON.parseObject(body);
        result.get("unionid");
        result.get("subscribe");//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号
        result.get("subscribe_scene");//返回用户关注的渠道来源
        log.info("当前微信用户信息[{}]",body);
        return result;
    }

    /**
     * 如果网页授权作用域为snsapi_userinfo,则可调用次方法
     * @param openId
     * @return
     */
    public String getSnsapiUserinfo(String openId){
        String url=BASE_URL+"/sns/userinfo?access_token="+getAccessToken()+"&openid="+openId;
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        JSONObject result = JSON.parseObject(body);
        result.get("nickname");
        result.get("sex");
        result.get("province");
        result.get("city");
        result.get("country");
        result.get("unionid");
        return null;
    }

    /**
     * 通过用户授权获取code传入到方法中从而得到openID或UnionId
     * @param code
     * @return
     */
    public String getOpenIDOrUnionId(String code){
        String openId=null;
        String url=BASE_URL+"/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        JSONObject result = JSON.parseObject(body);
        if(result.get("openid")!=null){
            openId=result.get("openid").toString();
        }
        return openId;
    }

    public String getPhoneNum(String code){
        String url=BASE_URL+"/wxa/business/getuserphonenumber&access_token="+getAccessToken();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(param, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = res.getBody();
        return null;
    }
}
