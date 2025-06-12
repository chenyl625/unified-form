package com.jfbrother.dataCenter.model.response;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师信息-响应模型
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ApiModel("教师信息-响应模型")
public class TeacherInfoResponseModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "姓名", position = 2)
    private String xm;

    @ApiModelProperty(value = "曾用名", position = 3)
    private String cym;

    @ApiModelProperty(value = "拼音简码", position = 4)
    private String pinyin;

    @ApiModelProperty(value = "性别", position = 5)
    private String sex;

    @ApiModelProperty(value = "出生日期", position = 6)
    private String csrq;

    @ApiModelProperty(value = "出生地", position = 7)
    private String birthPlace;

    @ApiModelProperty(value = "籍贯", position = 8)
    private String jg;

    @ApiModelProperty(value = "民族", position = 9)
    private String nationName;

    @ApiModelProperty(value = "国籍", position = 10)
    private String gj;

    @ApiModelProperty(value = "身份证类别", position = 11)
    private String sfzType;

    @ApiModelProperty(value = "证件号码", position = 12)
    private String sfzh;

    @ApiModelProperty(value = "婚姻状况", position = 13)
    private String hyzk;

    @ApiModelProperty(value = "最高学历", position = 14)
    private String topEdu;

    @ApiModelProperty(value = "政治面貌", position = 15)
    private String zzmmName;

    @ApiModelProperty(value = "所在学院及现任职务", position = 16)
    private String deptPosition;

    @ApiModelProperty(value = "性别代码", position = 5)
    private String xb;

    @ApiModelProperty(value = "所在部门", position = 5)
    private String deptName;

    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
