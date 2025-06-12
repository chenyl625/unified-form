package com.jfbrother.dataCenter.model.search;

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
@ApiModel("学评教-学生评价老师汇总表传输对象search")
@JSONType(orders = { "etlJfuuid", "xn", "xq", "jsid", "totalScore", "gh", "kcmc", "kcid", "cprs", "jsxm", "avgPgdf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuEvaluateTeacherModelSearch extends GsStuEvaluateTeacherModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
