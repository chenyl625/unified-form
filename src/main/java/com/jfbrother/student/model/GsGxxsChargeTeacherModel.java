package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 在校班级的班主任传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-31
 */
@Data
@ApiModel("在校班级的班主任传输对象")
@JSONType(orders = {"etlJfuuid", "id", "bjdm", "bjmc", "bzrgh", "bzrxm", "fdyh", "fdyxm", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsChargeTeacherModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "班级id", position = 1)
    private String id;

    @ApiModelProperty(value = "班级代码", position = 2)
    private String bjdm;

    @ApiModelProperty(value = "班级名称", position = 3)
    private String bjmc;

    @ApiModelProperty(value = "班主任工号", position = 4)
    private String bzrgh;

    @ApiModelProperty(value = "班主任姓名", position = 5)
    private String bzrxm;

    @ApiModelProperty(value = "辅导员工号", position = 6)
    private String fdyh;

    @ApiModelProperty(value = "辅导员姓名", position = 7)
    private String fdyxm;

    @ApiModelProperty(value = "业务主键", position = 8)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 9)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 10)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 11)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 12)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 13)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 14)
    private String etlCheckDate;

}
