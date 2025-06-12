package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师家庭成员传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ApiModel("教师家庭成员传输对象")
@JSONType(orders = {"etlJfuuid", "gh", "rsType", "relationCode", "relationName", "memberName", "phone", "memberCompany", "address", "zzmmCode", "zzmmName", "id", "idSort", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherFamilyMemModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "工号", position = 1)
    private String gh;

    @ApiModelProperty(value = "教师类别", position = 2)
    private String rsType;

    @ApiModelProperty(value = "与本人关系编号", position = 3)
    private String relationCode;

    @ApiModelProperty(value = "与本人关系名称", position = 4)
    private String relationName;

    @ApiModelProperty(value = "成员姓名", position = 5)
    private String memberName;

    @ApiModelProperty(value = "成员手机号", position = 6)
    private String phone;

    @ApiModelProperty(value = "成员工作单位及职务", position = 7)
    private String memberCompany;

    @ApiModelProperty(value = "成员家庭地址", position = 8)
    private String address;

    @ApiModelProperty(value = "成员政治面貌编号", position = 9)
    private String zzmmCode;

    @ApiModelProperty(value = "成员政治面貌名称", position = 10)
    private String zzmmName;

    @ApiModelProperty(value = "主键", position = 11)
    private String id;

    @ApiModelProperty(value = "主键序号", position = 12)
    private Integer idSort;

    @ApiModelProperty(value = "业务主键", position = 13)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 14)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 15)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 16)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 17)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 18)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 19)
    private String etlCheckDate;

}
