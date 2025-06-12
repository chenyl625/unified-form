package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
*  科研论文信息传输对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Data
@ApiModel("科研论文信息传输对象")
@JSONType(orders = { "etlJfuuid", "unitId", "unitCode", "unitName", "name", "publishDate", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "paperModeId", "paperModeName", "publishUnit", "wordNumber", "authorNumber", "publishRangeId", "publishRange", "projectSourceId", "projectSourceName", "issn", "productAbstract", "firstAuthorName", "firstAuthorCode", "members", "checkStatusId", "checkStatusName", "operateTime", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPaperInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "unit_id", position = 1)
    private String unitId;

    @ApiModelProperty(value = "单位代码", position = 2)
    private String unitCode;

    @ApiModelProperty(value = "单位名称", position = 3)
    private String unitName;

    @ApiModelProperty(value = "论文名称", position = 4)
    private String name;

    @ApiModelProperty(value = "publish_date", position = 5)
    private String publishDate;

    @ApiModelProperty(value = "subject_class_id", position = 6)
    private String subjectClassId;

    @ApiModelProperty(value = "科目分类名称", position = 7)
    private String subjectClassName;

    @ApiModelProperty(value = "学科代码", position = 8)
    private String subjectCode;

    @ApiModelProperty(value = "科目名称", position = 9)
    private String subjectName;

    @ApiModelProperty(value = "paper_mode_id", position = 10)
    private String paperModeId;

    @ApiModelProperty(value = "论文类型", position = 11)
    private String paperModeName;

    @ApiModelProperty(value = "publish_unit", position = 12)
    private String publishUnit;

    @ApiModelProperty(value = "word_number", position = 13)
    private BigDecimal wordNumber;

    @ApiModelProperty(value = "author_number", position = 14)
    private Integer authorNumber;

    @ApiModelProperty(value = "publish_range_id", position = 15)
    private String publishRangeId;

    @ApiModelProperty(value = "publish_range", position = 16)
    private String publishRange;

    @ApiModelProperty(value = "project_source_id", position = 17)
    private String projectSourceId;

    @ApiModelProperty(value = "项目来源", position = 18)
    private String projectSourceName;

    @ApiModelProperty(value = "issn", position = 19)
    private String issn;

    @ApiModelProperty(value = "product_abstract", position = 20)
    private String productAbstract;

    @ApiModelProperty(value = "first_author_name", position = 21)
    private String firstAuthorName;

    @ApiModelProperty(value = "first_author_code", position = 22)
    private String firstAuthorCode;

    @ApiModelProperty(value = "所有作者", position = 23)
    private String members;

    @ApiModelProperty(value = "check_status_id", position = 24)
    private String checkStatusId;

    @ApiModelProperty(value = "审核状态", position = 25)
    private String checkStatusName;

    @ApiModelProperty(value = "operate_time", position = 26)
    private String operateTime;

    @ApiModelProperty(value = "主键", position = 27)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 28)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 29)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 30)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 31)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 32)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 33)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 34)
    private String etlCheckDate;

}
