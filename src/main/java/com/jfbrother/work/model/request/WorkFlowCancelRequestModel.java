package com.jfbrother.work.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("取消流程-请求模型")
public class WorkFlowCancelRequestModel {

    @ApiModelProperty(value = "流程id", position = 1, required = true)
    @NotNull(message = "流程id不能为空")
    private int wf_id;

    @ApiModelProperty(value = "操作用户", position = 2, required = true)
    @NotNull(message = "操作用户不能为空")
    private String op_user;

}
