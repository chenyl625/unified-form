package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsScxfApplyDetailModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  三创学分申请详情传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("三创学分申请详情传输对象search")
@JSONType(orders = { "etlJfuuid", "applyUserXgh", "applyTime", "score", "way", "status", "applyDescribe", "xm", "termName", "xn", "projectName", "cognizanceRequire", "applyStatus", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsScxfApplyDetailModelSearch extends GsGxxsScxfApplyDetailModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
