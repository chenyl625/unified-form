package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教务系统里的在校生传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("教务系统里的在校生传输对象")
@JSONType(orders = { "etlJfuuid", "ssbjId", "xm", "xb", "sfzh", "csrq", "mzdmId", "jgId", "sysfId", "bjmc", "yxId", "zyId", "xz", "nj", "xh", "rxnj", "rxsj", "studentPhone", "zzmmId", "xjzt", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuBaseEntireModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "班级代码", position = 1)
    private String ssbjId;

    @ApiModelProperty(value = "姓名", position = 2)
    private String xm;

    @ApiModelProperty(value = "性别", position = 3)
    private String xb;

    @ApiModelProperty(value = "身份证号", position = 4)
    private String sfzh;

    @ApiModelProperty(value = "出生日期", position = 5)
    private String csrq;

    @ApiModelProperty(value = "民族代码", position = 6)
    private String mzdmId;

    @ApiModelProperty(value = "籍贯(市/县)", position = 7)
    private String jgId;

    @ApiModelProperty(value = "生源省份", position = 8)
    private String sysfId;

    @ApiModelProperty(value = "班级名称", position = 9)
    private String bjmc;

    @ApiModelProperty(value = "所属院系", position = 10)
    private String yxId;

    @ApiModelProperty(value = "所属专业", position = 11)
    private String zyId;

    @ApiModelProperty(value = "学制", position = 12)
    private String xz;

    @ApiModelProperty(value = "年级", position = 13)
    private Integer nj;

    @ApiModelProperty(value = "学号", position = 14)
    private String xh;

    @ApiModelProperty(value = "入学年级", position = 15)
    private String rxnj;

    @ApiModelProperty(value = "入学日期", position = 16)
    private String rxsj;

    @ApiModelProperty(value = "联系电话", position = 17)
    private String studentPhone;

    @ApiModelProperty(value = "政治面貌", position = 18)
    private String zzmmId;

    @ApiModelProperty(value = "学籍状态", position = 19)
    private String xjzt;

    @ApiModelProperty(value = "主键", position = 20)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 21)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 22)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 23)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 24)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 25)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 26)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 27)
    private String etlCheckDate;

}
