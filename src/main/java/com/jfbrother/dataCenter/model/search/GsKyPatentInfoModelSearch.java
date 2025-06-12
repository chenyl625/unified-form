package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsKyPatentInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  科研专利成果信息传输对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Data
@ApiModel("科研专利成果信息传输对象search")
@JSONType(orders = { "etlJfuuid", "name", "unitId", "unitCode", "unitName", "patentTypeId", "patentTypeName", "patentScopeId", "patentScopeName", "patentStateId", "patentStateName", "applyDate", "applyCode", "openDate", "authorizeDate", "authorizeCode", "authorNumber", "isDutyPatent", "isDutyPatentName", "firstAuthorName", "firstAuthorCode", "isPct", "members", "checkStatusId", "checkStatusName", "operateTime", "cooperationType", "cooperationTypeName", "applyInfo", "patentee", "invalidFlag", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPatentInfoModelSearch extends GsKyPatentInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
