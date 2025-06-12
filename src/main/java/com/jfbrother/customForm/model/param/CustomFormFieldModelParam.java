package com.jfbrother.customForm.model.param;

import java.math.BigDecimal;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  自定义表单字段传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单字段传输对象param")
@JSONType(orders = { "id", "pid", "comment", "defaultValue", "maxLength", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFieldModelParam extends CustomFormFieldModel {

}
