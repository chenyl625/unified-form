package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsCwJjPersonDetailModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  奖金发放-个人收入明细传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("奖金发放-个人收入明细传输对象search")
@JSONType(orders = { "etlJfuuid", "nybs", "xm", "gh", "yf", "ks", "sf", "yglx", "jjlx", "lymx", "jxnw", "id", "mainid", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwJjPersonDetailModelSearch extends GsCwJjPersonDetailModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
