package com.jfbrother.student.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室调动日志数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生寝室调动日志数据传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "schoolYear", "term", "bedroomName", "changeBedroomName", "bedCode", "changeBedCode", "type", "transactors", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormitoryTransferLogDataModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "年份", position = 2)
    private String schoolYear;

    @ApiModelProperty(value = "1:第一学期，2:第二学期", position = 3)
    private String term;

    @ApiModelProperty(value = "原寝室名称", position = 4)
    private String bedroomName;

    @ApiModelProperty(value = "变更后寝室名称", position = 5)
    private String changeBedroomName;

    @ApiModelProperty(value = "原床位号", position = 6)
    private String bedCode;

    @ApiModelProperty(value = "变更后床位号", position = 7)
    private String changeBedCode;

    @ApiModelProperty(value = "调寝类型(1:调入，2:互调)", position = 8)
    private String type;

    @ApiModelProperty(value = "办理人", position = 9)
    private String transactors;

    @ApiModelProperty(value = "业务主键", position = 10)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 11)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 12)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 13)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 14)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 15)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 16)
    private String etlCheckDate;

}
