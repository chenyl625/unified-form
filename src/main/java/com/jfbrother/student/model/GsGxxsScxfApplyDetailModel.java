package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  三创学分申请详情传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("三创学分申请详情传输对象")
@JSONType(orders = { "etlJfuuid", "applyUserXgh", "applyTime", "score", "way", "status", "applyDescribe", "xm", "termName", "xn", "projectName", "cognizanceRequire", "applyStatus", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsScxfApplyDetailModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "申请人", position = 1)
    private String applyUserXgh;

    @ApiModelProperty(value = "申请时间", position = 2)
    private String applyTime;

    @ApiModelProperty(value = "学分", position = 3)
    private String score;

    @ApiModelProperty(value = "申请方式", position = 4)
    private String way;

    @ApiModelProperty(value = "状态", position = 5)
    private String status;

    @ApiModelProperty(value = "申请描述", position = 6)
    private String applyDescribe;

    @ApiModelProperty(value = "姓名", position = 7)
    private String xm;

    @ApiModelProperty(value = "学期名称", position = 8)
    private String termName;

    @ApiModelProperty(value = "学年", position = 9)
    private String xn;

    @ApiModelProperty(value = "项目名称", position = 10)
    private String projectName;

    @ApiModelProperty(value = "认定要求", position = 11)
    private String cognizanceRequire;

    @ApiModelProperty(value = "认定状态(0:待认定 1:不通过 2:通过)", position = 12)
    private Integer applyStatus;

    @ApiModelProperty(value = "主键", position = 13)
    private String id;

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
