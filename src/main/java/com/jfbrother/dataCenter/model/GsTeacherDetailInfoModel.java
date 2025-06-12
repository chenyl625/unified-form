package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师详细信息传输对象
* @author chenyl@jfbrother.com 2022-07-19
*/
@Data
@ApiModel("教师详细信息传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "nowJobPositionDm", "gwLevel", "zyjszwDm", "zgxlDm", "zzmmDm", "zgxwDm", "zgxlName", "enterSchoolTime", "zyzgLevel", "gwTypeDm", "graduateSchool", "type", "positionTypeCode", "positionTypeName", "bmh", "nowPosition", "peopleSortCode", "peopleSort", "zyjszwAcquireDate", "bankCard", "busBankCard", "yglbDm", "yglbName", "teachDeptCode", "constructionBankCard", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherDetailInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "现任职务", position = 2)
    private String nowJobPositionDm;

    @ApiModelProperty(value = "岗位等级", position = 3)
    private String gwLevel;

    @ApiModelProperty(value = "专业技术职务", position = 4)
    private String zyjszwDm;

    @ApiModelProperty(value = "最高学历代码", position = 5)
    private String zgxlDm;

    @ApiModelProperty(value = "政治面貌", position = 6)
    private String zzmmDm;

    @ApiModelProperty(value = "最高学位代码", position = 7)
    private String zgxwDm;

    @ApiModelProperty(value = "最高学历名称", position = 8)
    private String zgxlName;

    @ApiModelProperty(value = "进校时间", position = 9)
    private String enterSchoolTime;

    @ApiModelProperty(value = "职业资格等级", position = 10)
    private String zyzgLevel;

    @ApiModelProperty(value = "岗位类别", position = 11)
    private String gwTypeDm;

    @ApiModelProperty(value = "毕业学校", position = 12)
    private String graduateSchool;

    @ApiModelProperty(value = "类型", position = 13)
    private String type;

    @ApiModelProperty(value = "教职工类别编码", position = 14)
    private String positionTypeCode;

    @ApiModelProperty(value = "教职工类别名称", position = 15)
    private String positionTypeName;

    @ApiModelProperty(value = "部门号", position = 16)
    private String bmh;

    @ApiModelProperty(value = "现任职务", position = 17)
    private String nowPosition;

    @ApiModelProperty(value = "人员划分代码", position = 18)
    private String peopleSortCode;

    @ApiModelProperty(value = "人员划分", position = 19)
    private String peopleSort;

    @ApiModelProperty(value = "专业技术职务获取时间", position = 20)
    private String zyjszwAcquireDate;

    @ApiModelProperty(value = "工行银行卡号", position = 21)
    private String bankCard;

    @ApiModelProperty(value = "公务卡号", position = 22)
    private String busBankCard;

    @ApiModelProperty(value = "用工类别", position = 23)
    private String yglbDm;

    @ApiModelProperty(value = "用工类别名称", position = 24)
    private String yglbName;

    @ApiModelProperty(value = "教学归属部门编码", position = 25)
    private String teachDeptCode;

    @ApiModelProperty(value = "建设银行卡", position = 26)
    private String constructionBankCard;

    @ApiModelProperty(value = "主键", position = 27)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 28)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 29)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 30)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 31)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 32)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 33)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 34)
    private String etlCheckDate;

}
