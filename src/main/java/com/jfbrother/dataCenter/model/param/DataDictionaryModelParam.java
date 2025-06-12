package com.jfbrother.dataCenter.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.DataDictionaryChildModel;
import com.jfbrother.dataCenter.model.DataDictionaryModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
*  数据字典表传输对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Data
@ApiModel("数据字典表传输对象param")
@JSONType(orders = { "id", "name", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataDictionaryModelParam extends DataDictionaryModel {
    @ApiModelProperty(value = "子表数据", position = 0)
    private List<DataDictionaryChildModel> dictChildList;
}
