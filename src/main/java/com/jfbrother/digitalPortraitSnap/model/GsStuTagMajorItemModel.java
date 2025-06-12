package com.jfbrother.digitalPortraitSnap.model;


import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  学生标签大类传输对象
 * @author xinz 2023-03-27
 */
@Data
@ApiModel("学生标签大类传输对象")
@JSONType(orders = { "id", "tagMajorItem", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagMajorItemModel {
    @ApiModelProperty(value = "流水号，标签大类代码", position = 0)
    private String id;

    @ApiModelProperty(value = "标签大类中文", position = 1)
    private String tagMajorItem;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 2)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer orderBy;

    @ApiModelProperty(value = "创建人", position = 5)
    private String createId;

    @ApiModelProperty(value = "创建时间", position = 6)
    private String createTime;

    @ApiModelProperty(value = "修改人", position = 7)
    private String updateId;

    @ApiModelProperty(value = "修改时间", position = 8)
    private String updateTime;

}
