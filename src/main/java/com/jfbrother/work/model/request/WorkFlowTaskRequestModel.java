package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程环节-请求模型
 */
@Data
@ApiModel("流程环节-请求模型")
public class WorkFlowTaskRequestModel {

    @ApiModelProperty(value = "流程实例id", position = 1, required = true)
    private int wf_id;

    @ApiModelProperty(value = "环节id 多个用逗号分割", position = 2)
    private String task_id;

    @ApiModelProperty(value = "环节自定义标识（当前填写在备注中）， 多个用逗号分割", position = 3)
    private String task_def;

}
