package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师基本信息传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-29
 */
@Data
@ApiModel("教师基本信息传输对象extend")
@JSONType(orders = {"etlJfuuid", "xm", "gh", "cym", "xb", "xrzwDm", "csrq", "sfzh", "mzDm", "sj", "isTeach", "isDoubleTeacher", "bmh", "type", "email", "zzmmDm", "phoneNum", "officePhone", "unicomShortPhone", "unicomPhone", "mobileShortPhone", "mobilePhone", "peopleSort", "sortNum", "jg", "xyzjm", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherBaseInfoModelExtend extends GsTeacherBaseInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
    @ApiModelProperty(value = "用户id", position = 1)
    private String userId;
    @ApiModelProperty(value = "部门名称", position = 2)
    private String bmmc;
}
