package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuXjChangeModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  学生学籍异动最新记录传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生学籍异动最新记录传输对象extend")
@JSONType(orders = { "etlJfuuid", "xn", "xh", "userXh", "changeTypeCode", "changeTypeName", "changeTime", "changeReasonCode", "confirmTime", "changeNumber", "changeExplain", "oldnj", "oldyx", "oldzy", "oldbj", "yddnj", "yddyx", "yddzy", "yddbj", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuXjChangeModelExtend extends GsGxxsStuXjChangeModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
