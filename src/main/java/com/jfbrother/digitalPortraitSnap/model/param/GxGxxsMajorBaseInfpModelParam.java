package com.jfbrother.digitalPortraitSnap.model.param;

import java.math.BigDecimal;
import com.jfbrother.digitalPortraitSnap.model.GxGxxsMajorBaseInfpModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  全部专业基本信息传输对象
 * @author hjy@jfbrother.com 2023-03-08
 */
@Data
@ApiModel("全部专业基本信息传输对象param")
@JSONType(orders = { "yxId", "yxmc", "yxdm", "id", "zymc", "zyDm", "gbzydm", "xz", "etlPri", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GxGxxsMajorBaseInfpModelParam extends GxGxxsMajorBaseInfpModel {

}
