package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuXjChangeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生学籍异动最新记录传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生学籍异动最新记录传输对象search")
@JSONType(orders = { "etlJfuuid", "xn", "xh", "userXh", "changeTypeCode", "changeTypeName", "changeTime", "changeReasonCode", "confirmTime", "changeNumber", "changeExplain", "oldnj", "oldyx", "oldzy", "oldbj", "yddnj", "yddyx", "yddzy", "yddbj", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuXjChangeModelSearch extends GsGxxsStuXjChangeModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
