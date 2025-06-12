package com.jfbrother.customForm.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormReleaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  自定义表单发布传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("自定义表单发布传输对象search")
@JSONType(orders = { "id", "formId", "isEnable", "fillScope", "startDateTime", "endDateTime", "flowCustomDef", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseModelSearch extends CustomFormReleaseModel {

}
