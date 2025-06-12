package com.jfbrother.digitalPortraitSnap.model.param;

import java.math.BigDecimal;
import com.jfbrother.digitalPortraitSnap.model.GsStuDormNightSignModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室晚归签到传输对象
 * @author xinz@jfbrother.com 2023-03-09
 */
@Data
@ApiModel("学生寝室晚归签到传输对象param")
@JSONType(orders = { "etlJfuuid", "xh", "type", "checkTime", "way", "lateTime", "checkStu", "checkStuCode", "remark", "createTime", "updateTime", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuDormNightSignModelParam extends GsStuDormNightSignModel {

}
