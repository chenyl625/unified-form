package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuSocialPracModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生社会实践传输对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ApiModel("学生社会实践传输对象extend")
@JSONType(orders = { "etlJfuuid", "xh", "nj", "bjmc", "participateMinute", "type", "acquireReason", "ssyx", "isEnable", "scoreType", "subNj", "bjdm", "subBjmc", "startDate", "endDate", "acquireDate", "acquireNum", "unit", "importType", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuSocialPracModelExtend extends GsGxxsStuSocialPracModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
