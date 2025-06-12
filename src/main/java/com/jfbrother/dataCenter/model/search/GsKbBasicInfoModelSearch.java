package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsKbBasicInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师排课基本信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("教师排课基本信息传输对象search")
@JSONType(orders = { "etlJfuuid", "kkxn", "kkxq", "kch", "analyseZc", "dsz", "yxDm", "sksj", "skdd", "skrs", "jsszxqDm", "jslxDm", "gh", "jsxm", "skbjDm", "skbjName", "kcmc", "jsh", "startWeek", "endWeek", "zxf", "zxs", "syxs", "skxq", "jcinfo", "stimezc", "realSkbj", "kclb", "kclbName", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKbBasicInfoModelSearch extends GsKbBasicInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
