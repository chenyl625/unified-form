package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教职工年底考核传输对象
* @author chenyl@jfbrother.com 2022-07-21
*/
@Data
@ApiModel("教职工年底考核传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "resultType", "xn", "resultName", "typeConvertValue", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherYearKhModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "考核结果类型", position = 2)
    private String resultType;

    @ApiModelProperty(value = "学年", position = 3)
    private String xn;

    @ApiModelProperty(value = "结果类型名称", position = 4)
    private String resultName;

    @ApiModelProperty(value = "类型转换分数", position = 5)
    private Integer typeConvertValue;

    @ApiModelProperty(value = "业务主键", position = 6)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 7)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 8)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 9)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 10)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 11)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 12)
    private String etlCheckDate;

}
