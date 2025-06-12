package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生家庭联系方式传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("学生家庭联系方式传输对象")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "homeAd", "fatherName", "fatherPhone", "motherName", "motherPhone", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuFamilyContactModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String studentCode;

    @ApiModelProperty(value = "学生姓名", position = 2)
    private String studentName;

    @ApiModelProperty(value = "家庭住址", position = 3)
    private String homeAd;

    @ApiModelProperty(value = "父亲姓名", position = 4)
    private String fatherName;

    @ApiModelProperty(value = "父亲电话", position = 5)
    private String fatherPhone;

    @ApiModelProperty(value = "母亲姓名", position = 6)
    private String motherName;

    @ApiModelProperty(value = "母亲电话", position = 7)
    private String motherPhone;

    @ApiModelProperty(value = "业务主键", position = 8)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 9)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 10)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 11)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 12)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 13)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 14)
    private String etlCheckDate;


    @ApiModelProperty(value = "与本人关系", position = 14)
    private String relation;

    @ApiModelProperty(value = "家长姓名", position = 14)
    private String parentName;

    @ApiModelProperty(value = "电话", position = 14)
    private String phone;
}
