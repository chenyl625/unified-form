package com.jfbrother.connector.model.search;

import java.math.BigDecimal;
import com.jfbrother.connector.model.ConnectorFieldModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  接口字段传输对象
* @author chenyl@jfbrother.com 2022-07-20
*/
@Data
@ApiModel("接口字段传输对象search")
@JSONType(orders = { "id", "connId", "fieldName", "fieldSource", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorFieldModelSearch extends ConnectorFieldModel {

}
