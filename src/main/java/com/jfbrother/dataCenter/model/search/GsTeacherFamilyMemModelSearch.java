package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherFamilyMemModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师家庭成员传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师家庭成员传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "relationCode", "relationName", "memberName", "phone", "memberCompany", "address", "zzmmCode", "zzmmName", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherFamilyMemModelSearch extends GsTeacherFamilyMemModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
