package com.jfbrother.digitalPortraitSnap.model;


import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  学生家长/教师留言传输对象
 * @author xinz 2023-03-16
 */
@Data
@ApiModel("学生家长/教师留言传输对象")
@JSONType(orders = { "id", "code", "name", "type", "leaveTime", "msg", "deleteFlag", "remark", "createTime", "updateTime"})
public class GsStuLeaveMsgModel {
    @ApiModelProperty(value = "流水号", position = 0)
    private String id;

    @ApiModelProperty(value = "工号/学号", position = 1)
    private String code;

    @ApiModelProperty(value = "姓名", position = 2)
    private String name;

    @ApiModelProperty(value = "类别，0-学生家长 1-老师", position = 3)
    private String type;

    @ApiModelProperty(value = "留言时间", position = 4)
    private Long leaveTime;

    @ApiModelProperty(value = "留言内容", position = 5)
    private String msg;

    @ApiModelProperty(value = "删除标志，0-未删 1-已删", position = 6)
    private String deleteFlag;

    @ApiModelProperty(value = "备注", position = 7)
    private String remark;

    @ApiModelProperty(value = "创建时间", position = 8)
    private Long createTime;

    @ApiModelProperty(value = "修改时间", position = 9)
    private Long updateTime;

    @ApiModelProperty(value = "关于谁的留言，学生学号", position = 10)
    private String aboutStuCode;

    @ApiModelProperty(value = "班主任留言，0-未读 1-已读", position = 11)
    private String bzrReadOrNot;

    @ApiModelProperty(value = "辅导员留言，0-未读 1-已读", position = 12)
    private String fdyReadOrNot;

    @ApiModelProperty(value = "家长留言，0-未读 1-已读", position = 13)
    private String parentReadOrNot;
}
