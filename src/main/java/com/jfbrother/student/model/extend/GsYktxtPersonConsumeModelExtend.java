package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsYktxtPersonConsumeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  个人消费记录传输对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ApiModel("个人消费记录传输对象extend")
@JSONType(orders = { "etlJfuuid", "id", "code", "bookkeepdate", "dealtime", "feepaymentway", "businessdeptcode", "mondeal", "mondiscount", "businessnum", "businessname", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsYktxtPersonConsumeModelExtend extends GsYktxtPersonConsumeModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
