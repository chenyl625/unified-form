package com.jfbrother.dataCenter.model.search;

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
@ApiModel("工资发放-个人收入明细传输对象search")
@JSONType(orders = { "etlJfuuid", "nybs", "xmrlzy", "gh", "yfgz", "sfgz", "gw", "xjgz", "gwjt", "shbt", "gjj", "yanglj", "yilj", "syj", "ghf", "yknj", "qtbfk", "sds", "id", "mainid", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwGzPersonDetailModelSearch extends GsCwGzPersonDetailModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
