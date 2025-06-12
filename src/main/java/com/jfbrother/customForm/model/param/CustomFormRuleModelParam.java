package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormRuleModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  自定义表单规则传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单规则传输对象param")
@JSONType(orders = { "id", "formId", "text", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormRuleModelParam extends CustomFormRuleModel {

}
