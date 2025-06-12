package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生联系方式传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("学生联系方式传输对象")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "studentShortphone", "homeAd", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuContactModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String studentCode;

    @ApiModelProperty(value = "学生姓名", position = 2)
    private String studentName;

    @ApiModelProperty(value = "联系电话", position = 3)
    private String studentPhone;

    @ApiModelProperty(value = "短号", position = 4)
    private String studentShortphone;

    @ApiModelProperty(value = "家庭住址", position = 5)
    private String homeAd;

    @ApiModelProperty(value = "id", position = 6)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 7)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 8)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 9)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 10)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 11)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 12)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 13)
    private String etlCheckDate;

}
