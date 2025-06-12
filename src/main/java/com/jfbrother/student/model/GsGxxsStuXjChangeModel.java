package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生学籍异动最新记录传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生学籍异动最新记录传输对象")
@JSONType(orders = { "etlJfuuid", "xn", "xh", "userXh", "changeTypeCode", "changeTypeName", "changeTime", "changeReasonCode", "confirmTime", "changeNumber", "changeExplain", "oldnj", "oldyx", "oldzy", "oldbj", "yddnj", "yddyx", "yddzy", "yddbj", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuXjChangeModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学年", position = 1)
    private String xn;

    @ApiModelProperty(value = "学号", position = 2)
    private String xh;

    @ApiModelProperty(value = "user_xh", position = 3)
    private String userXh;

    @ApiModelProperty(value = "异动类别", position = 4)
    private String changeTypeCode;

    @ApiModelProperty(value = "异动类别名称", position = 5)
    private String changeTypeName;

    @ApiModelProperty(value = "异动时间", position = 6)
    private String changeTime;

    @ApiModelProperty(value = "异动原因", position = 7)
    private String changeReasonCode;

    @ApiModelProperty(value = "确认时间", position = 8)
    private String confirmTime;

    @ApiModelProperty(value = "异动文号", position = 9)
    private String changeNumber;

    @ApiModelProperty(value = "异动说明", position = 10)
    private String changeExplain;

    @ApiModelProperty(value = "原年级", position = 11)
    private String oldnj;

    @ApiModelProperty(value = "原院系", position = 12)
    private String oldyx;

    @ApiModelProperty(value = "原专业", position = 13)
    private String oldzy;

    @ApiModelProperty(value = "原班级", position = 14)
    private String oldbj;

    @ApiModelProperty(value = "异动到年级", position = 15)
    private String yddnj;

    @ApiModelProperty(value = "异动到院系", position = 16)
    private String yddyx;

    @ApiModelProperty(value = "异动到专业", position = 17)
    private String yddzy;

    @ApiModelProperty(value = "异动到班级", position = 18)
    private String yddbj;

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
