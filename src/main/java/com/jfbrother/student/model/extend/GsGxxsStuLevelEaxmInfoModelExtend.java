package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuLevelEaxmInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  学生等级考试情况传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生等级考试情况传输对象extend")
@JSONType(orders = { "etlJfuuid", "nj", "xh", "userXh", "xm", "sex", "sfzh", "bjdm", "bjmc", "yxId", "college", "zyId", "major", "lbId", "djId", "shortName", "score", "compreScore", "signTime", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuLevelEaxmInfoModelExtend extends GsGxxsStuLevelEaxmInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
