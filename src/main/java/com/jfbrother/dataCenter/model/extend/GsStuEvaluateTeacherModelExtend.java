package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsStuEvaluateTeacherModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学评教-学生评价老师汇总表传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("学评教-学生评价老师汇总表传输对象extend")
@JSONType(orders = { "etlJfuuid", "xn", "xq", "jsid", "totalScore", "gh", "kcmc", "kcid", "cprs", "jsxm", "avgPgdf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuEvaluateTeacherModelExtend extends GsStuEvaluateTeacherModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
