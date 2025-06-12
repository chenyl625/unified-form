package com.jfbrother.work.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  消息发送模版表传输对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ApiModel("消息发送模版表传输对象")
@JSONType(orders = { "id", "templateName", "templateContent", "matchingKey", "sendObj", "type", "isOpen", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class MessageSendingTemplateModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "模版名称", position = 1)
    private String templateName;

    @ApiModelProperty(value = "模版内容", position = 2)
    private String templateContent;

    @ApiModelProperty(value = "匹配的key", position = 3)
    private String matchingKey;

    @ApiModelProperty(value = "发送的对象（角色）", position = 4)
    private String sendObj;

    @ApiModelProperty(value = "类型（1.短信 2.微信消息）", position = 5)
    private Integer type;

    @ApiModelProperty(value = "是否开启（0.否 1.是）", position = 6)
    private Integer isOpen;

    @ApiModelProperty(value = "排序号", position = 7)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码（0：正常，1：撤销）", position = 8)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 9)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 10)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 12)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 13)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 14)
    private String updateUser;

}
