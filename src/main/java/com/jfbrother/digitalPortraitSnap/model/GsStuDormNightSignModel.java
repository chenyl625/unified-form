package com.jfbrother.digitalPortraitSnap.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室晚归签到传输对象
 * @author xinz@jfbrother.com 2023-03-09
 */
@Data
@ApiModel("学生寝室晚归签到传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "type", "checkTime", "way", "lateTime", "checkStu", "checkStuCode", "remark", "createTime", "updateTime", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuDormNightSignModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "签到类别(3:晚归，4:夜不归宿)", position = 2)
    private String type;

    @ApiModelProperty(value = "夜检日期", position = 3)
    private String checkTime;

    @ApiModelProperty(value = "生成方式(1:夜检员，2:夜检学生扫码，3:自己扫二维码晚归，4:系统自动生成，5:自己登记)", position = 4)
    private String way;

    @ApiModelProperty(value = "晚归时间", position = 5)
    private String lateTime;

    @ApiModelProperty(value = "检查人员姓名", position = 6)
    private String checkStu;

    @ApiModelProperty(value = "检查人员学号", position = 7)
    private String checkStuCode;

    @ApiModelProperty(value = "备注", position = 8)
    private String remark;

    @ApiModelProperty(value = "创建时间", position = 9)
    private String createTime;

    @ApiModelProperty(value = "修改时间", position = 10)
    private String updateTime;

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
