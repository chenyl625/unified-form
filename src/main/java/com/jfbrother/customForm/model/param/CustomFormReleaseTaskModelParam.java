package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormReleaseTaskModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  自定义表单发布环节传输对象
* @author chenyl@jfbrother.com 2022-07-04
*/
@Data
@ApiModel("自定义表单发布环节传输对象param")
@JSONType(orders = { "id", "formId", "taskIdentify", "taskIdentifyName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseTaskModelParam extends CustomFormReleaseTaskModel {

}
