package com.jfbrother.customForm.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  自定义表单字段传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单字段传输对象search")
@JSONType(orders = { "id", "pid", "comment", "defaultValue", "maxLength", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFieldModelSearch extends CustomFormFieldModel {
    private String releaseId;
}
