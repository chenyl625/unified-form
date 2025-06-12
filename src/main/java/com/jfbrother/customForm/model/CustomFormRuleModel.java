package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  自定义表单规则传输对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ApiModel("自定义表单规则传输对象")
@JSONType(orders = { "id", "formId", "text", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormRuleModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "表单id", position = 1)
    private String formId;

    @ApiModelProperty(value = "规则文本", position = 2)
    private String text;

    @ApiModelProperty(value = "排序号", position = 3)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 4)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 5)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 6)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 8)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 9)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 10)
    private String updateUser;

}
