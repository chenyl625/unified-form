package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormChildModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 自定义表单子表传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ApiModel("自定义表单子表传输对象extend")
@JSONType(orders = {"id", "formId", "tableName", "tableComment", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormChildModelExtend extends CustomFormChildModel {

    @ApiModelProperty(value = "表单字段", position = 2)
    private List<CustomFormFieldModelExtend> tableColumn;

}
