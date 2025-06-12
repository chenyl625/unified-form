package com.jfbrother.student.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生注册数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生注册数据传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "xn", "xq", "bjmc", "zczt", "zcrq", "bdrq", "wzcyy", "zxzt", "xjzt", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuRegistrationDataModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "学年", position = 2)
    private String xn;

    @ApiModelProperty(value = "学期", position = 3)
    private String xq;

    @ApiModelProperty(value = "班级名称", position = 4)
    private String bjmc;

    @ApiModelProperty(value = "注册状态", position = 5)
    private String zczt;

    @ApiModelProperty(value = "注册日期", position = 6)
    private String zcrq;

    @ApiModelProperty(value = "报道日期", position = 7)
    private String bdrq;

    @ApiModelProperty(value = "未注册原因", position = 8)
    private String wzcyy;

    @ApiModelProperty(value = "在校状态", position = 9)
    private String zxzt;

    @ApiModelProperty(value = "学籍状态", position = 10)
    private String xjzt;

    @ApiModelProperty(value = "业务主键", position = 11)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 12)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 13)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 14)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 15)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 16)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 17)
    private String etlCheckDate;

}
