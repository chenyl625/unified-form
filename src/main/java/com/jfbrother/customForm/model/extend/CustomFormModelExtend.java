package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 自定义表单传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ApiModel("自定义表单传输对象extend")
@JSONType(orders = {"id", "name", "chineseExplain", "remark", "isEnable", "fillScope", "approvalProcess", "startDateTime", "endDateTime", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormModelExtend extends CustomFormModel {

    @ApiModelProperty(value = "表单规则", position = 1)
    private String ruleText;

    @ApiModelProperty(value = "表单规则", position = 2)
    private CustomFormRuleModelExtend customFormRule;

    @ApiModelProperty(value = "表单字段", position = 3)
    private List<CustomFormFieldModelExtend> tableColumn;

    @ApiModelProperty(value = "表单子表", position = 4)
    private List<CustomFormChildModelExtend> tableChild;

    @ApiModelProperty(value = "所属年份", position = 5)
    private Integer years;

    @ApiModelProperty(value = "填报信息", position = 6)
    private CustomFormFillModelExtend fillData;

    @ApiModelProperty(value = "使用时间范围", position = 7)
    private List<Long> dateTimes;

    @ApiModelProperty(value = "截止天数", position = 8)
    private Long deadline;

    @ApiModelProperty(value = "报表名称", position = 1)
    private String jimuReportName;
}
