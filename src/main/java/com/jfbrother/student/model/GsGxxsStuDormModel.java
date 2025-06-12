package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  学生住宿信息传输对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ApiModel("学生住宿信息传输对象")
@JSONType(orders = { "etlJfuuid", "studentCode", "studentName", "studentPhone", "bedCode", "bedroomArea", "bedroomLou", "bedroomFloor", "bedroomName", "ifAir", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuDormModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学号", position = 1)
    private String studentCode;

    @ApiModelProperty(value = "学生姓名", position = 2)
    private String studentName;

    @ApiModelProperty(value = "联系电话", position = 3)
    private String studentPhone;

    @ApiModelProperty(value = "床位号", position = 4)
    private Integer bedCode;

    @ApiModelProperty(value = "寝室区域", position = 5)
    private String bedroomArea;

    @ApiModelProperty(value = "寝室楼号", position = 6)
    private String bedroomLou;

    @ApiModelProperty(value = "寝室楼层", position = 7)
    private Integer bedroomFloor;

    @ApiModelProperty(value = "寝室名称", position = 8)
    private String bedroomName;

    @ApiModelProperty(value = "是否有空调（1：有 ，0：没有）", position = 9)
    private Integer ifAir;

    @ApiModelProperty(value = "业务主键", position = 10)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 11)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 12)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 13)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 14)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 15)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 16)
    private String etlCheckDate;

}
