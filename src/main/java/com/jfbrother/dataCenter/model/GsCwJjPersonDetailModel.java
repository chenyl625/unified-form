package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  奖金发放-个人收入明细传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("奖金发放-个人收入明细传输对象")
@JSONType(orders = { "etlJfuuid", "nybs", "xm", "gh", "yf", "ks", "sf", "yglx", "jjlx", "lymx", "jxnw", "id", "mainid", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwJjPersonDetailModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "年月标识", position = 1)
    private String nybs;

    @ApiModelProperty(value = "姓名", position = 2)
    private Integer xm;

    @ApiModelProperty(value = "工号", position = 3)
    private String gh;

    @ApiModelProperty(value = "应发", position = 4)
    private BigDecimal yf;

    @ApiModelProperty(value = "扣税", position = 5)
    private BigDecimal ks;

    @ApiModelProperty(value = "实发", position = 6)
    private BigDecimal sf;

    @ApiModelProperty(value = "用工类型", position = 7)
    private String yglx;

    @ApiModelProperty(value = "奖金类型", position = 8)
    private Integer jjlx;

    @ApiModelProperty(value = "来源明细", position = 9)
    private String lymx;

    @ApiModelProperty(value = "绩效内外", position = 10)
    private Integer jxnw;

    @ApiModelProperty(value = "主键", position = 11)
    private Integer id;

    @ApiModelProperty(value = "mainid", position = 12)
    private Integer mainid;

    @ApiModelProperty(value = "业务主键", position = 13)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 14)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 15)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 16)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 17)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 18)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 19)
    private String etlCheckDate;

}
