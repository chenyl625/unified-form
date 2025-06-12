package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsTeacherTeachQualityEvalInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  教师维度的教学质量评价信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师维度的教学质量评价信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "scheduleDetailId", "zblx", "jscd", "jszt", "ydrs", "sdrs", "cdrs", "ztrs", "jxtd", "jxjy", "ktwt", "jxnr", "qtwt", "pjjs", "pjsj", "sksj", "tkjc", "weeks", "detailDeleted", "scheduleId", "weekDay", "nodes", "skjs", "dd", "termId", "kclb1", "kc", "jc", "skbj", "zc", "xf", "kclb2", "scheDeleted", "cddw", "skrs", "zxs", "schoolYear", "semester", "listenTypeId", "listenTypeName", "orderNo", "color", "js", "kcbh", "wtfk", "wtfkzt", "zypjb", "skjsXm", "jxdf", "xqdf", "readed", "xqpjb", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherTeachQualityEvalInfoModelExtend extends GsTeacherTeachQualityEvalInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
