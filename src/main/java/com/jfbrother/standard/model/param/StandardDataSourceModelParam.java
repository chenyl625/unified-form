package com.jfbrother.standard.model.param;

import java.math.BigDecimal;
import com.jfbrother.standard.model.StandardDataSourceModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  数据来源标准库传输对象
* @author chenyl@jfbrother.com 2022-08-04
*/
@Data
@ApiModel("数据来源标准库传输对象param")
@JSONType(orders = { "id", "name", "checkUser", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class StandardDataSourceModelParam extends StandardDataSourceModel {

}
