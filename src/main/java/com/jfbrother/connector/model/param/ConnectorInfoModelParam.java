package com.jfbrother.connector.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.connector.model.ConnectorInfoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  接口管理传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("接口管理传输对象param")
@JSONType(orders = { "id", "connName", "connUrl", "withParams", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorInfoModelParam extends ConnectorInfoModel {

}
