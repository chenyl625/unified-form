package com.jfbrother.work.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  消息发送数据管理表传输对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ApiModel("消息发送数据管理表传输对象")
@JSONType(orders = { "id", "sendUser", "receiveGh", "receivePhone", "messageContent", "belongTemplate", "belongReleaseId", "sendStatus", "sendDateTime", "wechatUserId", "content", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class MessageSendingDataModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "发送人", position = 1)
    private String sendUser;

    @ApiModelProperty(value = "接受人工号", position = 2)
    private String receiveGh;

    @ApiModelProperty(value = "接受人手机号", position = 3)
    private String receivePhone;

    @ApiModelProperty(value = "消息内容（正文）", position = 4)
    private String messageContent;

    @ApiModelProperty(value = "所属模版", position = 5)
    private String belongTemplate;

    @ApiModelProperty(value = "所属表单发布id", position = 6)
    private String belongReleaseId;

    @ApiModelProperty(value = "所属填报id", position = 7)
    private String belongFillId;

    @ApiModelProperty(value = "发送状态(0-失败，1-成功)", position = 7)
    private Integer sendStatus;

    @ApiModelProperty(value = "发送时间", position = 8)
    private Long sendDateTime;

    @ApiModelProperty(value = "接收人的微信userid", position = 9)
    private String wechatUserId;

    @ApiModelProperty(value = "消息完整对象", position = 10)
    private String content;

    @ApiModelProperty(value = "消息类型（1.短信 2.微信消息）", position = 11)
    private Integer type;

    @ApiModelProperty(value = "排序号", position = 12)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码（0：正常，1：撤销）", position = 13)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 14)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 15)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 16)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 17)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 18)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 19)
    private String updateUser;

}
