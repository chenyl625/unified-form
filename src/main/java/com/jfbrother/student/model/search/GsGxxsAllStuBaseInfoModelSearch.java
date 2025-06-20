package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsAllStuBaseInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  所有学生基本数据子类传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("所有学生基本数据子类传输对象search")
@JSONType(orders = { "etlJfuuid", "xh", "xm", "jgId", "sfzh", "gkksh", "mzdmId", "xmpy", "csrq", "xydm", "xymc", "xb", "sydw", "gatdmId", "zzmmId", "zzmmMc", "bh", "xz", "bjmc", "sfzx", "xjzt", "xjztMc", "zydm", "zymc", "rxny", "rxsj", "xznj", "xslb", "rxnj", "bjnj", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsAllStuBaseInfoModelSearch extends GsGxxsAllStuBaseInfoModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
