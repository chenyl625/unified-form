package com.jfbrother.customForm.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormChildModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  自定义表单子表传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单子表传输对象search")
@JSONType(orders = { "id", "formId", "tableName", "tableComment", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormChildModelSearch extends CustomFormChildModel {

}
