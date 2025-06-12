package com.jfbrother.digitalPortraitSnap.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室卫生检查传输对象
 * @author xinz 2023-03-10
 */
@Data
@ApiModel("学生寝室卫生检查传输对象")
@JSONType(orders = { "etlJfuuid", "bedroomId", "score", "bedroomName", "bedroomLou", "bedroomFloor", "bedroomArea", "userTypeName", "danger", "remark", "checkDate", "checkUser", "checkUserCode", "levelType", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuDormHealthCheckModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "寝室id", position = 1)
    private String bedroomId;

    @ApiModelProperty(value = "分值", position = 2)
    private Integer score;

    @ApiModelProperty(value = "寝室名称", position = 3)
    private String bedroomName;

    @ApiModelProperty(value = "寝室楼号", position = 4)
    private String bedroomLou;

    @ApiModelProperty(value = "寝室楼层", position = 5)
    private String bedroomFloor;

    @ApiModelProperty(value = "寝室区域", position = 6)
    private String bedroomArea;

    @ApiModelProperty(value = "检查人员类型名称", position = 7)
    private String userTypeName;

    @ApiModelProperty(value = "有无违禁品", position = 8)
    private String danger;

    @ApiModelProperty(value = "备注信息", position = 9)
    private String remark;

    @ApiModelProperty(value = "检查日期", position = 10)
    private String checkDate;

    @ApiModelProperty(value = "检查人员姓名", position = 11)
    private String checkUser;

    @ApiModelProperty(value = "检查人员工号", position = 12)
    private String checkUserCode;

    @ApiModelProperty(value = "等级", position = 13)
    private String levelType;

    @ApiModelProperty(value = "业务主键", position = 14)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 15)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 16)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 17)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 18)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 19)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 20)
    private String etlCheckDate;

}
