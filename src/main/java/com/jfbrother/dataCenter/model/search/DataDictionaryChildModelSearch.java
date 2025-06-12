package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.DataDictionaryChildModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  数据字典子表传输对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Data
@ApiModel("数据字典子表传输对象search")
@JSONType(orders = { "id", "pid", "code", "name", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataDictionaryChildModelSearch extends DataDictionaryChildModel {

}
