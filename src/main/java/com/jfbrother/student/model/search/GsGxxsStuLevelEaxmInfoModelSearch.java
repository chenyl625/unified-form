package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuLevelEaxmInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生等级考试情况传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生等级考试情况传输对象search")
@JSONType(orders = { "etlJfuuid", "nj", "xh", "userXh", "xm", "sex", "sfzh", "bjdm", "bjmc", "yxId", "college", "zyId", "major", "lbId", "djId", "shortName", "score", "compreScore", "signTime", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuLevelEaxmInfoModelSearch extends GsGxxsStuLevelEaxmInfoModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
