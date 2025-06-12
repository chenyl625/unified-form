package com.jfbrother.dataCenter.model.response;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师人事信息-响应模型
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ApiModel("教师人事信息-响应模型")
public class TeacherPersonnelResponseModel {
    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "是否双师型教师", position = 2)
    private String isDouble;

    @ApiModelProperty(value = "是否有职业资格", position = 3)
    private String isQuali;

    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
