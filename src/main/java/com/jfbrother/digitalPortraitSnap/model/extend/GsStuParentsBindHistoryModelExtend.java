package com.jfbrother.digitalPortraitSnap.model.extend;

import com.jfbrother.digitalPortraitSnap.model.GsStuParentsBindHistoryModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;

import lombok.Data;

/**
 *  学生-家长绑定历史信息传输对象
 * @author xinz 2023-03-21
 */
@Data
@ApiModel("学生-家长绑定历史信息传输对象extend")
@JSONType(orders = { "id", "stuCode", "parentName", "parentPhone", "relation", "openId", "deleteFlag", "remark", "createTime", "updateTime"})
public class GsStuParentsBindHistoryModelExtend extends GsStuParentsBindHistoryModel {

}
