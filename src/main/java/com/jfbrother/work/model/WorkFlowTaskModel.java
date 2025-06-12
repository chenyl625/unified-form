package com.jfbrother.work.model;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 * 流程环节实例传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
@Data
@ApiModel("流程环节实例传输对象")
@JSONType(orders = {"id", "fillId", "taskId", "taskName", "mobileUrl", "pcUrl", "assigneeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class WorkFlowTaskModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "填报id", position = 1)
    private String fillId;

    @ApiModelProperty(value = "环节id", position = 2)
    private int taskId;

    @ApiModelProperty(value = "办理序号", position = 3)
    private int orderId;

    @ApiModelProperty(value = "环节名称", position = 4)
    private String name;

    @ApiModelProperty(value = "办理时限", position = 5)
    private String timeLimit;

    @ApiModelProperty(value = "办理时长", position = 6)
    private String diffHours;

    @ApiModelProperty(value = "办理时间", position = 7)
    private Long assignDate;

    @ApiModelProperty(value = "结束时间", position = 8)
    private Long endDate;

    @ApiModelProperty(value = "状态（待办，进行中，已完成，已取消，待审）", position = 9)
    private String status;

    @ApiModelProperty(value = "办理人", position = 10)
    private String assigeeName;

    @ApiModelProperty(value = "办理人工号", position = 10)
    private String assigneeAccount;

    @ApiModelProperty(value = "是否为当前环节", position = 10)
    private Integer isCurrTask;

    @ApiModelProperty(value = "排序号", position = 11)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 12)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 13)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 14)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 15)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 16)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 17)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 18)
    private String updateUser;

}
