package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
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
@ApiModel("三创学分申请详情传输对象extend")
@JSONType(orders = { "etlJfuuid", "applyUserXgh", "applyTime", "score", "way", "status", "applyDescribe", "xm", "termName", "xn", "projectName", "cognizanceRequire", "applyStatus", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsScxfApplyDetailModelExtend extends GsGxxsScxfApplyDetailModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
