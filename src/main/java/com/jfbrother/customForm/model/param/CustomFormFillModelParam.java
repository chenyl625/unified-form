package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFillModel;
import com.jfbrother.work.model.request.WorkFlowCreateRequestModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
*  自定义表单填报传输对象
* @author chenyl@jfbrother.com 2022-06-21
*/
@Data
@ApiModel("自定义表单填报传输对象param")
@JSONType(orders = { "id", "userId", "formId", "flowStatus", "flowId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFillModelParam extends CustomFormFillModel {

    @ApiModelProperty(value = "填报数据", position = 1)
    private JSONObject data;

    @ApiModelProperty(value = "环节办理人列表", position = 2)
    private List<WorkFlowCreateRequestModel.WorkFlowCreateFormAssigneeDataModel> assigneeData;

}
