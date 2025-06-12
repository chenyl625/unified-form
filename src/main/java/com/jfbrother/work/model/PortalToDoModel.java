package com.jfbrother.work.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("门户待办对象")
public class PortalToDoModel {
    @ApiModelProperty(value = "门户事项标识", position = 0)
    private String itemCode;
    @ApiModelProperty(value = "门户事项档案号",required = true,position = 0)
    private String itemDomId;
    @ApiModelProperty(value = "业务系统同步办件时使用的办件id",required = true, position = 0)
    private String instanceCode;
    @ApiModelProperty(value = "业务系统待办项id",required = true, position = 0)
    private String todoId;
    @ApiModelProperty(value = "待办项名称", position = 0)
    private String title;
    @ApiModelProperty(value = "待办项备注", position = 0)
    private String content;
    @ApiModelProperty(value = "TO_BE_RECEIVED(待接收)；DOING(办理中、已接收)；FINISHED(已完成)；CANCELLED(已取消)；FINISHED_WITH_EXCEPTION(异常结束);DELETED(已删除)",required = true, position = 0)
    private String status;
    @ApiModelProperty(value = "发起人的手机号、员工号、邮箱",required = true, position = 0)
    private String assignerSubject;
    @ApiModelProperty(value = "计划开始时刻毫秒数,Unix时间戳，可以理解为流程上报（创建）时间", position = 0)
    private Long planStartDateTime;
    @ApiModelProperty(value = "待办项开始时间Unix时间戳毫秒数",required = true, position = 0)
    private Long startDateTimeMills;
    @ApiModelProperty(value = "待办项结束时间Unix时间戳毫秒数,状态是FINISHED时必填",required = true, position = 0)
    private Long endDateTimeMills;
    @ApiModelProperty(value = "经办人点击待办项跳转时，url上target参数附加的值，方便业务系统做数据处理", position = 0)
    private String gotoVal;
    @ApiModelProperty(value = "经办人，可以对应多个，写经办人的手机号、员工号或者邮箱",required = true, position = 0)
    private String[] assigneeSubject;
}
