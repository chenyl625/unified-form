package com.jfbrother.standard.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.standard.model.StandardDataThemeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  数据开放主题管理传输对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ApiModel("数据开放主题管理传输对象search")
@JSONType(orders = { "id", "themeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class StandardDataThemeModelSearch extends StandardDataThemeModel {

}
