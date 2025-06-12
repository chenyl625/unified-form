package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  教师全部信息传输对象
* @author chenyl@jfbrother.com 2022-07-19
*/
@Data
@ApiModel("教师全部信息传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "name", "cym", "pinyin", "sex", "gj", "sfzType", "sfzh", "birth", "age", "jg", "birthPlace", "nowHj", "nationName", "topEdu", "topDegree", "zzmmName", "hyzk", "belief", "byxx", "byzy", "isDouble", "deptName", "partyTime", "oficalPhone", "telephone", "email", "address", "workTime", "schoolTime", "coefficient", "conStartTime", "conEndTime", "employTime", "positionType", "positionLevel", "conStatus", "teaSource", "teaType", "employType", "techPosition", "curPosition", "schoolEdu", "schoolDegree", "isQuali", "isTeach", "teachType", "isOverStudy", "unicomPhone", "unicomShortPhone", "mobilePhone", "mobileShortPhone", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherAllInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "姓名", position = 3)
    private String name;

    @ApiModelProperty(value = "曾用名", position = 4)
    private String cym;

    @ApiModelProperty(value = "拼音简码", position = 5)
    private String pinyin;

    @ApiModelProperty(value = "性别", position = 6)
    private String sex;

    @ApiModelProperty(value = "国籍", position = 7)
    private String gj;

    @ApiModelProperty(value = "身份证类别", position = 8)
    private String sfzType;

    @ApiModelProperty(value = "证件号码", position = 9)
    private String sfzh;

    @ApiModelProperty(value = "出生日期", position = 10)
    private String birth;

    @ApiModelProperty(value = "年龄", position = 11)
    private Integer age;

    @ApiModelProperty(value = "籍贯", position = 12)
    private String jg;

    @ApiModelProperty(value = "出生地", position = 13)
    private String birthPlace;

    @ApiModelProperty(value = "现户籍", position = 14)
    private String nowHj;

    @ApiModelProperty(value = "民族", position = 15)
    private String nationName;

    @ApiModelProperty(value = "最高学历", position = 16)
    private String topEdu;

    @ApiModelProperty(value = "最高学位", position = 17)
    private String topDegree;

    @ApiModelProperty(value = "政治面貌", position = 18)
    private String zzmmName;

    @ApiModelProperty(value = "婚姻状况", position = 19)
    private String hyzk;

    @ApiModelProperty(value = "宗教信仰", position = 20)
    private String belief;

    @ApiModelProperty(value = "毕业学校", position = 21)
    private String byxx;

    @ApiModelProperty(value = "毕业专业", position = 22)
    private String byzy;

    @ApiModelProperty(value = "是否双师型教师", position = 23)
    private String isDouble;

    @ApiModelProperty(value = "所属部门", position = 24)
    private String deptName;

    @ApiModelProperty(value = "中共党员填写入党时间", position = 25)
    private String partyTime;

    @ApiModelProperty(value = "办公电话", position = 26)
    private String oficalPhone;

    @ApiModelProperty(value = "手机号", position = 27)
    private String telephone;

    @ApiModelProperty(value = "电子邮箱", position = 28)
    private String email;

    @ApiModelProperty(value = "联系地址", position = 29)
    private String address;

    @ApiModelProperty(value = "参加工作时间", position = 30)
    private String workTime;

    @ApiModelProperty(value = "进校时间", position = 31)
    private String schoolTime;

    @ApiModelProperty(value = "系数", position = 32)
    private BigDecimal coefficient;

    @ApiModelProperty(value = "合同起始时间", position = 33)
    private String conStartTime;

    @ApiModelProperty(value = "合同终止时间", position = 34)
    private String conEndTime;

    @ApiModelProperty(value = "聘任时间", position = 35)
    private String employTime;

    @ApiModelProperty(value = "岗位类别", position = 36)
    private String positionType;

    @ApiModelProperty(value = "岗位等级", position = 37)
    private String positionLevel;

    @ApiModelProperty(value = "签订合同情况", position = 38)
    private String conStatus;

    @ApiModelProperty(value = "教职工来源", position = 39)
    private String teaSource;

    @ApiModelProperty(value = "教职工类别", position = 40)
    private String teaType;

    @ApiModelProperty(value = "用工类别", position = 41)
    private String employType;

    @ApiModelProperty(value = "专业技术职务", position = 42)
    private String techPosition;

    @ApiModelProperty(value = "现任职务", position = 43)
    private String curPosition;

    @ApiModelProperty(value = "进校时的学历", position = 44)
    private String schoolEdu;

    @ApiModelProperty(value = "进校时的学位", position = 45)
    private String schoolDegree;

    @ApiModelProperty(value = "是否有职业资格", position = 46)
    private String isQuali;

    @ApiModelProperty(value = "是否授课", position = 47)
    private String isTeach;

    @ApiModelProperty(value = "授课类别", position = 48)
    private String teachType;

    @ApiModelProperty(value = "是否有海外留学经历", position = 49)
    private String isOverStudy;

    @ApiModelProperty(value = "联通长号", position = 50)
    private String unicomPhone;

    @ApiModelProperty(value = "联通短号", position = 51)
    private String unicomShortPhone;

    @ApiModelProperty(value = "移动手机长号", position = 52)
    private String mobilePhone;

    @ApiModelProperty(value = "移动短号", position = 53)
    private String mobileShortPhone;

    @ApiModelProperty(value = "主键", position = 54)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 55)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 56)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 57)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 58)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 59)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 60)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 61)
    private String etlCheckDate;

}
