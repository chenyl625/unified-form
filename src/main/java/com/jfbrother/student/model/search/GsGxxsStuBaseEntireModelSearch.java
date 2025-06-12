package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuBaseEntireModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教务系统里的在校生传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Data
@ApiModel("教务系统里的在校生传输对象search")
@JSONType(orders = {"etlJfuuid", "ssbjId", "xm", "xb", "sfzh", "csrq", "mzdmId", "jgId", "sysfId", "bjmc", "yxId", "zyId", "xz", "nj", "xh", "rxnj", "rxsj", "studentPhone", "zzmmId", "xjzt", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuBaseEntireModelSearch extends GsGxxsStuBaseEntireModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
    @ApiModelProperty(value = "通用查询条件", position = 3)
    private String keyWords;
}
