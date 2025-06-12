package com.jfbrother.standard.model.param;

import java.math.BigDecimal;
import com.jfbrother.standard.model.StandardDataThemeModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  数据开放主题管理传输对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ApiModel("数据开放主题管理传输对象param")
@JSONType(orders = { "id", "themeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class StandardDataThemeModelParam extends StandardDataThemeModel {

}
