package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  自定义表单填报传输对象
* @author chenyl@jfbrother.com 2022-06-21
*/
@Data
@ApiModel("自定义表单填报传输对象")
@JSONType(orders = { "id", "userId", "formId", "flowStatus", "flowId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFillModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "用户id", position = 1)
    private String userId;

    @ApiModelProperty(value = "发布表单id", position = 1)
    private String releaseId;

    @ApiModelProperty(value = "流程状态（进行中，已完成，已取消）", position = 3)
    private String flowStatus;

    @ApiModelProperty(value = "流程id", position = 4)
    private Integer flowId;

    @ApiModelProperty(value = "启动时间", position = 5)
    private Long startDate;

    @ApiModelProperty(value = "结束时间", position = 6)
    private Long endDate;

    @ApiModelProperty(value = "填报时间", position = 6)
    private Long fillDateTime;

    @ApiModelProperty(value = "排序号", position = 7)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 8)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 9)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 10)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 12)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 13)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 14)
    private String updateUser;


}
