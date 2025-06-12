package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师专业技术职务传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("教师专业技术职务传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "positionCode", "positionName", "acquireDate", "bmh", "finalPositionCode", "subPositionCode", "rankPositionCode", "rsZyjszwParentid", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherPositionModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "专业技术职务编号", position = 3)
    private String positionCode;

    @ApiModelProperty(value = "专业技术职务名称", position = 4)
    private String positionName;

    @ApiModelProperty(value = "专业技术职务取得时间", position = 5)
    private String acquireDate;

    @ApiModelProperty(value = "部门号", position = 6)
    private String bmh;

    @ApiModelProperty(value = "职称代码", position = 7)
    private String finalPositionCode;

    @ApiModelProperty(value = "职称编号最后一位", position = 8)
    private String subPositionCode;

    @ApiModelProperty(value = "排名时用到的职务代码", position = 9)
    private String rankPositionCode;

    @ApiModelProperty(value = "人事定义的专业技术职务的父节点", position = 10)
    private String rsZyjszwParentid;

    @ApiModelProperty(value = "主键", position = 11)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 12)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 13)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 14)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 15)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 16)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 17)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 18)
    private String etlCheckDate;

}
