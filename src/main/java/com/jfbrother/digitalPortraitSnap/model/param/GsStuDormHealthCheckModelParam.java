package com.jfbrother.digitalPortraitSnap.model.param;

import java.math.BigDecimal;
import com.jfbrother.digitalPortraitSnap.model.GsStuDormHealthCheckModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生寝室卫生检查传输对象
 * @author xinz 2023-03-10
 */
@Data
@ApiModel("学生寝室卫生检查传输对象param")
@JSONType(orders = { "etlJfuuid", "bedroomId", "score", "bedroomName", "bedroomLou", "bedroomFloor", "bedroomArea", "userTypeName", "danger", "remark", "checkDate", "checkUser", "checkUserCode", "levelType", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsStuDormHealthCheckModelParam extends GsStuDormHealthCheckModel {

}
