package com.jfbrother.work.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 流程-响应模型
 */
@Data
@ApiModel("流程-响应模型")
public class WorkFlowResponseModel {

    @ApiModelProperty(value = "流程id", position = 1)
    private int wfId;

    @ApiModelProperty(value = "流程名称", position = 2)
    private String name;

    @ApiModelProperty(value = "发起人", position = 3)
    private String initUser;

    @ApiModelProperty(value = "流程状态 进行中，已完成，已取消", position = 4)
    private String status;

    @ApiModelProperty(value = "启动时间 YYYY-MM-DD HH24:MI:SS", position = 5)
    private String startDate;

    @ApiModelProperty(value = "结束时间 YYYY-MM-DD HH24:MI:SS", position = 5)
    private String endDate;

    @ApiModelProperty(value = "当前环节信息", position = 2)
    private List<WorkFlowCurrentTaskModel> currTask;

    @Data
    @ApiModel("当前环节信息实例")
    public static class WorkFlowCurrentTaskModel {

        @ApiModelProperty(value = "环节id", position = 3)
        private int id;

        @ApiModelProperty(value = "办理序号 比如：顺序签", position = 3)
        private int orderId;

        @ApiModelProperty(value = "环节名称", position = 3)
        private String name;

        @ApiModelProperty(value = "办理时限， 单位 ： 天（d），小时(h), 分钟（m）", position = 3)
        private String timeLimit;

        @ApiModelProperty(value = "办理时长， 单位 小时(h) 结束时间 – 开始时间", position = 3)
        private String diffHours;

        @ApiModelProperty(value = "状态 待办，进行中，已完成，已取消，待审", position = 3)
        private String status;

        @ApiModelProperty(value = "环节办理人", position = 3)
        private String assigeeName;
    }

}
