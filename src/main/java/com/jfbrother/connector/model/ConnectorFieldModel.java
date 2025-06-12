package com.jfbrother.connector.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口字段传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-20
 */
@Data
@ApiModel("接口字段传输对象")
@JSONType(orders = {"id", "connId", "fieldName", "fieldSource", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class ConnectorFieldModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "接口id", position = 1)
    private String connId;

    @ApiModelProperty(value = "字段名称", position = 2)
    private String fieldName;

    @ApiModelProperty(value = "数据来源", position = 3)
    private String fieldSource;

    @ApiModelProperty(value = "是否隐藏", position = 3)
    private Integer isHide;

    @ApiModelProperty(value = "数据来源表", position = 4)
    private String fieldSourceTable;

    @ApiModelProperty(value = "描述", position = 4)
    private String description;

    @ApiModelProperty(value = "类型", position = 4)
    private String type;

    @ApiModelProperty(value = "数据字典id", position = 4)
    private String dictId;

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
