package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  学评教-学生评价老师汇总表传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("学评教-学生评价老师汇总表传输对象")
@JSONType(orders = { "etlJfuuid", "xn", "xq", "jsid", "totalScore", "gh", "kcmc", "kcid", "cprs", "jsxm", "avgPgdf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuEvaluateTeacherModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学年", position = 1)
    private String xn;

    @ApiModelProperty(value = "学期", position = 2)
    private String xq;

    @ApiModelProperty(value = "教师id", position = 3)
    private String jsid;

    @ApiModelProperty(value = "综合得分", position = 4)
    private BigDecimal totalScore;

    @ApiModelProperty(value = "教职工号", position = 5)
    private String gh;

    @ApiModelProperty(value = "课程名称", position = 6)
    private String kcmc;

    @ApiModelProperty(value = "课程id", position = 7)
    private String kcid;

    @ApiModelProperty(value = "参加评定人数", position = 8)
    private String cprs;

    @ApiModelProperty(value = "教师姓名", position = 9)
    private String jsxm;

    @ApiModelProperty(value = "平均评估得分", position = 10)
    private BigDecimal avgPgdf;

    @ApiModelProperty(value = "业务主键", position = 11)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 12)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 13)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 14)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 15)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 16)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 17)
    private String etlCheckDate;

}
