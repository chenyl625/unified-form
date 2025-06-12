package com.jfbrother.customForm.model.response;

import com.jfbrother.customForm.model.extend.CustomFormModelExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("自定义表单我的填报（年份）-响应模型")
public class CustomFormSelfYearResponseModel {

    @ApiModelProperty(value = "年份", position = 1)
    private Integer year;

    @ApiModelProperty(value = "填报信息", position = 2)
    private List<CustomFormModelExtend> children;

}
