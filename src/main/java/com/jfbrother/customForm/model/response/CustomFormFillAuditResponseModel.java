package com.jfbrother.customForm.model.response;

import com.jfbrother.customForm.model.CustomFormFillModel;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("自定义表单填报审核列表-响应模型")
public class CustomFormFillAuditResponseModel extends CustomFormFillModel {

    @ApiModelProperty(value = "填报人姓名", position = 1)
    private String fillUserName;

    @ApiModelProperty(value = "表单名称", position = 2)
    private String releaseName;

    @ApiModelProperty(value = "环节信息", position = 3)
    private List<WorkFlowTaskModelExtend> taskList;

    @ApiModelProperty(value = "当前环节信息", position = 4)
    private List<WorkFlowTaskModelExtend> currTaskList;

}
