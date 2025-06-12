package com.jfbrother.connector.model.extend;

import java.math.BigDecimal;
import com.jfbrother.connector.model.ConnectorInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  接口管理传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("接口管理传输对象extend")
@JSONType(orders = { "id", "connName", "connUrl", "withParams", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorInfoModelExtend extends ConnectorInfoModel {

}
