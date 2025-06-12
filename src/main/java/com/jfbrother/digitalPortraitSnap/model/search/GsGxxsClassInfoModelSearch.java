package com.jfbrother.digitalPortraitSnap.model.search;

import com.jfbrother.digitalPortraitSnap.model.GsGxxsClassInfoModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *  班级基本信息传输对象
 * @author xinz 2023-04-03
 */
@Data
@ApiModel("班级基本信息传输对象search")
@JSONType(orders = { "nj", "id", "bjmc", "xz", "bjdm", "majorDm", "xyDm", "etlPri", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate", "zyId"})
public class GsGxxsClassInfoModelSearch extends GsGxxsClassInfoModel {

}