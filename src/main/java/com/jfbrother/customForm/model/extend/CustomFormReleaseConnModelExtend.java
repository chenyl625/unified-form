package com.jfbrother.customForm.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.baseserver.enums.ConnTypeEnum;
import com.jfbrother.customForm.model.CustomFormReleaseConnModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  自定义表单发布对应接口传输对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Data
@ApiModel("自定义表单发布对应接口传输对象extend")
@JSONType(orders = { "id", "releaseId", "formFieldId", "connFieldId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseConnModelExtend extends CustomFormReleaseConnModel {

    @ApiModelProperty(value = "字段名称", position = 1)
    private String fieldName;

    @ApiModelProperty(value = "数据来源", position = 2)
    private String connFieldSource;

    @ApiModelProperty(value = "数据来源名称", position = 3)
    private String connFieldSourceName;

    @ApiModelProperty(value = "关联接口类型", position = 4)
    private ConnTypeEnum connTypeEnum;

}
