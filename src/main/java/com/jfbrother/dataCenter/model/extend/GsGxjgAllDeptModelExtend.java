package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsGxjgAllDeptModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
*  人事中历史所有部门信息传输对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Data
@ApiModel("人事中历史所有部门信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "bmdm", "bmmc", "sjbmdm", "fzr", "sortNum", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxjgAllDeptModelExtend extends GsGxjgAllDeptModel {

}
