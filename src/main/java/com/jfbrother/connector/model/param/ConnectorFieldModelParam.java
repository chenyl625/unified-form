package com.jfbrother.connector.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.connector.model.ConnectorFieldModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  接口字段传输对象
* @author chenyl@jfbrother.com 2022-07-20
*/
@Data
@ApiModel("接口字段传输对象param")
@JSONType(orders = { "id", "connId", "fieldName", "fieldSource", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorFieldModelParam extends ConnectorFieldModel {

}
