package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  科研项目信息传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("科研项目信息传输对象")
@JSONType(orders = { "etlJfuuid", "code", "name", "unitId", "unitCode", "unitName", "chargerCode", "chargerName", "projectTypeId", "projectTypeName", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "projectClassId", "projectClassName", "authorizeDate", "planEndDate", "actualEndDate", "projectStatusId", "projectStatusName", "checkStatusId", "checkStatusName", "projectLevelId", "projectLevelName", "attachFee", "startDate", "feeAuthorize", "contractType", "contractTypeName", "incomeFeeSum", "operateTime", "isSubProblem", "payoutSum", "outboundSum", "balanceSum", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsProjectInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "项目编号", position = 1)
    private String code;

    @ApiModelProperty(value = "项目名称", position = 2)
    private String name;

    @ApiModelProperty(value = "unit_id", position = 3)
    private String unitId;

    @ApiModelProperty(value = "单位代码", position = 4)
    private String unitCode;

    @ApiModelProperty(value = "单位名称", position = 5)
    private String unitName;

    @ApiModelProperty(value = "负责人工号", position = 6)
    private String chargerCode;

    @ApiModelProperty(value = "charger_name", position = 7)
    private String chargerName;

    @ApiModelProperty(value = "project_type_id", position = 8)
    private String projectTypeId;

    @ApiModelProperty(value = "项目类型名称", position = 9)
    private String projectTypeName;

    @ApiModelProperty(value = "subject_class_id", position = 10)
    private String subjectClassId;

    @ApiModelProperty(value = "科目分类名称", position = 11)
    private String subjectClassName;

    @ApiModelProperty(value = "学科代码", position = 12)
    private String subjectCode;

    @ApiModelProperty(value = "科目名称", position = 13)
    private String subjectName;

    @ApiModelProperty(value = "project_class_id", position = 14)
    private String projectClassId;

    @ApiModelProperty(value = "项目分类名称", position = 15)
    private String projectClassName;

    @ApiModelProperty(value = "authorize_date", position = 16)
    private String authorizeDate;

    @ApiModelProperty(value = "plan_end_date", position = 17)
    private String planEndDate;

    @ApiModelProperty(value = "actual_end_date", position = 18)
    private String actualEndDate;

    @ApiModelProperty(value = "project_status_id", position = 19)
    private String projectStatusId;

    @ApiModelProperty(value = "项目状态", position = 20)
    private String projectStatusName;

    @ApiModelProperty(value = "check_status_id", position = 21)
    private String checkStatusId;

    @ApiModelProperty(value = "考核状态", position = 22)
    private String checkStatusName;

    @ApiModelProperty(value = "project_level_id", position = 23)
    private String projectLevelId;

    @ApiModelProperty(value = "项目级别", position = 24)
    private String projectLevelName;

    @ApiModelProperty(value = "配套经费", position = 25)
    private BigDecimal attachFee;

    @ApiModelProperty(value = "start_date", position = 26)
    private String startDate;

    @ApiModelProperty(value = "项目经费/合同金额", position = 27)
    private BigDecimal feeAuthorize;

    @ApiModelProperty(value = "contract_type", position = 28)
    private String contractType;

    @ApiModelProperty(value = "合同类别名称", position = 29)
    private String contractTypeName;

    @ApiModelProperty(value = "总到账经费", position = 30)
    private BigDecimal incomeFeeSum;

    @ApiModelProperty(value = "operate_time", position = 31)
    private String operateTime;

    @ApiModelProperty(value = "is_sub_problem", position = 32)
    private String isSubProblem;

    @ApiModelProperty(value = "payout_sum", position = 33)
    private BigDecimal payoutSum;

    @ApiModelProperty(value = "outbound_sum", position = 34)
    private BigDecimal outboundSum;

    @ApiModelProperty(value = "余额", position = 35)
    private BigDecimal balanceSum;

    @ApiModelProperty(value = "主键", position = 36)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 37)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 38)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 39)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 40)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 41)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 42)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 43)
    private String etlCheckDate;

}
