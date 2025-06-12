package com.jfbrother.utils;


import com.jfbrother.baseserver.utils.StringUtils;
import com.sudytech.ucp.serv.MessageSender;
import com.sudytech.ucp.serv.client.*;

import com.sudytech.ucp.ws.util.TokenBuilder;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Component
public class SmsSendUtils {
    //应用标志 (ucp平台提供)
    private static final String appId = "CN=https://*************,O=*********,C=CN";
    //秘钥，私钥 (ucp平台提供)
    private static final String privateKey = "***********";
    //发送webservice地址  (ucp平台提供)
    private static final String wsUrl = "https://****/UcpWebServ?wsdl";
    //消息盒id (ucp平台提供)
    private static int boxId = 63650;
    //int[] channeIds = new int[]{1,5}; 短信、邮箱、即时消息(1,2,3) //发送短信固定填1,邮箱2，可以都填 英文逗号分隔 (对接方选择通道)。其他消息通道例如钉钉询问负责人

    /**
     * 发送短信消息
     */
    public static SendResult sendSmsMessage(String phoneNum,String content){
        if(StringUtils.isEmpty(phoneNum)){
            return new SendResult();
        }
        Address[] address = new Address[]{new Address(phoneNum,"sms")};
        Message message = new Message();
        //设置接收人地址
        message.setTo(address);
        //设置标题
        message.setSubject("这是消息标题");
        //发送内容
        message.setContent(content);
        SendResult sendResult=null;
        try {
             sendResult = doSendMessage(new int[]{1}, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    /**
     * 发送微信公众号消息提供
     *
     * @throws Exception
     */
    public static SendResult sendWxMessage(String gh,String content){
        SendResult sendResult=null;
        Address[] address = new Address[]{new Address(gh,"uc_ux")};
        Message message = new Message();
        message.setTo(address);
        message.setContent(content);
        try {
            sendResult=doSendMessage(new int[]{5},message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
    public static SendResult doSendMessage(int[] channels,Message message) throws Exception {
        /**
         * 1.发送系统外部人员，new Address("可填手机号","sms");或者new Address("可填邮箱","email")
         * 2.发送系统内部人员，new Address("loginName(loginName)","uc_ux");   例如 new Address("zhangsan(zhangsan)","uc_ux")//loginName是统一身份认证的登录账号
         *3.计划发送 ，需要设置好发送时间
         * Calendar planTime = Calendar.getInstance();
         * planTime.add(12, 1);
         * SendResult sendResult = portType.sendMessage(boxId, message, channels, true, planTime, token);
         *
         */
        //是否有链接，0-无，1-有，如果有链接则需要将链接编码
        message.setMsgType(0);
        MessagePropertiesEntry signature = new MessagePropertiesEntry("smsSignature", ""); //
        //即时消息中的链接
        //MessagePropertiesEntry imLinkUrl = new MessagePropertiesEntry("im_linkUrl", "http://www.baidu.com");
        MessagePropertiesEntry[] entrys = new MessagePropertiesEntry[]{signature};
        message.setProperties(new MessageProperties(entrys));
        TokenBuilder tokenBuilder = new TokenBuilder(appId, privateKey);
        SendResult sendResult=null;
        try {
            String token = tokenBuilder.buildToken().toString();
            UcpWebServ_PortType portType = new MessageSender(wsUrl).loadUcpClient();
             sendResult = portType.sendMessage(boxId, message, channels, true, null, token);
            if(sendResult.isSucceeded()){
                System.out.println("sending ok!!!!");
                sendResult.getMessageId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    public static String createToken(){

        long timestamp = System.currentTimeMillis ();
        Random random = new Random ( 4 );
        int nonce = random.nextInt ( 1 );
        String signature = null;
        try {
            signature = signature (  appId,String.valueOf ( timestamp),String.valueOf ( nonce),privateKey );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put ( "appId",appId );
        jsonObject.put ( "timestamp", timestamp);
        jsonObject.put ( "nonce", nonce);
        jsonObject.put ( "signature", signature);
        return jsonObject.toString ();
    }
    public static String signature(String ... params) throws NoSuchAlgorithmException {
        List<String> sParamList = Arrays.asList ( params );
        Collections.sort(sParamList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sign = new StringBuilder();
        for (String sParam : sParamList) {
            sign.append(sParam);
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(sign.toString().getBytes());
        return toHex(md.digest());
    }
    public static String toHex(byte[] buffer) {
        StringBuilder sb = new StringBuilder(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }
        return sb.toString();
    }
}
