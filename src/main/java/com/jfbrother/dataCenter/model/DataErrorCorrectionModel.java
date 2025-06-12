package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据纠错补录表传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-05
 */
@Data
@ApiModel("数据纠错补录表传输对象")
@JSONType(orders = {"id", "connFieldId", "oldValue", "newValue", "jsonRow", "checkUser", "checkDateTime", "checkStatus", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataErrorCorrectionModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "接口字段id", position = 1)
    private String connFieldId;

    @ApiModelProperty(value = "旧值", position = 2)
    private String oldValue;

    @ApiModelProperty(value = "新值", position = 3)
    private String newValue;

    @ApiModelProperty(value = "字段数据json", position = 4)
    private String rowJson;

    @ApiModelProperty(value = "审核人", position = 5)
    private String checkUser;

    @ApiModelProperty(value = "审核时间", position = 6)
    private Long checkDateTime;

    @ApiModelProperty(value = "审核状态", position = 7)
    private Integer checkStatus;

    @ApiModelProperty(value = "申请时间", position = 6)
    private Long applyDateTime;

    @ApiModelProperty(value = "字段json", position = 6)
    private String labelJson;

    @ApiModelProperty(value = "排序号", position = 8)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 9)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 10)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 11)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 12)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 13)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 14)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 15)
    private String updateUser;

}
