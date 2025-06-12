package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师个人通讯方式传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("教师个人通讯方式传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "email", "bgPhone", "telephone", "ltLong", "ltShort", "ydLong", "ydShort", "qq", "address", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherPersonCommModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "电子邮箱", position = 3)
    private String email;

    @ApiModelProperty(value = "办公电话", position = 4)
    private String bgPhone;

    @ApiModelProperty(value = "手机号", position = 5)
    private String telephone;

    @ApiModelProperty(value = "联通长号", position = 6)
    private String ltLong;

    @ApiModelProperty(value = "联通短号", position = 7)
    private String ltShort;

    @ApiModelProperty(value = "移动长号", position = 8)
    private String ydLong;

    @ApiModelProperty(value = "移动短号", position = 9)
    private String ydShort;

    @ApiModelProperty(value = "qq", position = 10)
    private String qq;

    @ApiModelProperty(value = "联系地址", position = 11)
    private String address;

    @ApiModelProperty(value = "主键", position = 12)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 13)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 14)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 15)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 16)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 17)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 18)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 19)
    private String etlCheckDate;

}
