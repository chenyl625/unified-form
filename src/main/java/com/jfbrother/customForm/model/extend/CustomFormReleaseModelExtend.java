package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormReleaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 自定义表单发布传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Data
@ApiModel("自定义表单发布传输对象extend")
@JSONType(orders = {"id", "formId", "isEnable", "fillScope", "startDateTime", "endDateTime", "flowCustomDef", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseModelExtend extends CustomFormReleaseModel {

    @ApiModelProperty(value = "表单名称", position = 1)
    private String formName;

    @ApiModelProperty(value = "使用时间范围", position = 2)
    private List<Long> dateTimes;

    @ApiModelProperty(value = "填报角色", position = 3)
    private String fillScopeName;

    @ApiModelProperty(value = "截止天数", position = 4)
    private Long deadline;

    @ApiModelProperty(value = "表单信息", position = 5)
    private CustomFormModelExtend formData;

    @ApiModelProperty(value = "流程环节标识", position = 6)
    private List<CustomFormReleaseTaskModelExtend> releaseTaskList;

    @ApiModelProperty(value = "字段关联信息", position = 7)
    private List<CustomFormReleaseConnModelExtend> releaseConnList;

    @ApiModelProperty(value = "字段接口关联信息", position = 8)
    private List<CustomFormReleaseConnModelExtend> connectorList;
}
