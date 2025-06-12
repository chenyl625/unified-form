package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  人事中历史所有部门信息传输对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Data
@ApiModel("人事中历史所有部门信息传输对象")
@JSONType(orders = { "etlJfuuid", "bmdm", "bmmc", "sjbmdm", "fzr", "sortNum", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxjgAllDeptModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "部门代码", position = 1)
    private String bmdm;

    @ApiModelProperty(value = "部门名称", position = 2)
    private String bmmc;

    @ApiModelProperty(value = "上级部门代码", position = 3)
    private String sjbmdm;

    @ApiModelProperty(value = "负责人", position = 4)
    private String fzr;

    @ApiModelProperty(value = "排序号", position = 5)
    private Integer sortNum;

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
