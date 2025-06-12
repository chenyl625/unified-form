package com.jfbrother.work.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程-请求模型
 */
@Data
@ApiModel("流程-请求模型")
public class WorkFlowRequestModel {

    @ApiModelProperty(value = "流程自定义标识，流程模板的唯一标识。多个用逗号分割 “MSSP,htfwsh,wode3”", position = 1)
    private String wf_def;

    @ApiModelProperty(value = "发起人（支持用户名，工号，手机号，邮箱，身份证号） 多个用逗号分割 “peter,dingjiawen”", position = 2)
    private String init_user;

    @ApiModelProperty(value = "启动时间范围-开始，格式： 2020-01-01", position = 3)
    private String init_time_st;

    @ApiModelProperty(value = "启动时间范围-结束，格式： 2020-03-01", position = 4)
    private String init_time_ed;

    @ApiModelProperty(value = "排序字段 支持流程id（wf_id）,发起时间（init_time）如：“wf_id desc, init_time asc”", position = 5)
    private String sort_col;

}
