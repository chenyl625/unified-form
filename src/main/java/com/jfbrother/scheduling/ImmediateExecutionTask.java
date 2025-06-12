package com.jfbrother.scheduling;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.model.extend.CustomFormReleaseModelExtend;
import com.jfbrother.customForm.service.CustomFormReleaseService;
import com.jfbrother.dataCenter.jpa.GsTeacherBaseInfo;
import com.jfbrother.dataCenter.jpa.QGsTeacherBaseInfo;

import com.jfbrother.utils.SmsSendUtils;
import com.jfbrother.work.jpa.MessageSendingTemplate;
import com.jfbrother.work.jpa.QMessageSendingTemplate;
import com.jfbrother.work.model.extend.MessageSendingDataModelExtend;
import com.jfbrother.work.model.param.MessageSendingDataModelParam;
import com.jfbrother.work.service.MessageSendingDataService;


import com.sudytech.ucp.serv.client.SendResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ImmediateExecutionTask extends BaseServiceImpl implements ApplicationRunner  {

    @Autowired
    private RedisService redisService;

    @Value("${immediateExecutionTask.enable}")
    private boolean enable;

    @Value("${baseUrl}")
    private String baseUrl;
    @Autowired
    private SmsSendUtils smsSendUtils;


    @Autowired
    private MessageSendingDataService messageSendingDataService;
    @Autowired
    private CustomFormReleaseService customFormReleaseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (enable) {
            //启动任务
            log.info("项目已经启动，下面启动消息队列消费机制，baseUrl【{}】", baseUrl);

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> res = restTemplate.getForEntity(baseUrl + "/v2/api-docs?group=v1", String.class);

                if (!StringUtils.isEmpty(res) && !StringUtils.isEmpty(res.getBody())) {
                    redisService.set(RedisKeyConstant.SWAGGER_API, res.getBody());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Scheduled(cron = "0 0/2 * * * ?")
    public void sendSmsMessageTask(){
        boolean flag=true;
        while (flag){
            Object obj = redisService.rPop(RedisKeyConstant.SMS_MESSAGE_TASK);
            if (obj != null) {
                MessageSendingDataModelExtend sendingModel = JSONObject.parseObject(obj.toString(), MessageSendingDataModelExtend.class);
                if(!StringUtils.isEmpty(sendingModel.getMatchingKey())){
                    QMessageSendingTemplate qMessageSendingTemplate = QMessageSendingTemplate.messageSendingTemplate;
                    QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
                    List<MessageSendingTemplate> templateList = queryFactory.select(qMessageSendingTemplate).from(qMessageSendingTemplate)
                            .where(qMessageSendingTemplate.isOpen.eq(1).and(qMessageSendingTemplate.matchingKey.eq(sendingModel.getMatchingKey()))).fetch();
                    List<GsTeacherBaseInfo> reciverList = queryFactory.selectFrom(qGsTeacherBaseInfo).where(qGsTeacherBaseInfo.gh.in(StringUtils.join(",", sendingModel.getReciverGhList()))).fetch()
                            .stream().collect(Collectors.toList());
                    List<String> sendUserName=new ArrayList<>();
                    if(!StringUtils.isEmpty(sendingModel.getSendUser())){
                        sendUserName = queryFactory.select(qGsTeacherBaseInfo.xm).from(qGsTeacherBaseInfo).where(qGsTeacherBaseInfo.gh.eq(sendingModel.getSendUser())).fetch();
                    }else {
                        sendUserName.add("管理员");
                    }

                    CustomFormReleaseModelExtend customFormReleaseModel = customFormReleaseService.get(sendingModel.getBelongReleaseId());

                    Map<String,Object> paramMap=new HashMap<>();
                    paramMap.put("item_name",customFormReleaseModel.getReleaseName());
                    paramMap.put("submit_teacher_name",sendUserName.get(0));
                    for (GsTeacherBaseInfo teacherBaseInfo : reciverList) {
                        paramMap.put("hanld_teacher_name",teacherBaseInfo.getXm());

                        for (MessageSendingTemplate template : templateList) {
                            String templateContent = template.getTemplateContent();
                            StringSubstitutor str = new StringSubstitutor(paramMap);
                            String content = str.replace(templateContent);
                            sendingModel.setMessageContent(content);
                            sendingModel.setReceiveGh(teacherBaseInfo.getGh());
                            sendingModel.setReceivePhone(teacherBaseInfo.getSj());
                            sendingModel.setBelongReleaseId(sendingModel.getBelongReleaseId());
                            sendingModel.setBelongTemplate(template.getId());
                            sendingModel.setSendUser(StringUtils.isEmpty(sendingModel.getSendUser())?"admin":sendingModel.getSendUser());
                            sendingModel.setSendDateTime(System.currentTimeMillis());
                            sendingModel.setType(template.getType());
                            if(template.getType().equals(1)&&!StringUtils.isEmpty(teacherBaseInfo.getSj())){
                                //发送短信消息
                                SendResult sendResult = smsSendUtils.sendSmsMessage(teacherBaseInfo.getSj(),content);
                                sendingModel.setSendStatus(sendResult.isSucceeded()?1:0);
                            }
                            if(template.getType().equals(2)){
                                //发送微信消息
                                SendResult sendResult = smsSendUtils.sendWxMessage(teacherBaseInfo.getGh(), content);
                                sendingModel.setSendStatus(sendResult.isSucceeded()?1:0);
                            }
                            messageSendingDataService.post(CopyUtils.copyBean(sendingModel, MessageSendingDataModelParam.class));
                        }
                    }


                }

            }else {
                flag=false;
            }

        }

    }
}
