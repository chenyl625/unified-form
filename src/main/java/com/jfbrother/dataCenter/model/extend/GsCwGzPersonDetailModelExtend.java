package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsCwGzPersonDetailModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  工资发放-个人收入明细传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("工资发放-个人收入明细传输对象extend")
@JSONType(orders = { "etlJfuuid", "nybs", "xmrlzy", "gh", "yfgz", "sfgz", "gw", "xjgz", "gwjt", "shbt", "gjj", "yanglj", "yilj", "syj", "ghf", "yknj", "qtbfk", "sds", "id", "mainid", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwGzPersonDetailModelExtend extends GsCwGzPersonDetailModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
