package com.jfbrother.digitalPortraitSnap.model.param;

import com.jfbrother.digitalPortraitSnap.model.GsStuTagMajorItemModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *  学生标签大类传输对象
 * @author xinz 2023-03-27
 */
@Data
@ApiModel("学生标签大类传输对象param")
@JSONType(orders = { "id", "tagMajorItem", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagMajorItemModelParam extends GsStuTagMajorItemModel {

}
