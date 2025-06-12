package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuCourseScoreModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生课程成绩表传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生课程成绩表传输对象extend")
@JSONType(orders = { "etlJfuuid", "xh", "xn", "xq", "zxf", "kch", "kcmc", "fxFlag", "khfsDm", "kscj", "cxbkFlag", "remark", "jd", "xfj", "bjdm", "zyId", "xb", "kscjNum", "floorJd", "kclbName", "kclb2Name", "kclb3Name", "yxDm", "getXf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuCourseScoreModelExtend extends GsGxxsStuCourseScoreModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
    @ApiModelProperty(value = "单科班内排名", position = 0)
    private String intraClassRanking;
}
