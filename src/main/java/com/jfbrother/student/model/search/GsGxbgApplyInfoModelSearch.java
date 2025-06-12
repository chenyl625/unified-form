package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxbgApplyInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  办事申请表传输对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ApiModel("办事申请表传输对象search")
@JSONType(orders = { "etlJfuuid", "flowStep", "createdUserId", "termId", "deleted", "publishId", "remind", "status", "submitDate", "applyerType", "userCode", "userName", "userType", "termStartDate", "termEndDate", "isCurrent", "collidePublishId", "publishStatus", "publishName", "publishStartDate", "publishEndDate", "tableId", "tabName", "tableTypeId", "tableTypeCode", "tableTypeName", "schoolYear", "semester", "userStatus", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxbgApplyInfoModelSearch extends GsGxbgApplyInfoModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
