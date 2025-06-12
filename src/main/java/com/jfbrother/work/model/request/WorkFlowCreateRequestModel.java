package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 启动流程-请求模型
 */
@Data
@ApiModel("启动流程-请求模型")
public class WorkFlowCreateRequestModel {

    @ApiModelProperty(value = "流程自定义标识", position = 1, required = true)
    private String wf_def;

    @ApiModelProperty(value = "发起人", position = 2, required = true)
    private String init_user;

    @ApiModelProperty(value = "是否驱动到审批环节 默认否", position = 3)
    private boolean forward;

    @ApiModelProperty(value = "表单组件赋值列表", position = 4)
    private List<WorkFlowCreateFormDataModel> form_data;

    @ApiModelProperty(value = "环节办理人列表", position = 3)
    private List<WorkFlowCreateFormAssigneeDataModel> assignee_data;

    @Data
    @ApiModel("表单组件赋值实例")
    public static class WorkFlowCreateFormDataModel {

        @ApiModelProperty(value = "表单组件自定义标识", position = 3)
        private String def;

        @ApiModelProperty(value = "表单组件赋值", position = 3)
        private String val;

    }

    @Data
    @ApiModel("环节办理人实例")
    public static class WorkFlowCreateFormAssigneeDataModel {

        @ApiModelProperty(value = "环节标识（环节备注）", position = 3)
        private String def;

        @ApiModelProperty(value = "环节办理人", position = 3)
        private String val;

    }

}
