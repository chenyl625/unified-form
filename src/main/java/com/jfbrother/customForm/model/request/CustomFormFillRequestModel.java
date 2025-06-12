package com.jfbrother.customForm.model.request;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.work.model.request.WorkFlowCreateRequestModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("自定义表单填报-请求模型")
public class CustomFormFillRequestModel {

    @ApiModelProperty(value = "填报用户id", position = 1)
    private String userId;

    @ApiModelProperty(value = "填报数据", position = 1)
    private JSONObject data;

    @ApiModelProperty(value = "环节办理人列表", position = 2)
    private List<WorkFlowCreateRequestModel.WorkFlowCreateFormAssigneeDataModel> assigneeData;

}
