package com.jfbrother.digitalPortraitSnap.model.search;

import com.jfbrother.digitalPortraitSnap.model.GsStuTagRelationModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *  学生标签关系表传输对象
 * @author xinz 2023-03-28
 */
@Data
@ApiModel("学生标签关系表传输对象search")
@JSONType(orders = { "id", "stuCode", "tagId", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagRelationModelSearch extends GsStuTagRelationModel {

}
