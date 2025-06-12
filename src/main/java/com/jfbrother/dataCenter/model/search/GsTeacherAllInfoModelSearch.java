package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsTeacherAllInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师全部信息传输对象
* @author chenyl@jfbrother.com 2022-09-09
*/
@Data
@ApiModel("教师全部信息传输对象search")
@JSONType(orders = { "etlJfuuid", "gh", "rsType", "name", "cym", "pinyin", "sex", "gj", "sfzType", "sfzh", "birth", "age", "jg", "birthPlace", "nowHj", "nationName", "topEdu", "topDegree", "zzmmName", "hyzk", "belief", "byxx", "byzy", "isDouble", "deptName", "partyTime", "oficalPhone", "telephone", "email", "address", "workTime", "schoolTime", "coefficient", "conStartTime", "conEndTime", "employTime", "positionType", "positionLevel", "conStatus", "teaSource", "teaType", "employType", "techPosition", "curPosition", "schoolEdu", "schoolDegree", "isQuali", "isTeach", "teachType", "isOverStudy", "unicomPhone", "unicomShortPhone", "mobilePhone", "mobileShortPhone", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherAllInfoModelSearch extends GsTeacherAllInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "关键字", position = 2)
    private String keyWords;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;

    private String roleId;

    private String deptName;
}
