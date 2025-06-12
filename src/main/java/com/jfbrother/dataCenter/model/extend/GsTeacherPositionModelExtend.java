package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherPositionModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师专业技术职务传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Data
@ApiModel("教师专业技术职务传输对象extend")
@JSONType(orders = {"etlJfuuid", "gh", "rsType", "positionCode", "positionName", "acquireDate", "bmh", "finalPositionCode", "subPositionCode", "rankPositionCode", "rsZyjszwParentid", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherPositionModelExtend extends GsTeacherPositionModel {
    @ApiModelProperty(value = "现专业技术等级及聘任时间", position = 1)
    private String positionNameDate;
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
