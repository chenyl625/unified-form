package com.jfbrother.customForm.model.extend;

import java.math.BigDecimal;
import com.jfbrother.customForm.model.CustomFormRuleModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  自定义表单规则传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单规则传输对象extend")
@JSONType(orders = { "id", "formId", "text", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormRuleModelExtend extends CustomFormRuleModel {

}
