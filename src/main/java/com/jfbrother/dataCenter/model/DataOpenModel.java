package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  数据开放管理传输对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ApiModel("数据开放管理传输对象")
@JSONType(orders = { "id", "themeId", "dataItemName", "connId", "icons", "openRoles", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataOpenModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "所属主题", position = 1)
    private String themeId;

    @ApiModelProperty(value = "数据项名称", position = 2)
    private String dataItemName;

    @ApiModelProperty(value = "接口地址", position = 3)
    private String connId;

    @ApiModelProperty(value = "图标", position = 4)
    private String icons;

    @ApiModelProperty(value = "开放角色", position = 5)
    private String openRoles;

    @ApiModelProperty(value = "排序号", position = 6)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 7)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 8)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 9)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 11)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 13)
    private String updateUser;

}
