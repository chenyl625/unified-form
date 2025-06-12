package com.jfbrother.digitalPortraitSnap.model;


import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  学生标签库传输对象
 * @author xinz 2023-03-27
 */
@Data
@ApiModel("学生标签库传输对象")
@JSONType(orders = { "id", "tagMajorItemCode", "tagMajorItem", "tagName", "tagEvaluationZw", "tagEvaluationSql", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsStuTagLibraryModel {
    @ApiModelProperty(value = "流水号，标签代码", position = 0)
    private String id;

    @ApiModelProperty(value = "标签大类代码", position = 1)
    private String tagMajorItemCode;

    @ApiModelProperty(value = "标签大类中文", position = 2)
    private String tagMajorItem;

    @ApiModelProperty(value = "标签名字", position = 3)
    private String tagName;

    @ApiModelProperty(value = "标签评定中文介绍", position = 4)
    private String tagEvaluationZw;

    @ApiModelProperty(value = "标签评定 sql", position = 5)
    private String tagEvaluationSql;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 6)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 7)
    private String remark;

    @ApiModelProperty(value = "排序", position = 8)
    private Integer orderBy;

    @ApiModelProperty(value = "创建人", position = 9)
    private String createId;

    @ApiModelProperty(value = "创建时间", position = 10)
    private String createTime;

    @ApiModelProperty(value = "修改人", position = 11)
    private String updateId;

    @ApiModelProperty(value = "修改时间", position = 12)
    private String updateTime;

}
