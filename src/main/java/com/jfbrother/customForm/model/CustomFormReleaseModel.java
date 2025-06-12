package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义表单发布传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Data
@ApiModel("自定义表单发布传输对象")
@JSONType(orders = {"id", "formId", "isEnable", "fillScope", "startDateTime", "endDateTime", "flowCustomDef", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "表单id", position = 1)
    private String formId;

    @ApiModelProperty(value = "发布名称", position = 1)
    private String releaseName;

    @ApiModelProperty(value = "是否启用", position = 2)
    private Integer isEnable;

    @ApiModelProperty(value = "填写范围", position = 3)
    private String fillScope;

    @ApiModelProperty(value = "开始使用时间", position = 4)
    private Long startDateTime;

    @ApiModelProperty(value = "结束使用时间", position = 5)
    private Long endDateTime;

    @ApiModelProperty(value = "流程自定义标识", position = 6)
    private String flowCustomDef;

    @ApiModelProperty(value = "是否流程", position = 6)
    private Integer isFlow;

    @ApiModelProperty(value = "门户事项档案号,具备事项的唯一性", position = 7)
    private String itemDomId;

    @ApiModelProperty(value = "备注", position = 6)
    private String remark;

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
