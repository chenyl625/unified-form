package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsKyPatentInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  科研专利成果信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("科研专利成果信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "name", "unitId", "unitCode", "unitName", "patentTypeId", "patentTypeName", "patentScopeId", "patentScopeName", "patentStateId", "patentStateName", "applyDate", "applyCode", "openDate", "authorizeDate", "authorizeCode", "authorNumber", "isDutyPatent", "isDutyPatentName", "firstAuthorName", "firstAuthorCode", "isPct", "members", "checkStatusId", "checkStatusName", "operateTime", "cooperationType", "cooperationTypeName", "applyInfo", "patentee", "invalidFlag", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPatentInfoModelExtend extends GsKyPatentInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
