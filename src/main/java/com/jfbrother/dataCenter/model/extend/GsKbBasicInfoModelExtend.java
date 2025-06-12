package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsKbBasicInfoModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  教师排课基本信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师排课基本信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "kkxn", "kkxq", "kch", "analyseZc", "dsz", "yxDm", "sksj", "skdd", "skrs", "jsszxqDm", "jslxDm", "gh", "jsxm", "skbjDm", "skbjName", "kcmc", "jsh", "startWeek", "endWeek", "zxf", "zxs", "syxs", "skxq", "jcinfo", "stimezc", "realSkbj", "kclb", "kclbName", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKbBasicInfoModelExtend extends GsKbBasicInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
