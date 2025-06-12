package com.jfbrother.digitalPortraitSnap.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  班级基本信息传输对象
 * @author xinz 2023-04-03
 */
@Data
@ApiModel("班级基本信息传输对象")
@JSONType(orders = { "nj", "id", "bjmc", "xz", "bjdm", "majorDm", "xyDm", "etlPri", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate", "zyId"})
public class GsGxxsClassInfoModel {
    @ApiModelProperty(value = "年级", position = 0)
    private String nj;

    @ApiModelProperty(value = "班级id-bjdm", position = 1)
    private String id;

    @ApiModelProperty(value = "班级名称", position = 2)
    private String bjmc;

    @ApiModelProperty(value = "学制", position = 3)
    private String xz;

    @ApiModelProperty(value = "班级代码user_dm", position = 4)
    private String bjdm;

    @ApiModelProperty(value = "所属专业id", position = 5)
    private String majorDm;

    @ApiModelProperty(value = "所属学院id", position = 6)
    private String xyDm;

    @ApiModelProperty(value = "业务主键", position = 7)
    private String etlPri;

    @ApiModelProperty(value = "校验唯一性", position = 8)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 9)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 10)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 11)
    private String etlCheckDate;

    @ApiModelProperty(value = "专业id", position = 12)
    private String zyId;
}