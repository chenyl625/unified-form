package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义表单传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ApiModel("自定义表单传输对象")
@JSONType(orders = {"id", "name", "chineseExplain", "remark", "isEnable", "fillScope", "approvalProcess", "startDateTime", "endDateTime", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "名称", position = 1)
    private String name;

    @ApiModelProperty(value = "中文解释", position = 2)
    private String chineseExplain;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

    @ApiModelProperty(value = "是否启用", position = 4)
    private Integer isEnable;

    @ApiModelProperty(value = "填写范围", position = 5)
    private String fillScope;

    @ApiModelProperty(value = "审批流程", position = 6)
    private String approvalProcess;

    @ApiModelProperty(value = "开始使用时间", position = 7)
    private Long startDateTime;

    @ApiModelProperty(value = "结束使用时间", position = 8)
    private Long endDateTime;

    @ApiModelProperty(value = "表单配置", position = 9)
    private String option;

    @ApiModelProperty(value = "流程自定义标识", position = 9)
    private String flowCustomDef;

    @ApiModelProperty(value = "积木报表id", position = 9)
    private String jimuReportId;

    @ApiModelProperty(value = "排序号", position = 9)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 10)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 11)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 12)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 13)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 14)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 15)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 16)
    private String updateUser;

}
