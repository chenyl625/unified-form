package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("驱动流程（环节提交）-请求模型")
public class WorkFlowTaskForwardRequestModel {

    @ApiModelProperty(value = "办理环节id", position = 1, required = true)
    private int task_id;

    @ApiModelProperty(value = "办理人", position = 2, required = true)
    private String assignee;

    @ApiModelProperty(value = "审批意见编码，当前环节存在审批意见项时提供，同意: 1 不同意： 0 默认 同意", position = 3)
    private String appr_code;

    @ApiModelProperty(value = "审批意见描述 默认 “同意“", position = 3)
    private String appr_desc;

    @ApiModelProperty(value = "环节办理人列表", position = 4)
    private List<WorkFlowCreateRequestModel.WorkFlowCreateFormAssigneeDataModel> assignee_data;

}
