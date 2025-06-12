package com.jfbrother.customForm.model.param;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.customForm.model.CustomFormReleaseConnModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 自定义表单发布对应接口传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Data
@ApiModel("自定义表单发布对应接口传输对象param")
@JSONType(orders = {"id", "releaseId", "formFieldId", "connFieldId", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseConnModelParam extends CustomFormReleaseConnModel {

}
