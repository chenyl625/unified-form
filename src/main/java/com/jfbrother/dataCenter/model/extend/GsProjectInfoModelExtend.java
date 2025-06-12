package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsProjectInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  科研项目信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("科研项目信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "code", "name", "unitId", "unitCode", "unitName", "chargerCode", "chargerName", "projectTypeId", "projectTypeName", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "projectClassId", "projectClassName", "authorizeDate", "planEndDate", "actualEndDate", "projectStatusId", "projectStatusName", "checkStatusId", "checkStatusName", "projectLevelId", "projectLevelName", "attachFee", "startDate", "feeAuthorize", "contractType", "contractTypeName", "incomeFeeSum", "operateTime", "isSubProblem", "payoutSum", "outboundSum", "balanceSum", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsProjectInfoModelExtend extends GsProjectInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
