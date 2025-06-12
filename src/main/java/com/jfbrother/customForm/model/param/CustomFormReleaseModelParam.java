package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormReleaseModel;
import com.jfbrother.customForm.model.extend.CustomFormReleaseTaskModelExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
*  自定义表单发布传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("自定义表单发布传输对象param")
@JSONType(orders = { "id", "formId", "isEnable", "fillScope", "startDateTime", "endDateTime", "flowCustomDef", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseModelParam extends CustomFormReleaseModel {

    @ApiModelProperty(value = "环节自定义标识列表", position = 1)
    private List<CustomFormReleaseTaskModelExtend> releaseTaskList;

}
