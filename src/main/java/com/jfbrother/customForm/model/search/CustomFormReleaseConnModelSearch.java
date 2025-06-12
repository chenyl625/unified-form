package com.jfbrother.customForm.model.search;

import java.math.BigDecimal;
import com.jfbrother.customForm.model.CustomFormReleaseConnModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  自定义表单发布对应接口传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("自定义表单发布对应接口传输对象search")
@JSONType(orders = { "id", "releaseId", "formFieldId", "connFieldId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseConnModelSearch extends CustomFormReleaseConnModel {

}
