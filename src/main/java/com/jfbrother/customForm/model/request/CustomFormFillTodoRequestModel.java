package com.jfbrother.customForm.model.request;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.work.model.request.WorkFlowCreateRequestModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CustomFormFillTodoRequestModel {

    @ApiModelProperty(value = "流程id", position = 2, required = true)
    private Integer flowId;

    @ApiModelProperty(value = "审批意见编码（1:同意，0:不同意）", position = 1)
    private String apprCode;

    @ApiModelProperty(value = "审批意见描述", position = 2)
    private String apprDesc;

    @ApiModelProperty(value = "环节办理人列表", position = 3)
    private List<WorkFlowCreateRequestModel.WorkFlowCreateFormAssigneeDataModel> assigneeData;

    @ApiModelProperty(value = "填报数据", position = 4)
    private JSONObject data;
}
