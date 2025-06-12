package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.connector.model.extend.ConnectorFieldModelExtend;
import com.jfbrother.dataCenter.model.DataErrorCorrectionModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  数据纠错补录表传输对象
* @author chenyl@jfbrother.com 2022-08-05
*/
@Data
@ApiModel("数据纠错补录表传输对象extend")
@JSONType(orders = { "id", "connFieldId", "oldValue", "newValue", "jsonRow", "checkUser", "checkDateTime", "checkStatus", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataErrorCorrectionModelExtend extends DataErrorCorrectionModel {

    @ApiModelProperty(value = "接口字段信息", position = 1)
    private ConnectorFieldModelExtend connField;

    @ApiModelProperty(value = "字段数据来源", position = 2)
    private String fieldSourceName;

    @ApiModelProperty(value = "审核人", position = 3)
    private String checkUserName;

    @ApiModelProperty(value = "纠错类型", position = 4)
    private String corrType;

}
