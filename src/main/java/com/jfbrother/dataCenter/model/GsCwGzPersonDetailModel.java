package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  工资发放-个人收入明细传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("工资发放-个人收入明细传输对象")
@JSONType(orders = { "etlJfuuid", "nybs", "xmrlzy", "gh", "yfgz", "sfgz", "gw", "xjgz", "gwjt", "shbt", "gjj", "yanglj", "yilj", "syj", "ghf", "yknj", "qtbfk", "sds", "id", "mainid", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwGzPersonDetailModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "年月标识", position = 1)
    private String nybs;

    @ApiModelProperty(value = "姓名", position = 2)
    private Integer xmrlzy;

    @ApiModelProperty(value = "工号", position = 3)
    private String gh;

    @ApiModelProperty(value = "应发工资", position = 4)
    private BigDecimal yfgz;

    @ApiModelProperty(value = "实发工资", position = 5)
    private BigDecimal sfgz;

    @ApiModelProperty(value = "岗位工资", position = 6)
    private BigDecimal gw;

    @ApiModelProperty(value = "薪级工资", position = 7)
    private BigDecimal xjgz;

    @ApiModelProperty(value = "岗位津贴", position = 8)
    private BigDecimal gwjt;

    @ApiModelProperty(value = "生活补贴", position = 9)
    private BigDecimal shbt;

    @ApiModelProperty(value = "公积金", position = 10)
    private BigDecimal gjj;

    @ApiModelProperty(value = "养老金", position = 11)
    private BigDecimal yanglj;

    @ApiModelProperty(value = "医疗金", position = 12)
    private BigDecimal yilj;

    @ApiModelProperty(value = "失业金", position = 13)
    private BigDecimal syj;

    @ApiModelProperty(value = "工会费", position = 14)
    private BigDecimal ghf;

    @ApiModelProperty(value = "预扣年金", position = 15)
    private BigDecimal yknj;

    @ApiModelProperty(value = "其他", position = 16)
    private BigDecimal qtbfk;

    @ApiModelProperty(value = "所得税", position = 17)
    private BigDecimal sds;

    @ApiModelProperty(value = "主键", position = 18)
    private Integer id;

    @ApiModelProperty(value = "mainid", position = 19)
    private Integer mainid;

    @ApiModelProperty(value = "业务主键", position = 20)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 21)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 22)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 23)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 24)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 25)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 26)
    private String etlCheckDate;

}
