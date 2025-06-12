package com.jfbrother.digitalPortraitSnap.model;


import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生-家长绑定历史信息传输对象
 * @author xinz 2023-03-21
 */
@Data
@ApiModel("学生-家长绑定历史信息传输对象")
@JSONType(orders = { "id", "stuCode", "parentName", "parentPhone", "relation", "openId", "deleteFlag", "remark", "createTime", "updateTime"})
public class GsStuParentsBindHistoryModel {
    @ApiModelProperty(value = "流水号", position = 0)
    private String id;

    @ApiModelProperty(value = "学生学号", position = 1)
    private String stuCode;

    @ApiModelProperty(value = "家长姓名", position = 2)
    private String parentName;

    @ApiModelProperty(value = "家长手机号", position = 3)
    private String parentPhone;

    @ApiModelProperty(value = "与该学生关系", position = 4)
    private String relation;

    @ApiModelProperty(value = "微信唯一验证码", position = 5)
    private String openId;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 6)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 7)
    private String remark;

    @ApiModelProperty(value = "创建时间", position = 8)
    private String createTime;

    @ApiModelProperty(value = "修改时间", position = 9)
    private String updateTime;

    @ApiModelProperty(value = "绑定用户方式，0-游客绑定 1-已有绑定", position = 10)
    private String bindType;

}