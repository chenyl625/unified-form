package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuDormModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生住宿信息传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生住宿信息传输对象search")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "bedCode", "bedroomArea", "bedroomLou", "bedroomFloor", "bedroomName", "ifAir", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormModelSearch extends GsGxxsStuDormModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
