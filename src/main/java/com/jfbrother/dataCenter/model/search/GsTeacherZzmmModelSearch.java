package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherZzmmModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师政治面貌传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师政治面貌传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "zzmmm", "zzmmName", "joinDate", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherZzmmModelSearch extends GsTeacherZzmmModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
