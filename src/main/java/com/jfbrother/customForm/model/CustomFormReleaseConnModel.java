package com.jfbrother.customForm.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义表单发布对应接口传输对象
 *
 * @author chenyl@jfbrother.com 2022-07-06
 */
@Data
@ApiModel("自定义表单发布对应接口传输对象")
@JSONType(orders = {"id", "releaseId", "formFieldId", "isField", "connId", "connFieldName", "isLink", "linkName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class CustomFormReleaseConnModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "发布id", position = 1)
    private String releaseId;

    @ApiModelProperty(value = "表单字段id", position = 2)
    private String formFieldId;

    @ApiModelProperty(value = "是否为表单子表字段", position = 3)
    private Integer isChildField;

    @ApiModelProperty(value = "表单字段子表id", position = 4)
    private String childId;

    @ApiModelProperty(value = "是否关联接口字段", position = 3)
    private Integer isField;

    @ApiModelProperty(value = "接口id", position = 4)
    private String connId;

    @ApiModelProperty(value = "接口字段名称", position = 5)
    private String connFieldName;

    @ApiModelProperty(value = "是否关联环节", position = 6)
    private Integer isLink;

    @ApiModelProperty(value = "环节名称", position = 7)
    private String linkName;

    @ApiModelProperty(value = "关联接口类型", position = 7)
    private Integer connType;

    @ApiModelProperty(value = "排序号", position = 8)
    private Integer sortNum;

    @ApiModelProperty(value = "状态码", position = 9)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 10)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 11)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 12)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 13)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 14)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 15)
    private String updateUser;

}
