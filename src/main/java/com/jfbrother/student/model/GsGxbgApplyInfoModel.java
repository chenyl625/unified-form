package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  办事申请表传输对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ApiModel("办事申请表传输对象")
@JSONType(orders = { "etlJfuuid", "flowStep", "createdUserId", "termId", "deleted", "publishId", "remind", "status", "submitDate", "applyerType", "userCode", "userName", "userType", "termStartDate", "termEndDate", "isCurrent", "collidePublishId", "publishStatus", "publishName", "publishStartDate", "publishEndDate", "tableId", "tabName", "tableTypeId", "tableTypeCode", "tableTypeName", "schoolYear", "semester", "userStatus", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxbgApplyInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "当前任务流程进度", position = 1)
    private Long flowStep;

    @ApiModelProperty(value = "申请人id", position = 2)
    private Long createdUserId;

    @ApiModelProperty(value = "申请学期", position = 3)
    private Long termId;

    @ApiModelProperty(value = "删除状态 0:未删除 1:已删除", position = 4)
    private Integer deleted;

    @ApiModelProperty(value = "发布的任务id", position = 5)
    private Long publishId;

    @ApiModelProperty(value = "提醒结果", position = 6)
    private String remind;

    @ApiModelProperty(value = "任务申请状态 0:暂存 1:已退回 2:待审核 3:通过 4:撤销", position = 7)
    private Integer status;

    @ApiModelProperty(value = "提交申请时间", position = 8)
    private String submitDate;

    @ApiModelProperty(value = "申请人类型 1:学生 2:教师", position = 9)
    private Integer applyerType;

    @ApiModelProperty(value = "工号（工号和身份证不能同时为空）", position = 10)
    private String userCode;

    @ApiModelProperty(value = "姓名", position = 11)
    private String userName;

    @ApiModelProperty(value = "用户类型 1:管理员 2:教师 3：学生 4：测试", position = 12)
    private Integer userType;

    @ApiModelProperty(value = "开始时间", position = 13)
    private String termStartDate;

    @ApiModelProperty(value = "结束时间", position = 14)
    private String termEndDate;

    @ApiModelProperty(value = "是否当前学期: 1，是 2，否", position = 15)
    private Integer isCurrent;

    @ApiModelProperty(value = "collide_publish_id", position = 16)
    private Long collidePublishId;

    @ApiModelProperty(value = "任务状态 0:删除 1:关闭 2:发布中 3暂停", position = 17)
    private Integer publishStatus;

    @ApiModelProperty(value = "任务标题", position = 18)
    private String publishName;

    @ApiModelProperty(value = "开始时间", position = 19)
    private String publishStartDate;

    @ApiModelProperty(value = "截止时间", position = 20)
    private String publishEndDate;

    @ApiModelProperty(value = "任务表格", position = 21)
    private Long tableId;

    @ApiModelProperty(value = "表格名称", position = 22)
    private String tabName;

    @ApiModelProperty(value = "表格类型", position = 23)
    private Long tableTypeId;

    @ApiModelProperty(value = "表格类型代码", position = 24)
    private String tableTypeCode;

    @ApiModelProperty(value = "表格类型名称", position = 25)
    private String tableTypeName;

    @ApiModelProperty(value = "学年", position = 26)
    private String schoolYear;

    @ApiModelProperty(value = "学期", position = 27)
    private String semester;

    @ApiModelProperty(value = "用户状态（0停用，1正常，2离司）", position = 28)
    private Integer userStatus;

    @ApiModelProperty(value = "id", position = 29)
    private Long id;

    @ApiModelProperty(value = "业务主键", position = 30)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 31)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 32)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 33)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 34)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 35)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 36)
    private String etlCheckDate;

}
