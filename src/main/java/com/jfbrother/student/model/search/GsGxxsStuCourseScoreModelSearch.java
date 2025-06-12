package com.jfbrother.student.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsGxxsStuCourseScoreModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学生课程成绩表传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-29
 */
@Data
@ApiModel("学生课程成绩表传输对象search")
@JSONType(orders = {"etlJfuuid", "xh", "xn", "xq", "zxf", "kch", "kcmc", "fxFlag", "khfsDm", "kscj", "cxbkFlag", "remark", "jd", "xfj", "bjdm", "zyId", "xb", "kscjNum", "floorJd", "kclbName", "kclb2Name", "kclb3Name", "yxDm", "getXf", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuCourseScoreModelSearch extends GsGxxsStuCourseScoreModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
    @ApiModelProperty(value = "班级代码", position = 2)
    private String ssbjId;
    @ApiModelProperty(value = "学号", position = 2)
    private String xh;
    @ApiModelProperty(value = "学年", position = 2)
    private String xn;
    @ApiModelProperty(value = "学期", position = 2)
    private String xq;
}
