package com.jfbrother.standard.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.standard.model.StandardDataSourceModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  数据来源标准库传输对象
* @author chenyl@jfbrother.com 2022-08-04
*/
@Data
@ApiModel("数据来源标准库传输对象extend")
@JSONType(orders = { "id", "name", "checkUser", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class StandardDataSourceModelExtend extends StandardDataSourceModel {

    @ApiModelProperty(value = "审核人姓名", position = 2)
    private String checkUserName;

}
