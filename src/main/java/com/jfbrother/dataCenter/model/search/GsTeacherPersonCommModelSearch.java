package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherPersonCommModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师个人通讯方式传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师个人通讯方式传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "email", "bgPhone", "telephone", "ltLong", "ltShort", "ydLong", "ydShort", "qq", "address", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherPersonCommModelSearch extends GsTeacherPersonCommModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
