package com.jfbrother.digitalPortraitSnap.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  全部专业基本信息传输对象
 * @author hjy@jfbrother.com 2023-03-08
 */
@Data
@ApiModel("全部专业基本信息传输对象")
@JSONType(orders = { "yxId", "yxmc", "yxdm", "id", "zymc", "zyDm", "gbzydm", "xz", "etlPri", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GxGxxsMajorBaseInfpModel {
    @ApiModelProperty(value = "学院id", position = 0)
    private String yxId;

    @ApiModelProperty(value = "学院名称", position = 1)
    private String yxmc;

    @ApiModelProperty(value = "院系代码", position = 2)
    private String yxdm;

    @ApiModelProperty(value = "专业id", position = 3)
    private String id;

    @ApiModelProperty(value = "专业名称", position = 4)
    private String zymc;

    @ApiModelProperty(value = "专业代码", position = 5)
    private String zyDm;

    @ApiModelProperty(value = "国标专业代码", position = 6)
    private String gbzydm;

    @ApiModelProperty(value = "学制", position = 7)
    private String xz;

    @ApiModelProperty(value = "业务主键", position = 8)
    private String etlPri;

    @ApiModelProperty(value = "校验唯一性", position = 9)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 10)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 11)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 12)
    private String etlCheckDate;

}
