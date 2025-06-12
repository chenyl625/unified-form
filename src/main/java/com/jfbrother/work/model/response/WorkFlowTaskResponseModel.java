package com.jfbrother.work.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程环节-响应模型
 */
@Data
@ApiModel("流程环节-响应模型")
public class WorkFlowTaskResponseModel {

    @ApiModelProperty(value = "流程id", position = 1)
    private int id;

    @ApiModelProperty(value = "办理序号", position = 2)
    private int orderId;

    @ApiModelProperty(value = "环节名称", position = 3)
    private String name;

    @ApiModelProperty(value = "办理时限， 单位 ： 天（d），小时(h), 分钟（m）", position = 4)
    private String timeLimit;

    @ApiModelProperty(value = "办理时长， 单位 小时(h) 结束时间 – 开始时间", position = 5)
    private String diffHours;

    @ApiModelProperty(value = "办理时间", position = 7)
    private String assignDate;

    @ApiModelProperty(value = "结束时间", position = 8)
    private String endDate;

    @ApiModelProperty(value = "状态 待办，进行中，已完成，已取消，待审", position = 9)
    private String status;

    @ApiModelProperty(value = "环节办理人", position = 10)
    private String assigeeName;

    @ApiModelProperty(value = "环节办理人工号", position = 11)
    private String assingeeAccount;

}
