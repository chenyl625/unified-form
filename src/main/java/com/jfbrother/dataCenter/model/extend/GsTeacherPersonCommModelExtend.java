package com.jfbrother.dataCenter.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.dataCenter.model.GsTeacherPersonCommModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  教师个人通讯方式传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师个人通讯方式传输对象extend")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "email", "bgPhone", "telephone", "ltLong", "ltShort", "ydLong", "ydShort", "qq", "address", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherPersonCommModelExtend extends GsTeacherPersonCommModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
