package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师学历学位传输对象
* @author chenyl@jfbrother.com 2022-08-09
*/
@Data
@ApiModel("教师学历学位传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "eduTopCode", "eduTopName", "degreeTopCode", "degreeTopName", "eduCode", "eduName", "eduCountryCode", "eduCountryName", "eduCollege", "eduMajor", "startTime", "endTime", "degreeCode", "degreeName", "degreeCountryCode", "degreeCountryName", "degreeCollege", "degreeTime", "styleCode", "styleName", "unitType", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherEduDegreeModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "最高学历", position = 3)
    private String eduTopCode;

    @ApiModelProperty(value = "最高学历名称", position = 4)
    private String eduTopName;

    @ApiModelProperty(value = "最高学位", position = 5)
    private String degreeTopCode;

    @ApiModelProperty(value = "最高学位名称", position = 6)
    private String degreeTopName;

    @ApiModelProperty(value = "获得学历", position = 7)
    private String eduCode;

    @ApiModelProperty(value = "获得学历名称", position = 8)
    private String eduName;

    @ApiModelProperty(value = "获得学历的国家（地区）", position = 9)
    private String eduCountryCode;

    @ApiModelProperty(value = "获得学历的国家（地区）名称", position = 10)
    private String eduCountryName;

    @ApiModelProperty(value = "获得学历的院校或机构", position = 11)
    private String eduCollege;

    @ApiModelProperty(value = "所学专业", position = 12)
    private String eduMajor;

    @ApiModelProperty(value = "入学时间", position = 13)
    private String startTime;

    @ApiModelProperty(value = "毕业时间", position = 14)
    private String endTime;

    @ApiModelProperty(value = "学位编码", position = 15)
    private String degreeCode;

    @ApiModelProperty(value = "学位名称", position = 16)
    private String degreeName;

    @ApiModelProperty(value = "获得学位的国家／地区", position = 17)
    private String degreeCountryCode;

    @ApiModelProperty(value = "获得学位的国家／地区名称", position = 18)
    private String degreeCountryName;

    @ApiModelProperty(value = "获得学位的单位名称", position = 19)
    private String degreeCollege;

    @ApiModelProperty(value = "获学位日期", position = 20)
    private String degreeTime;

    @ApiModelProperty(value = "学习方式1", position = 21)
    private String styleCode;

    @ApiModelProperty(value = "学习方式名称", position = 22)
    private String styleName;

    @ApiModelProperty(value = "在学单位类别", position = 23)
    private String unitType;

    @ApiModelProperty(value = "主键", position = 24)
    private String id;

    @ApiModelProperty(value = "主键序号", position = 25)
    private Integer idSort;

    @ApiModelProperty(value = "业务主键", position = 26)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 27)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 28)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 29)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 30)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 31)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 32)
    private String etlCheckDate;

}
