package com.jfbrother.digitalPortraitSnap.model.extend;

import com.jfbrother.digitalPortraitSnap.model.GsStuTagLibraryModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *  学生标签库传输对象
 * @author xinz 2023-03-27
 */
@Data
@ApiModel("学生标签库传输对象extend")
@JSONType(orders = { "id", "tagMajorItemCode", "tagMajorItem", "tagName", "tagEvaluationZw", "tagEvaluationSql", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagLibraryModelExtend extends GsStuTagLibraryModel {

}