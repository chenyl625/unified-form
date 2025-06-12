package com.jfbrother.connector.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  接口管理传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("接口管理传输对象")
@JSONType(orders = { "id", "connName", "connUrl", "withParams", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorInfoModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "接口名称", position = 1)
    private String connName;

    @ApiModelProperty(value = "接口地址", position = 2)
    private String connUrl;

    @ApiModelProperty(value = "接口地址（分页）", position = 2)
    private String connUrlPage;

    @ApiModelProperty(value = "携带参数", position = 3)
    private String withParams;

    @ApiModelProperty(value = "接口类型", position = 3)
    private String connType;

    @ApiModelProperty(value = "排序号", position = 4)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 5)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 6)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 7)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 8)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 9)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 10)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 11)
    private String updateUser;

}
