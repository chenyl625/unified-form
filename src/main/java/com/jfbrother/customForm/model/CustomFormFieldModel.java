package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义表单字段传输对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ApiModel("自定义表单字段传输对象")
@JSONType(orders = {"id", "pid", "comment", "defaultValue", "maxLength", "type", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormFieldModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "父id", position = 1)
    private String pid;

    @ApiModelProperty(value = "字段ID", position = 1)
    private String field;

    @ApiModelProperty(value = "类型", position = 5)
    private String fieldType;

    @ApiModelProperty(value = "字段名称", position = 2)
    private String comment;

    @ApiModelProperty(value = "默认值", position = 3)
    private String defaultValue;

    @ApiModelProperty(value = "最长长度", position = 4)
    private Long maxlength;

    @ApiModelProperty(value = "精度", position = 4)
    private Integer fieldPrecision;

    @ApiModelProperty(value = "是否为附件上传组件", position = 4)
    private Integer izFileUpload;

    @ApiModelProperty(value = "排序号", position = 6)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 7)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 8)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 9)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 11)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 13)
    private String updateUser;

}
