package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师基本信息传输对象
* @author chenyl@jfbrother.com 2022-09-09
*/
@Data
@ApiModel("教师基本信息传输对象search")
@JSONType(orders = { "etlJfuuid", "xm", "gh", "cym", "xb", "xrzwDm", "csrq", "sfzh", "mzDm", "sj", "isTeach", "isDoubleTeacher", "bmh", "type", "email", "zzmmDm", "phoneNum", "officePhone", "unicomShortPhone", "unicomPhone", "mobileShortPhone", "mobilePhone", "peopleSort", "sortNum", "jg", "xyzjm", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherBaseInfoModelSearch extends GsTeacherBaseInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "关键字", position = 2)
    private String keyWords;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;

}
