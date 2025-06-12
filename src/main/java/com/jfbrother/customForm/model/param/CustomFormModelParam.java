package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import com.jfbrother.customForm.model.CustomFormModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
*  自定义表单传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单传输对象param")
@JSONType(orders = { "id", "name", "chineseExplain", "remark", "isEnable", "fillScope", "approvalProcess", "startDateTime", "endDateTime", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormModelParam extends CustomFormModel {

    @ApiModelProperty(value = "表单规则", position = 1)
    private String ruleText;

    @ApiModelProperty(value = "表单字段", position = 2)
    private List<CustomFormFieldModel> tableColumn;

    @ApiModelProperty(value = "表单子表", position = 3)
    private List<CustomFormChildModelParam> tableChild;
}
