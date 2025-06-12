package com.jfbrother.student.model.search;

import java.math.BigDecimal;
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
@ApiModel("学生寝室调动日志数据传输对象search")
@JSONType(orders = { "etlJfuuid", "xh", "schoolYear", "term", "bedroomName", "changeBedroomName", "bedCode", "changeBedCode", "type", "transactors", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormitoryTransferLogDataModelSearch extends GsGxxsStuDormitoryTransferLogDataModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
