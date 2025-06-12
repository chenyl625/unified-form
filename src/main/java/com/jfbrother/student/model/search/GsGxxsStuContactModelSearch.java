package com.jfbrother.student.model.search;

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
@ApiModel("学生联系方式传输对象search")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "studentShortphone", "homeAd", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuContactModelSearch extends GsGxxsStuContactModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
