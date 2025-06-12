package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFillModel;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 自定义表单填报传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
@Data
@ApiModel("自定义表单填报传输对象extend")
@JSONType(orders = {"id", "userId", "formId", "flowStatus", "flowId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFillModelExtend extends CustomFormFillModel {

    @ApiModelProperty(value = "填报数据", position = 1)
    private JSONObject data;

    @ApiModelProperty(value = "填报人姓名", position = 2)
    private String fillUserName;

    @ApiModelProperty(value = "表单名称", position = 3)
    private String releaseName;

    @ApiModelProperty(value = "环节信息", position = 4)
    private List<WorkFlowTaskModelExtend> taskList;

    @ApiModelProperty(value = "当前环节信息", position = 5)
    private List<WorkFlowTaskModelExtend> currentTaskList;

    @ApiModelProperty(value = "表单信息", position = 6)
    private CustomFormModelExtend formData;

    @ApiModelProperty(value = "表单id", position = 7)
    private String formId;

    @ApiModelProperty(value = "自定义表单规则", position = 8)
    private String ruleText;

    @ApiModelProperty(value = "表单发布信息", position = 9)
    private CustomFormReleaseModelExtend release;

    @ApiModelProperty(value = "是否流程", position = 10)
    private Integer isFlow;

    @ApiModelProperty(value = "接口数据", position = 11)
    private Map<String, Object> connDataMap;

    @ApiModelProperty(value = "部门名称", position = 12)
    private String deptName;

    @ApiModelProperty(value = "积木报表id",position = 12)
    private String jimuReportId;

/*    @ApiModelProperty(value = "流程环节标识", position = 9)
    private List<CustomFormReleaseTaskModelExtend> releaseTaskList;

    @ApiModelProperty(value = "字段关联信息", position = 7)
    private List<CustomFormReleaseConnModelExtend> releaseConnList;*/

}
