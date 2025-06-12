package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsCwProjectTeacherDetailModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  项目发放-教职工收入传输对象
* @author chenyl@jfbrother.com 2022-08-12
*/
@Data
@ApiModel("项目发放-教职工收入传输对象extend")
@JSONType(orders = { "etlJfuuid", "bh", "xm", "gh", "je", "gxkh", "gwkh", "yglxwb", "yglx", "bz", "nybs", "jfly", "jfrq", "jfspr", "ffnr", "xmktbh", "xmlx", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwProjectTeacherDetailModelExtend extends GsCwProjectTeacherDetailModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
