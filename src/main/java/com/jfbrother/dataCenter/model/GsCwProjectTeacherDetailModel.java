package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  项目发放-教职工收入传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("项目发放-教职工收入传输对象")
@JSONType(orders = { "etlJfuuid", "bh", "xm", "gh", "je", "gxkh", "gwkh", "yglxwb", "yglx", "bz", "nybs", "jfly", "jfrq", "jfspr", "ffnr", "xmktbh", "xmlx", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwProjectTeacherDetailModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "编号", position = 1)
    private String bh;

    @ApiModelProperty(value = "姓名", position = 2)
    private Integer xm;

    @ApiModelProperty(value = "工号", position = 3)
    private String gh;

    @ApiModelProperty(value = "金额", position = 4)
    private BigDecimal je;

    @ApiModelProperty(value = "建行卡号", position = 5)
    private String gxkh;

    @ApiModelProperty(value = "公务卡号", position = 6)
    private String gwkh;

    @ApiModelProperty(value = "用工类型文本", position = 7)
    private String yglxwb;

    @ApiModelProperty(value = "用工类型", position = 8)
    private Integer yglx;

    @ApiModelProperty(value = "备注", position = 9)
    private String bz;

    @ApiModelProperty(value = "年月标识", position = 10)
    private String nybs;

    @ApiModelProperty(value = "经费来源", position = 11)
    private Integer jfly;

    @ApiModelProperty(value = "经费日期", position = 12)
    private String jfrq;

    @ApiModelProperty(value = "经费来源（成本中心）", position = 13)
    private String jfspr;

    @ApiModelProperty(value = "发放内容", position = 14)
    private String ffnr;

    @ApiModelProperty(value = "项目/课题代码", position = 15)
    private String xmktbh;

    @ApiModelProperty(value = "项目类型", position = 16)
    private Integer xmlx;

    @ApiModelProperty(value = "id", position = 17)
    private Integer id;

    @ApiModelProperty(value = "业务主键", position = 18)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 19)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 20)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 21)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 22)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 23)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 24)
    private String etlCheckDate;

}
