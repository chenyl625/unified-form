package com.jfbrother.customForm.model.search;

import com.jfbrother.customForm.model.CustomFormModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;

import lombok.Data;

/**
*  自定义表单传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单传输对象search")
@JSONType(orders = { "id", "name", "chineseExplain", "remark", "isEnable", "fillScope", "approvalProcess", "startDateTime", "endDateTime", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormModelSearch extends CustomFormModel {

}
