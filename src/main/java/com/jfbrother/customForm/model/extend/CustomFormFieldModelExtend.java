package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义表单字段传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ApiModel("自定义表单字段传输对象extend")
@JSONType(orders = {"id", "pid", "comment", "defaultValue", "maxLength", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFieldModelExtend extends CustomFormFieldModel {

    @ApiModelProperty(value = "发布关联信息", position = 1)
    private CustomFormReleaseConnModelExtend releaseConn;

    @ApiModelProperty(value = "是否为子表字段", position = 2)
    private Integer isChildField;

    @ApiModelProperty(value = "子表id", position = 3)
    private String childId;

}
