package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  学生等级考试情况传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生等级考试情况传输对象")
@JSONType(orders = { "etlJfuuid", "nj", "xh", "userXh", "xm", "sex", "sfzh", "bjdm", "bjmc", "yxId", "college", "zyId", "major", "lbId", "djId", "shortName", "score", "compreScore", "signTime", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuLevelEaxmInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "年级", position = 1)
    private String nj;

    @ApiModelProperty(value = "学号", position = 2)
    private String xh;

    @ApiModelProperty(value = "user_xh", position = 3)
    private String userXh;

    @ApiModelProperty(value = "姓名", position = 4)
    private String xm;

    @ApiModelProperty(value = "性别", position = 5)
    private String sex;

    @ApiModelProperty(value = "身份证号", position = 6)
    private String sfzh;

    @ApiModelProperty(value = "班级代码", position = 7)
    private String bjdm;

    @ApiModelProperty(value = "班级名称", position = 8)
    private String bjmc;

    @ApiModelProperty(value = "所属院系", position = 9)
    private String yxId;

    @ApiModelProperty(value = "学院名称", position = 10)
    private String college;

    @ApiModelProperty(value = "所属专业", position = 11)
    private String zyId;

    @ApiModelProperty(value = "专业名称", position = 12)
    private String major;

    @ApiModelProperty(value = "等级类别id", position = 13)
    private String lbId;

    @ApiModelProperty(value = "等级id", position = 14)
    private String djId;

    @ApiModelProperty(value = "等级简称", position = 15)
    private String shortName;

    @ApiModelProperty(value = "成绩", position = 16)
    private BigDecimal score;

    @ApiModelProperty(value = "综合成绩", position = 17)
    private String compreScore;

    @ApiModelProperty(value = "报名时间", position = 18)
    private String signTime;

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
