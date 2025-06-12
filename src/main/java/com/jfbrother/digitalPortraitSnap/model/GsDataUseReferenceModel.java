package com.jfbrother.digitalPortraitSnap.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据使用参照,快照系统取数依据传输对象
 * @author xinz 2023-03-30
 */
@Data
@ApiModel("数据使用参照,快照系统取数依据传输对象")
@JSONType(orders = { "id", "templateName", "startTime", "endTime", "bjdm", "xn", "xq", "status", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsDataUseReferenceModel {
    @ApiModelProperty(value = "流水号", position = 0)
    private String id;

    @ApiModelProperty(value = "数据模板名称", position = 1)
    private String templateName;

    @ApiModelProperty(value = "起始时间", position = 2)
    private String startTime;

    @ApiModelProperty(value = "截止时间", position = 3)
    private String endTime;

    @ApiModelProperty(value = "班级代码", position = 4)
    private String bjdm;

    @ApiModelProperty(value = "学年", position = 5)
    private String xn;

    @ApiModelProperty(value = "学期", position = 6)
    private String xq;

    @ApiModelProperty(value = "是否启用，0-未启用 1-已启用", position = 7)
    private String status;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 8)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 9)
    private String remark;

    @ApiModelProperty(value = "排序", position = 10)
    private Integer orderBy;

    @ApiModelProperty(value = "创建人", position = 11)
    private String createId;

    @ApiModelProperty(value = "创建时间", position = 12)
    private String createTime;

    @ApiModelProperty(value = "修改人", position = 13)
    private String updateId;

    @ApiModelProperty(value = "修改时间", position = 14)
    private String updateTime;

    @ApiModelProperty(value = "班级名称", position = 15)
    private String bjmc;
}