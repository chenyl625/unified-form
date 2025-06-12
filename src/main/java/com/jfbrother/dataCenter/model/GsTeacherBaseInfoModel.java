package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师基本信息传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@Data
@ApiModel("教师基本信息传输对象")
@JSONType(orders = {"etlJfuuid", "xm", "gh", "cym", "xb", "xrzwDm", "csrq", "sfzh", "mzDm", "sj", "isTeach", "isDoubleTeacher", "bmh", "type", "email", "zzmmDm", "phoneNum", "officePhone", "unicomShortPhone", "unicomPhone", "mobileShortPhone", "mobilePhone", "peopleSort", "sortNum", "jg", "xyzjm", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherBaseInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "姓名", position = 1)
    private String xm;

    @ApiModelProperty(value = "工号", position = 2)
    private String gh;

    @ApiModelProperty(value = "曾用名", position = 3)
    private String cym;

    @ApiModelProperty(value = "性别", position = 4)
    private String xb;

    @ApiModelProperty(value = "现任职务", position = 5)
    private String xrzwDm;

    @ApiModelProperty(value = "出生日期", position = 6)
    private String csrq;

    @ApiModelProperty(value = "证件号码", position = 7)
    private String sfzh;

    @ApiModelProperty(value = "民族", position = 8)
    private String mzDm;

    @ApiModelProperty(value = "手机号", position = 9)
    private String sj;

    @ApiModelProperty(value = "是否授课", position = 10)
    private String isTeach;

    @ApiModelProperty(value = "是否双师型教师", position = 11)
    private Double isDoubleTeacher;

    @ApiModelProperty(value = "部门号", position = 12)
    private String bmh;

    @ApiModelProperty(value = "教师类型", position = 13)
    private String type;

    @ApiModelProperty(value = "电子邮箱", position = 14)
    private String email;

    @ApiModelProperty(value = "政治面貌代码", position = 15)
    private String zzmmDm;

    @ApiModelProperty(value = "手机号", position = 16)
    private String phoneNum;

    @ApiModelProperty(value = "办公电话", position = 17)
    private String officePhone;

    @ApiModelProperty(value = "联通短号", position = 18)
    private String unicomShortPhone;

    @ApiModelProperty(value = "联通长号", position = 19)
    private String unicomPhone;

    @ApiModelProperty(value = "移动短号", position = 20)
    private String mobileShortPhone;

    @ApiModelProperty(value = "移动长号", position = 21)
    private String mobilePhone;

    @ApiModelProperty(value = "人员分类", position = 22)
    private String peopleSort;

    @ApiModelProperty(value = "排序号", position = 23)
    private Integer sortNum;

    @ApiModelProperty(value = "籍贯", position = 24)
    private String jg;

    @ApiModelProperty(value = "宗教信仰", position = 25)
    private String xyzjm;

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
