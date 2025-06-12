package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("环节审批意见-请求模型")
public class WorkFlowTaskApprovalRequestModel {

    @ApiModelProperty(value = "流程id", position = 1, required = true)
    private String wf_id;

    @ApiModelProperty(value = "环节自定义标识，多个用“,”号分隔，为空标识获取所有环节", position = 2)
    private String tasks;

}
