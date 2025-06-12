package com.jfbrother.dataCenter.model.search;

import java.math.BigDecimal;
import com.jfbrother.dataCenter.model.DataOpenModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  数据开放管理传输对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ApiModel("数据开放管理传输对象search")
@JSONType(orders = { "id", "themeId", "dataItemName", "connId", "icons", "openRoles", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataOpenModelSearch extends DataOpenModel {

}
