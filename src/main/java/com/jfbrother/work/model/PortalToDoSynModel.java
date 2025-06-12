package com.jfbrother.work.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("门户待办推送对象")
public class PortalToDoSynModel {
    @ApiModelProperty(value = "门户事项标识", position = 0)
    private String itemCode;
    @ApiModelProperty(value = "门户事项档案号",required = true, position = 0)
    private String itemDomId;
    @ApiModelProperty(value = "办件编号，可以回传门户点击在线办理时传入的instanceId，也可以用应用内部的办件id",required = true, position = 0)
    private String code;
    @ApiModelProperty(value = "办件名称", position = 0)
    private String name;
    @ApiModelProperty(value = "TO_BE_RECEIVED(待接收)；DOING(办理中、已接收)；FINISHED(已完成)；CANCELLED(已取消)；FINISHED_WITH_EXCEPTION(异常结束);DELETED(已删除)",required = true, position = 0)
    private String status;
    @ApiModelProperty(value = "发起人的手机号、员工号、邮箱",required = true, position = 0)
    private String assignerSubject;
    @ApiModelProperty(value = "计划开始时刻毫秒数,Unix时间戳，可以理解为流程上报（创建）时间", position = 0)
    private Long planStartDateTime;
    @ApiModelProperty(value = "实际开始时间，也可以理解为工作接受时间（流程第一个环节创建时间），status为DOING、FINISHED、FINISHED_WITH_EXCEPTION时，必填",required = true, position = 0)
    private Long actualStartDateTime;
    @ApiModelProperty(value = "实际完成时间，可以理解为工作完成时间，status为FINISHED、FINISHED_WITH_EXCEPTION时",required = true, position = 0)
    private Long actualEndDateTime;
    @ApiModelProperty(value = "打开办件时，会附加到url上，以参数target带回", position = 0)
    private String gotoUrl;
}
