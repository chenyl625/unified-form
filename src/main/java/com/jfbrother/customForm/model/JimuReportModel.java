package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  在线excel设计器传输对象
* @author chenyl@jfbrother.com 2022-08-23
*/
@Data
@ApiModel("在线excel设计器传输对象")
@JSONType(orders = { "id", "code", "name", "note", "status", "type", "jsonStr", "apiUrl", "thumb", "createBy", "createTime", "updateBy", "updateTime", "delFlag", "apiMethod", "apiCode", "template", "viewCount", "cssStr", "jsStr"})
public class JimuReportModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "编码", position = 1)
    private String code;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "说明", position = 3)
    private String note;

    @ApiModelProperty(value = "状态", position = 4)
    private String status;

    @ApiModelProperty(value = "类型", position = 5)
    private String type;

    @ApiModelProperty(value = "json字符串", position = 6)
    private String jsonStr;

    @ApiModelProperty(value = "请求地址", position = 7)
    private String apiUrl;

    @ApiModelProperty(value = "缩略图", position = 8)
    private String thumb;

    @ApiModelProperty(value = "创建人", position = 9)
    private String createBy;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    @ApiModelProperty(value = "修改人", position = 11)
    private String updateBy;

    @ApiModelProperty(value = "修改时间", position = 12)
    private Long updateTime;

    @ApiModelProperty(value = "删除标识0-正常，1-已删除", position = 13)
    private int delFlag;

    @ApiModelProperty(value = "请求方法0-get，1-post", position = 14)
    private String apiMethod;

    @ApiModelProperty(value = "请求编码", position = 15)
    private String apiCode;

    @ApiModelProperty(value = "是否是模板 0-是，1-不是", position = 16)
    private int template;

    @ApiModelProperty(value = "浏览次数", position = 17)
    private long viewCount;

    @ApiModelProperty(value = "css增强", position = 18)
    private String cssStr;

    @ApiModelProperty(value = "js增强", position = 19)
    private String jsStr;

}
