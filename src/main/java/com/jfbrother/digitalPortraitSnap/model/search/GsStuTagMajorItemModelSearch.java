package com.jfbrother.digitalPortraitSnap.model.search;

import com.jfbrother.digitalPortraitSnap.model.GsStuTagMajorItemModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  学生标签大类传输对象
 * @author xinz 2023-03-27
 */
@Data
@ApiModel("学生标签大类传输对象search")
@JSONType(orders = { "id", "tagMajorItem", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagMajorItemModelSearch extends GsStuTagMajorItemModel {
    @ApiModelProperty(value = "多个主键id,查询")
    private String ids;
}
