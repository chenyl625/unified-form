package com.jfbrother.work.model.search;

import java.math.BigDecimal;
import com.jfbrother.work.model.MessageSendingTemplateModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  消息发送模版表传输对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ApiModel("消息发送模版表传输对象search")
@JSONType(orders = { "id", "templateName", "templateContent", "matchingKey", "sendObj", "type", "isOpen", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class MessageSendingTemplateModelSearch extends MessageSendingTemplateModel {

}
