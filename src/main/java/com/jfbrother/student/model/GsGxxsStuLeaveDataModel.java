package com.jfbrother.student.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生请假数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生请假数据传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "schoolYear", "term", "type", "category", "startTime", "endTime", "firstSection", "lastSection", "sectionLength", "sky", "reason", "file", "teacherName", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuLeaveDataModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "年份", position = 2)
    private String schoolYear;

    @ApiModelProperty(value = "1:第一学期，2:第二学期", position = 3)
    private String term;

    @ApiModelProperty(value = "1:普通请假，2:长期请假", position = 4)
    private String type;

    @ApiModelProperty(value = "1:因公请假，2:因私请假，3:因病请假", position = 5)
    private String category;

    @ApiModelProperty(value = "请假开始时间", position = 6)
    private String startTime;

    @ApiModelProperty(value = "请假结束时间", position = 7)
    private String endTime;

    @ApiModelProperty(value = "请假开始节次", position = 8)
    private String firstSection;

    @ApiModelProperty(value = "请假结束节次", position = 9)
    private String lastSection;

    @ApiModelProperty(value = "请假节数", position = 10)
    private String sectionLength;

    @ApiModelProperty(value = "请假天数", position = 11)
    private String sky;

    @ApiModelProperty(value = "请假原因", position = 12)
    private String reason;

    @ApiModelProperty(value = "请假附件", position = 13)
    private String file;

    @ApiModelProperty(value = "教师姓名", position = 14)
    private String teacherName;

    @ApiModelProperty(value = "业务主键", position = 15)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 16)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 17)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 18)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 19)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 20)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 21)
    private String etlCheckDate;

}
