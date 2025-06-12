package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师政治面貌传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("教师政治面貌传输对象")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "zzmmm", "zzmmName", "joinDate", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherZzmmModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "政治面貌", position = 3)
    private String zzmmm;

    @ApiModelProperty(value = "政治面貌名称", position = 4)
    private String zzmmName;

    @ApiModelProperty(value = "参加党派日期", position = 5)
    private String joinDate;

    @ApiModelProperty(value = "主键", position = 6)
    private String id;

    @ApiModelProperty(value = "主键序号", position = 7)
    private Integer idSort;

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
