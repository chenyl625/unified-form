package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuPhysiqueResultDataModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生体质测试成绩数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生体质测试成绩数据传输对象extend")
@JSONType(orders = { "etlJfuuid", "rxnj", "ssbjM", "bjmc", "xjzt", "mzM", "xm", "xh", "xbM", "csrq", "semesterid", "height", "weight", "weightScore", "weightLevel", "vitalCapacityResult", "vitalCapacityScore", "vitalCapacityLevel", "fiftyMeterRunResult", "fiftyMeterRunScore", "fiftyMeterRunLevel", "standingLongJumpResult", "standingLongJumpScore", "standingLongJumpLevel", "sittingForwardFlexionResult", "sittingForwardFlexionScore", "sittingForwardFlexionLevel", "eightHundredMeterRunResult", "eightHundredMeterRunScore", "eightHundredMeterRunLevel", "oneThousandMeterRunResult", "oneThousandMeterRunScore", "oneThousandMeterRunLevel", "abdominalCurlResult", "abdominalCurlResultScore", "abdominalCurlLevel", "pullUpResult", "pullUpScore", "pullUpLevel", "lyslzy", "lyslyy", "qgbzzy", "qgbzyy", "cjzy", "cjyy", "standardScore", "plus1", "plus2", "finalscore", "totalLevel", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuPhysiqueResultDataModelExtend extends GsGxxsStuPhysiqueResultDataModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
