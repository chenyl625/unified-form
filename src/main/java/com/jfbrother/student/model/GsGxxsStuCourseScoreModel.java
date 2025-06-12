package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  学生课程成绩表传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生课程成绩表传输对象")
@JSONType(orders = { "etlJfuuid", "xh", "xn", "xq", "zxf", "kch", "kcmc", "fxFlag", "khfsDm", "kscj", "cxbkFlag", "remark", "jd", "xfj", "bjdm", "zyId", "xb", "kscjNum", "floorJd", "kclbName", "kclb2Name", "kclb3Name", "yxDm", "getXf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuCourseScoreModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String xh;

    @ApiModelProperty(value = "修读学年", position = 2)
    private String xn;

    @ApiModelProperty(value = "修读学期", position = 3)
    private String xq;

    @ApiModelProperty(value = "学分", position = 4)
    private BigDecimal zxf;

    @ApiModelProperty(value = "课程号", position = 5)
    private String kch;

    @ApiModelProperty(value = "课程名称", position = 6)
    private String kcmc;

    @ApiModelProperty(value = "辅修标志", position = 7)
    private String fxFlag;

    @ApiModelProperty(value = "考核方式", position = 8)
    private String khfsDm;

    @ApiModelProperty(value = "考试成绩", position = 9)
    private String kscj;

    @ApiModelProperty(value = "重修补修标志", position = 10)
    private String cxbkFlag;

    @ApiModelProperty(value = "备注", position = 11)
    private String remark;

    @ApiModelProperty(value = "绩点", position = 12)
    private BigDecimal jd;

    @ApiModelProperty(value = "学分绩点", position = 13)
    private BigDecimal xfj;

    @ApiModelProperty(value = "班级代码", position = 14)
    private String bjdm;

    @ApiModelProperty(value = "所属专业", position = 15)
    private String zyId;

    @ApiModelProperty(value = "性别", position = 16)
    private String xb;

    @ApiModelProperty(value = "考试成绩num类型", position = 17)
    private BigDecimal kscjNum;

    @ApiModelProperty(value = "绩点取整", position = 18)
    private Integer floorJd;

    @ApiModelProperty(value = "课程类别", position = 19)
    private String kclbName;

    @ApiModelProperty(value = "课程类别2", position = 20)
    private String kclb2Name;

    @ApiModelProperty(value = "课程类别3", position = 21)
    private String kclb3Name;

    @ApiModelProperty(value = "所属学院代码", position = 22)
    private String yxDm;

    @ApiModelProperty(value = "已取得学分", position = 23)
    private BigDecimal getXf;

    @ApiModelProperty(value = "业务主键", position = 24)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 25)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 26)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 27)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 28)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 29)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 30)
    private String etlCheckDate;

}
