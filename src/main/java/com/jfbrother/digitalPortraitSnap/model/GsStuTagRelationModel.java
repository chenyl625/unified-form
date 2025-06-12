package com.jfbrother.digitalPortraitSnap.model;


import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  学生标签关系表传输对象
 * @author xinz 2023-03-28
 */
@Data
@ApiModel("学生标签关系表传输对象")
@JSONType(orders = { "id", "stuCode", "tagId", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagRelationModel {
    @ApiModelProperty(value = "流水号", position = 0)
    private String id;

    @ApiModelProperty(value = "学号", position = 1)
    private String stuCode;

    @ApiModelProperty(value = "标签 id", position = 2)
    private String tagId;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 3)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 4)
    private String remark;

    @ApiModelProperty(value = "排序", position = 5)
    private Integer orderBy;

    @ApiModelProperty(value = "创建人", position = 6)
    private String createId;

    @ApiModelProperty(value = "创建时间", position = 7)
    private String createTime;

    @ApiModelProperty(value = "修改人", position = 8)
    private String updateId;

    @ApiModelProperty(value = "修改时间", position = 9)
    private String updateTime;

}