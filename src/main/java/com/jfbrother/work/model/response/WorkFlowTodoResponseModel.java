package com.jfbrother.work.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("流程待办-响应模型")
public class WorkFlowTodoResponseModel {

    @ApiModelProperty(value = "本体建设", position = 1)
    private String group;

    @ApiModelProperty(value = "", position = 2)
    private String subGroup;

    @ApiModelProperty(value = "流程名称", position = 3)
    private String name;

    @ApiModelProperty(value = "环节名称", position = 4)
    private String taskName;

    @ApiModelProperty(value = "", position = 5)
    private int typeId;

    @ApiModelProperty(value = "", position = 6)
    private int approvable;

    @ApiModelProperty(value = "创建人", position = 7)
    private String createUser;

    @ApiModelProperty(value = "创建时间", position = 8)
    private String createDate;

    @ApiModelProperty(value = "", position = 9)
    private String cosignFlag;

    @ApiModelProperty(value = "", position = 10)
    private String assignName;

    @ApiModelProperty(value = "环节id", position = 11)
    private int id;

    @ApiModelProperty(value = "流程id", position = 12)
    private int requestId;

    @ApiModelProperty(value = "环节状态", position = 13)
    private String status;

    @ApiModelProperty(value = "启动时间", position = 14)
    private String initDate;

    @ApiModelProperty(value = "环节到达时间", position = 15)
    private String assignDate;

    @ApiModelProperty(value = "", position = 16)
    private String fromUsername;

    @ApiModelProperty(value = "流程自定义标识", position = 17)
    private String defFieldId;

    @ApiModelProperty(value = "", position = 18)
    private String priorityId;
}
