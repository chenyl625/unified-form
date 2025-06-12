package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsChargeTeacherModel;
import com.jfbrother.student.model.GsGxxsStuBaseEntireModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教务系统里的在校生传输对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ApiModel("教务系统里的在校生传输对象extend")
@JSONType(orders = { "etlJfuuid", "ssbjId", "xm", "xb", "sfzh", "csrq", "mzdmId", "jgId", "sysfId", "bjmc", "yxId", "zyId", "xz", "nj", "xh", "rxnj", "rxsj", "studentPhone", "zzmmId", "xjzt", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuBaseEntireModelExtend extends GsGxxsStuBaseEntireModel {
    @ApiModelProperty(value = "用户id", position = 0)
    private String userId;
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
    @ApiModelProperty(value = "班主任和辅导员信息", position = 20)
    private GsGxxsChargeTeacherModel teacherInfo;
    @ApiModelProperty(value = "院校名称", position = 21)
    private String yxmc;
    @ApiModelProperty(value = "专业名称", position = 22)
    private String zymc;
    @ApiModelProperty(value = "班主任手机", position = 23)
    private String bzrsj;
    @ApiModelProperty(value = "辅导员手机", position = 24)
    private String fdysj;
}
