package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学院基本信息传输对象
* @author chenyl@jfbrother.com 2022-08-31
*/
@Data
@ApiModel("学院基本信息传输对象")
@JSONType(orders = { "etlJfuuid", "id", "yxmc", "yxdm", "ssdwId", "zyCount", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsInstitutionBaseInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学院id", position = 1)
    private String id;

    @ApiModelProperty(value = "院系名称", position = 2)
    private String yxmc;

    @ApiModelProperty(value = "院系代码", position = 3)
    private String yxdm;

    @ApiModelProperty(value = "ssdw_id", position = 4)
    private String ssdwId;

    @ApiModelProperty(value = "专业数量", position = 5)
    private Integer zyCount;

    @ApiModelProperty(value = "业务主键", position = 6)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 7)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 8)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 9)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 10)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 11)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 12)
    private String etlCheckDate;

}
