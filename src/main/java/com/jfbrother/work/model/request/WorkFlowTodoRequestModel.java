package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("流程待办-请求模型")
public class WorkFlowTodoRequestModel {

    @ApiModelProperty(value = "待办用户", position = 1, required = true)
    private String assignUser;

    @ApiModelProperty(value = "流程发起人", position = 2)
    private String initUser;

    @ApiModelProperty(value = "启动时间（开始）", position = 3)
    private String initTimeStart;

    @ApiModelProperty(value = "启动时间（结束）", position = 4)
    private String initTimeEnd;

}
