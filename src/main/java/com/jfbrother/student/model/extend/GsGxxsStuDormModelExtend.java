package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuDormModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
*  学生住宿信息传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生住宿信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "bedCode", "bedroomArea", "bedroomLou", "bedroomFloor", "bedroomName", "ifAir", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormModelExtend extends GsGxxsStuDormModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
