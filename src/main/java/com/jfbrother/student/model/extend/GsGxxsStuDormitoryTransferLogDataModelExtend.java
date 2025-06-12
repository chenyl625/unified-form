package com.jfbrother.student.model.extend;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.student.model.GsGxxsStuDormitoryTransferLogDataModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室调动日志数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生寝室调动日志数据传输对象extend")
@JSONType(orders = { "etlJfuuid", "xh", "schoolYear", "term", "bedroomName", "changeBedroomName", "bedCode", "changeBedCode", "type", "transactors", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormitoryTransferLogDataModelExtend extends GsGxxsStuDormitoryTransferLogDataModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
