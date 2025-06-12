package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  科研专利成果信息传输对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Data
@ApiModel("科研专利成果信息传输对象")
@JSONType(orders = { "etlJfuuid", "name", "unitId", "unitCode", "unitName", "patentTypeId", "patentTypeName", "patentScopeId", "patentScopeName", "patentStateId", "patentStateName", "applyDate", "applyCode", "openDate", "authorizeDate", "authorizeCode", "authorNumber", "isDutyPatent", "isDutyPatentName", "firstAuthorName", "firstAuthorCode", "isPct", "members", "checkStatusId", "checkStatusName", "operateTime", "cooperationType", "cooperationTypeName", "applyInfo", "patentee", "invalidFlag", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPatentInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "专利名称", position = 1)
    private String name;

    @ApiModelProperty(value = "unit_id", position = 2)
    private String unitId;

    @ApiModelProperty(value = "单位代码", position = 3)
    private String unitCode;

    @ApiModelProperty(value = "单位名称", position = 4)
    private String unitName;

    @ApiModelProperty(value = "patent_type_id", position = 5)
    private String patentTypeId;

    @ApiModelProperty(value = "专利类型", position = 6)
    private String patentTypeName;

    @ApiModelProperty(value = "patent_scope_id", position = 7)
    private String patentScopeId;

    @ApiModelProperty(value = "专利范围", position = 8)
    private String patentScopeName;

    @ApiModelProperty(value = "patent_state_id", position = 9)
    private String patentStateId;

    @ApiModelProperty(value = "专利状态", position = 10)
    private String patentStateName;

    @ApiModelProperty(value = "申请日期", position = 11)
    private String applyDate;

    @ApiModelProperty(value = "专利号", position = 12)
    private String applyCode;

    @ApiModelProperty(value = "公开日期", position = 13)
    private String openDate;

    @ApiModelProperty(value = "授权日期", position = 14)
    private String authorizeDate;

    @ApiModelProperty(value = "authorize_code", position = 15)
    private String authorizeCode;

    @ApiModelProperty(value = "author_number", position = 16)
    private String authorNumber;

    @ApiModelProperty(value = "is_duty_patent", position = 17)
    private String isDutyPatent;

    @ApiModelProperty(value = "是否为职务专利", position = 18)
    private String isDutyPatentName;

    @ApiModelProperty(value = "first_author_name", position = 19)
    private String firstAuthorName;

    @ApiModelProperty(value = "first_author_code", position = 20)
    private String firstAuthorCode;

    @ApiModelProperty(value = "是否为pct专利", position = 21)
    private String isPct;

    @ApiModelProperty(value = "专利发明（设计）人", position = 22)
    private String members;

    @ApiModelProperty(value = "check_status_id", position = 23)
    private String checkStatusId;

    @ApiModelProperty(value = "审核名称", position = 24)
    private String checkStatusName;

    @ApiModelProperty(value = "operate_time", position = 25)
    private String operateTime;

    @ApiModelProperty(value = "cooperation_type", position = 26)
    private String cooperationType;

    @ApiModelProperty(value = "合作类型", position = 27)
    private String cooperationTypeName;

    @ApiModelProperty(value = "申请人", position = 28)
    private String applyInfo;

    @ApiModelProperty(value = "专利权人", position = 29)
    private String patentee;

    @ApiModelProperty(value = "是否失效", position = 30)
    private String invalidFlag;

    @ApiModelProperty(value = "主键", position = 31)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 32)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 33)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 34)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 35)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 36)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 37)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 38)
    private String etlCheckDate;

}
