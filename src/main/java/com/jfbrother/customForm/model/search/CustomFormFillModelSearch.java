package com.jfbrother.customForm.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFillModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
*  自定义表单填报传输对象
* @author chenyl@jfbrother.com 2022-06-21
*/
@Data
@ApiModel("自定义表单填报传输对象search")
@JSONType(orders = { "id", "userId", "formId", "flowStatus", "flowId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFillModelSearch extends CustomFormFillModel {

    @ApiModelProperty(value = "是否有流程", position = 1)
    private Boolean izFlow;

    @ApiModelProperty(value = "填报时间范围", position = 2)
    private List<Long> fillDateTimes;

    @ApiModelProperty(value = "部门", position = 3)
    private String deptId;

    @ApiModelProperty(value = "填报人姓名", position = 4)
    private String fillUserName;

    @ApiModelProperty(value = "发布表单名称", position = 3)
    private String releaseName;

    @ApiModelProperty(value = "是否只查看自己", position = 3)
    private Boolean isSelfSearch;

    @ApiModelProperty(value = "已选字段数组", position = 15)
    private List<String> fieldList;

    private String fileUploadField;
}
