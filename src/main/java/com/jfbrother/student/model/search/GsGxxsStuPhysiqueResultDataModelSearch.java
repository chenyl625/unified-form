package com.jfbrother.student.model.search;

import java.math.BigDecimal;
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
@ApiModel("学生体质测试成绩数据传输对象search")
@JSONType(orders = { "etlJfuuid", "rxnj", "ssbjM", "bjmc", "xjzt", "mzM", "xm", "xh", "xbM", "csrq", "semesterid", "height", "weight", "weightScore", "weightLevel", "vitalCapacityResult", "vitalCapacityScore", "vitalCapacityLevel", "fiftyMeterRunResult", "fiftyMeterRunScore", "fiftyMeterRunLevel", "standingLongJumpResult", "standingLongJumpScore", "standingLongJumpLevel", "sittingForwardFlexionResult", "sittingForwardFlexionScore", "sittingForwardFlexionLevel", "eightHundredMeterRunResult", "eightHundredMeterRunScore", "eightHundredMeterRunLevel", "oneThousandMeterRunResult", "oneThousandMeterRunScore", "oneThousandMeterRunLevel", "abdominalCurlResult", "abdominalCurlResultScore", "abdominalCurlLevel", "pullUpResult", "pullUpScore", "pullUpLevel", "lyslzy", "lyslyy", "qgbzzy", "qgbzyy", "cjzy", "cjyy", "standardScore", "plus1", "plus2", "finalscore", "totalLevel", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuPhysiqueResultDataModelSearch extends GsGxxsStuPhysiqueResultDataModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
