package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsProjectInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  科研项目信息传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("科研项目信息传输对象search")
@JSONType(orders = { "etlJfuuid", "code", "name", "unitId", "unitCode", "unitName", "chargerCode", "chargerName", "projectTypeId", "projectTypeName", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "projectClassId", "projectClassName", "authorizeDate", "planEndDate", "actualEndDate", "projectStatusId", "projectStatusName", "checkStatusId", "checkStatusName", "projectLevelId", "projectLevelName", "attachFee", "startDate", "feeAuthorize", "contractType", "contractTypeName", "incomeFeeSum", "operateTime", "isSubProblem", "payoutSum", "outboundSum", "balanceSum", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsProjectInfoModelSearch extends GsProjectInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
