package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuContactModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生联系方式传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("学生联系方式传输对象extend")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "studentShortphone", "homeAd", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuContactModelExtend extends GsGxxsStuContactModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
