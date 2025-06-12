package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  教师排课基本信息传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("教师排课基本信息传输对象")
@JSONType(orders = { "etlJfuuid", "kkxn", "kkxq", "kch", "analyseZc", "dsz", "yxDm", "sksj", "skdd", "skrs", "jsszxqDm", "jslxDm", "gh", "jsxm", "skbjDm", "skbjName", "kcmc", "jsh", "startWeek", "endWeek", "zxf", "zxs", "syxs", "skxq", "jcinfo", "stimezc", "realSkbj", "kclb", "kclbName", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKbBasicInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "开课学年", position = 1)
    private String kkxn;

    @ApiModelProperty(value = "开课学期", position = 2)
    private String kkxq;

    @ApiModelProperty(value = "课程码", position = 3)
    private String kch;

    @ApiModelProperty(value = "上课时间周次", position = 4)
    private String analyseZc;

    @ApiModelProperty(value = "单双周（1-单周，2-双周）", position = 5)
    private String dsz;

    @ApiModelProperty(value = "学院代码", position = 6)
    private String yxDm;

    @ApiModelProperty(value = "上课时间", position = 7)
    private String sksj;

    @ApiModelProperty(value = "上课地点", position = 8)
    private String skdd;

    @ApiModelProperty(value = "上课人数", position = 9)
    private String skrs;

    @ApiModelProperty(value = "教室所在校区代码", position = 10)
    private String jsszxqDm;

    @ApiModelProperty(value = "教室类型码", position = 11)
    private String jslxDm;

    @ApiModelProperty(value = "教师工号", position = 12)
    private String gh;

    @ApiModelProperty(value = "教师姓名", position = 13)
    private String jsxm;

    @ApiModelProperty(value = "上课班级代码", position = 14)
    private String skbjDm;

    @ApiModelProperty(value = "上课班级名称", position = 15)
    private String skbjName;

    @ApiModelProperty(value = "课程名称", position = 16)
    private String kcmc;

    @ApiModelProperty(value = "教室号", position = 17)
    private String jsh;

    @ApiModelProperty(value = "起始周", position = 18)
    private String startWeek;

    @ApiModelProperty(value = "终止周", position = 19)
    private String endWeek;

    @ApiModelProperty(value = "总学分", position = 20)
    private BigDecimal zxf;

    @ApiModelProperty(value = "总学时", position = 21)
    private BigDecimal zxs;

    @ApiModelProperty(value = "实验学时", position = 22)
    private Integer syxs;

    @ApiModelProperty(value = "上课星期", position = 23)
    private String skxq;

    @ApiModelProperty(value = "jcinfo", position = 24)
    private String jcinfo;

    @ApiModelProperty(value = "周次", position = 25)
    private String stimezc;

    @ApiModelProperty(value = "上课班级", position = 26)
    private String realSkbj;

    @ApiModelProperty(value = "课程类别", position = 27)
    private String kclb;

    @ApiModelProperty(value = "课程类别名称", position = 28)
    private String kclbName;

    @ApiModelProperty(value = "业务主键", position = 29)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 30)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 31)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 32)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 33)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 34)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 35)
    private String etlCheckDate;

}
