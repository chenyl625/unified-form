package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("表单组件值-请求模型")
public class WorkFlowFormDataRequestModel {

    @ApiModelProperty(value = "流程id", position = 1, required = true)
    private int wf_id;

    @ApiModelProperty(value = "组件自定义标识，多个 逗号分割；为空则返回所有配置了自定义标识的组件值", position = 2)
    private String item_def;

}
