package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.DataOpenModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  数据开放管理传输对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ApiModel("数据开放管理传输对象extend")
@JSONType(orders = { "id", "themeId", "dataItemName", "connId", "icons", "openRoles", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataOpenModelExtend extends DataOpenModel {
    @ApiModelProperty(value = "主题名称", position = 1)
    private String themeName;
    @ApiModelProperty(value = "接口名称", position = 1)
    private String connName;
    @ApiModelProperty(value = "接口地址", position = 1)
    private String connUrl;
    @ApiModelProperty(value = "接口地址（分页）", position = 1)
    private String connUrlPage;
}
