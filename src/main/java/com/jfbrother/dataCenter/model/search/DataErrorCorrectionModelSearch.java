package com.jfbrother.dataCenter.model.search;

import java.math.BigDecimal;
import com.jfbrother.dataCenter.model.DataErrorCorrectionModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  数据纠错补录表传输对象
* @author chenyl@jfbrother.com 2022-08-05
*/
@Data
@ApiModel("数据纠错补录表传输对象search")
@JSONType(orders = { "id", "connFieldId", "oldValue", "newValue", "jsonRow", "checkUser", "checkDateTime", "checkStatus", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataErrorCorrectionModelSearch extends DataErrorCorrectionModel {

}
