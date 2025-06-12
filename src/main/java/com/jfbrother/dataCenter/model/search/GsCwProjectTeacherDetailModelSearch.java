package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsCwProjectTeacherDetailModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 项目发放-教职工收入传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Data
@ApiModel("项目发放-教职工收入传输对象search")
@JSONType(orders = {"etlJfuuid", "bh", "xm", "gh", "je", "gxkh", "gwkh", "yglxwb", "yglx", "bz", "nybs", "jfly", "jfrq", "jfspr", "ffnr", "xmktbh", "xmlx", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsCwProjectTeacherDetailModelSearch extends GsCwProjectTeacherDetailModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
