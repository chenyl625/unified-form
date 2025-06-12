package com.jfbrother.connector.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.connector.model.ConnectorFieldModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  接口字段传输对象
* @author chenyl@jfbrother.com 2022-07-20
*/
@Data
@ApiModel("接口字段传输对象extend")
@JSONType(orders = { "id", "connId", "fieldName", "fieldSource", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorFieldModelExtend extends ConnectorFieldModel {

    @ApiModelProperty(value = "数据来源名称", position = 1)
    private String fieldSourceName;

    @ApiModelProperty(value = "审核人员", position = 2)
    private String checkUser;

    @ApiModelProperty(value = "审核人员名称", position = 3)
    private String checkUserName;

}
