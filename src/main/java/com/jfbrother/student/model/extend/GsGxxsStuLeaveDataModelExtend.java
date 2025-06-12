package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuLeaveDataModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生请假数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生请假数据传输对象extend")
@JSONType(orders = { "etlJfuuid", "xh", "schoolYear", "term", "type", "category", "startTime", "endTime", "firstSection", "lastSection", "sectionLength", "sky", "reason", "file", "teacherName", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuLeaveDataModelExtend extends GsGxxsStuLeaveDataModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
