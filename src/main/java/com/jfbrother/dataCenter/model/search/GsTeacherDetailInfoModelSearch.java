package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherDetailInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师详细信息传输对象
* @author chenyl@jfbrother.com 2022-09-09
*/
@Data
@ApiModel("教师详细信息传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "nowJobPositionDm", "gwLevel", "zyjszwDm", "zgxlDm", "zzmmDm", "zgxwDm", "zgxlName", "enterSchoolTime", "zyzgLevel", "gwTypeDm", "graduateSchool", "type", "positionTypeCode", "positionTypeName", "bmh", "nowPosition", "peopleSortCode", "peopleSort", "zyjszwAcquireDate", "bankCard", "busBankCard", "yglbDm", "yglbName", "teachDeptCode", "constructionBankCard", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherDetailInfoModelSearch extends GsTeacherDetailInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
