package com.jfbrother.dataCenter.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.DataErrorCorrectionModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  数据纠错补录表传输对象
* @author chenyl@jfbrother.com 2022-08-05
*/
@Data
@ApiModel("数据纠错补录表传输对象param")
@JSONType(orders = { "id", "connFieldId", "oldValue", "newValue", "jsonRow", "checkUser", "checkDateTime", "checkStatus", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataErrorCorrectionModelParam extends DataErrorCorrectionModel {

}
