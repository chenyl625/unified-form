package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsYktxtPersonConsumeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  个人消费记录传输对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ApiModel("个人消费记录传输对象search")
@JSONType(orders = { "etlJfuuid", "id", "code", "bookkeepdate", "dealtime", "feepaymentway", "businessdeptcode", "mondeal", "mondiscount", "businessnum", "businessname", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsYktxtPersonConsumeModelSearch extends GsYktxtPersonConsumeModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
    @ApiModelProperty(value = "查询年度", position = 3)
    private String queryYear;
}
