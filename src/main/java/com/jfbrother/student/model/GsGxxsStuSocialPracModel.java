package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 学生社会实践传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Data
@ApiModel("学生社会实践传输对象")
@JSONType(orders = {"etlJfuuid", "xh", "nj", "bjmc", "participateMinute", "type", "acquireReason", "ssyx", "isEnable", "scoreType", "subNj", "bjdm", "subBjmc", "startDate", "endDate", "acquireDate", "acquireNum", "unit", "importType", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuSocialPracModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "年级", position = 2)
    private String nj;

    @ApiModelProperty(value = "班级名称", position = 3)
    private String bjmc;

    @ApiModelProperty(value = "参与时长（分）", position = 4)
    private Long participateMinute;

    @ApiModelProperty(value = "活动分类", position = 5)
    private String type;

    @ApiModelProperty(value = "获取原因", position = 6)
    private String acquireReason;

    @ApiModelProperty(value = "所属院系", position = 7)
    private String ssyx;

    @ApiModelProperty(value = "是否有效", position = 8)
    private String isEnable;

    @ApiModelProperty(value = "学分类型", position = 9)
    private String scoreType;

    @ApiModelProperty(value = "年级简称", position = 10)
    private String subNj;

    @ApiModelProperty(value = "班级代码", position = 11)
    private String bjdm;

    @ApiModelProperty(value = "班级名称简称", position = 12)
    private String subBjmc;

    @ApiModelProperty(value = "开始时间", position = 13)
    private String startDate;

    @ApiModelProperty(value = "结束时间", position = 14)
    private String endDate;

    @ApiModelProperty(value = "获取时间", position = 15)
    private String acquireDate;

    @ApiModelProperty(value = "获得数量", position = 16)
    private BigDecimal acquireNum;

    @ApiModelProperty(value = "单位", position = 17)
    private String unit;

    @ApiModelProperty(value = "导入的类型（1-学分，2-志愿小时）", position = 18)
    private String importType;

    @ApiModelProperty(value = "业务主键", position = 19)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 20)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 21)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 22)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 23)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 24)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 25)
    private String etlCheckDate;

}
