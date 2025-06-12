package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
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
@ApiModel("教师详细信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "gh", "nowJobPositionDm", "gwLevel", "zyjszwDm", "zgxlDm", "zzmmDm", "zgxwDm", "zgxlName", "enterSchoolTime", "zyzgLevel", "gwTypeDm", "graduateSchool", "type", "positionTypeCode", "positionTypeName", "bmh", "nowPosition", "peopleSortCode", "peopleSort", "zyjszwAcquireDate", "bankCard", "busBankCard", "yglbDm", "yglbName", "teachDeptCode", "constructionBankCard", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherDetailInfoModelExtend extends GsTeacherDetailInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
