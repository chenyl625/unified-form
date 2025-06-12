package com.jfbrother.work.model.extend;

import java.math.BigDecimal;

import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import com.jfbrother.work.model.MessageSendingDataModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  消息发送数据管理表传输对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ApiModel("消息发送数据管理表传输对象extend")
@JSONType(orders = { "id", "sendUser", "receiveGh", "receivePhone", "messageContent", "belongTemplate", "belongReleaseId", "sendStatus", "sendDateTime", "wechatUserId", "content", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class MessageSendingDataModelExtend extends MessageSendingDataModel {

    @ApiModelProperty(value = "接收人列表", position = 3)
    private String[] reciverGhList;

    @ApiModelProperty(value = "匹配的key", position = 3)
    private String matchingKey;

    private GsTeacherBaseInfoModel sendTeacher;
    private GsTeacherBaseInfoModel reciverTeacher;
    private String templateName;
    private String formReleaseName;
}
