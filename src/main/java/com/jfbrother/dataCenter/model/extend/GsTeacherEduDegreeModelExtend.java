package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherEduDegreeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师学历学位传输对象
* @author chenyl@jfbrother.com 2022-08-09
*/
@Data
@ApiModel("教师学历学位传输对象extend")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "eduTopCode", "eduTopName", "degreeTopCode", "degreeTopName", "eduCode", "eduName", "eduCountryCode", "eduCountryName", "eduCollege", "eduMajor", "startTime", "endTime", "degreeCode", "degreeName", "degreeCountryCode", "degreeCountryName", "degreeCollege", "degreeTime", "styleCode", "styleName", "unitType", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherEduDegreeModelExtend extends GsTeacherEduDegreeModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
