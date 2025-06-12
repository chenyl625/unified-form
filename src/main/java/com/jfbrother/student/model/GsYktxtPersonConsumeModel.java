package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 个人消费记录传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Data
@ApiModel("个人消费记录传输对象")
@JSONType(orders = {"etlJfuuid", "id", "code", "bookkeepdate", "dealtime", "feepaymentway", "businessdeptcode", "mondeal", "mondiscount", "businessnum", "businessname", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsYktxtPersonConsumeModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "交易流水号", position = 1)
    private Integer id;

    @ApiModelProperty(value = "工号/学号", position = 2)
    private String code;

    @ApiModelProperty(value = "记账日", position = 3)
    private String bookkeepdate;

    @ApiModelProperty(value = "交易时间", position = 4)
    private String dealtime;

    @ApiModelProperty(value = "费用缴纳方式", position = 5)
    private String feepaymentway;

    @ApiModelProperty(value = "交易类型编码", position = 6)
    private Integer businessdeptcode;

    @ApiModelProperty(value = "交易金额", position = 7)
    private BigDecimal mondeal;

    @ApiModelProperty(value = "优惠金额", position = 8)
    private BigDecimal mondiscount;

    @ApiModelProperty(value = "商户编号", position = 9)
    private Integer businessnum;

    @ApiModelProperty(value = "商户名称", position = 10)
    private String businessname;

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
