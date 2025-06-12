package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  所有学生基本数据子类传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("所有学生基本数据子类传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "xm", "jgId", "sfzh", "gkksh", "mzdmId", "xmpy", "csrq", "xydm", "xymc", "xb", "sydw", "gatdmId", "zzmmId", "zzmmMc", "bh", "xz", "bjmc", "sfzx", "xjzt", "xjztMc", "zydm", "zymc", "rxny", "rxsj", "xznj", "xslb", "rxnj", "bjnj", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsAllStuBaseInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "姓名", position = 2)
    private String xm;

    @ApiModelProperty(value = "籍贯(市/县)", position = 3)
    private String jgId;

    @ApiModelProperty(value = "身份证号", position = 4)
    private String sfzh;

    @ApiModelProperty(value = "gkksh", position = 5)
    private String gkksh;

    @ApiModelProperty(value = "民族代码", position = 6)
    private String mzdmId;

    @ApiModelProperty(value = "姓名拼音", position = 7)
    private String xmpy;

    @ApiModelProperty(value = "出生日期", position = 8)
    private String csrq;

    @ApiModelProperty(value = "学院代码", position = 9)
    private String xydm;

    @ApiModelProperty(value = "学院名称", position = 10)
    private String xymc;

    @ApiModelProperty(value = "性别", position = 11)
    private String xb;

    @ApiModelProperty(value = "生源单位", position = 12)
    private String sydw;

    @ApiModelProperty(value = "港澳台代码", position = 13)
    private String gatdmId;

    @ApiModelProperty(value = "政治面貌", position = 14)
    private String zzmmId;

    @ApiModelProperty(value = "政治面貌名称", position = 15)
    private String zzmmMc;

    @ApiModelProperty(value = "班号", position = 16)
    private String bh;

    @ApiModelProperty(value = "学制", position = 17)
    private String xz;

    @ApiModelProperty(value = "班级名称", position = 18)
    private String bjmc;

    @ApiModelProperty(value = "是否在校", position = 19)
    private String sfzx;

    @ApiModelProperty(value = "学籍状态", position = 20)
    private String xjzt;

    @ApiModelProperty(value = "学籍状态名称", position = 21)
    private String xjztMc;

    @ApiModelProperty(value = "专业代码", position = 22)
    private String zydm;

    @ApiModelProperty(value = "专业名称", position = 23)
    private String zymc;

    @ApiModelProperty(value = "入学年月", position = 24)
    private String rxny;

    @ApiModelProperty(value = "入学日期", position = 25)
    private String rxsj;

    @ApiModelProperty(value = "现在年级", position = 26)
    private String xznj;

    @ApiModelProperty(value = "xslb", position = 27)
    private String xslb;

    @ApiModelProperty(value = "入学年级", position = 28)
    private String rxnj;

    @ApiModelProperty(value = "班级年级", position = 29)
    private String bjnj;

    @ApiModelProperty(value = "id", position = 30)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 31)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 32)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 33)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 34)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 35)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 36)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 37)
    private String etlCheckDate;

}
