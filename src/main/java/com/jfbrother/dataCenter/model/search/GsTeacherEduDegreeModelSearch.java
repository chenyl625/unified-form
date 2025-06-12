package com.jfbrother.dataCenter.model.search;

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
@ApiModel("教师学历学位传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "eduTopCode", "eduTopName", "degreeTopCode", "degreeTopName", "eduCode", "eduName", "eduCountryCode", "eduCountryName", "eduCollege", "eduMajor", "startTime", "endTime", "degreeCode", "degreeName", "degreeCountryCode", "degreeCountryName", "degreeCollege", "degreeTime", "styleCode", "styleName", "unitType", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherEduDegreeModelSearch extends GsTeacherEduDegreeModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
