package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 海康人脸照片数据传输对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Data
@ApiModel("海康人脸照片数据传输对象")
@JSONType(orders = {"etlJfuuid", "code", "facePic", "type", "filePath", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsImportHikFaceInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "学工号", position = 1)
    private String code;

    @ApiModelProperty(value = "人脸照片", position = 2)
    private String facePic;

    @ApiModelProperty(value = "类型(1-学生，2-教师)", position = 3)
    private String type;

    @ApiModelProperty(value = "文件上传地址", position = 4)
    private String filePath;

    @ApiModelProperty(value = "业务主键", position = 5)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 6)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 7)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 8)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 9)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 10)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 11)
    private String etlCheckDate;

}
