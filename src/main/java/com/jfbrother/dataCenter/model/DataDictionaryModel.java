package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典表传输对象
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@Data
@ApiModel("数据字典表传输对象")
@JSONType(orders = {"id", "name", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class DataDictionaryModel {
    @ApiModelProperty(value = "主键", position = 0)
    private String id;

    @ApiModelProperty(value = "名称", position = 1)
    private String name;

    @ApiModelProperty(value = "备注", position = 2)
    private String remark;

    @ApiModelProperty(value = "排序号", position = 2)
    private Integer sortNum;

    @ApiModelProperty(value = "同步的标准字典维护的id", position = 2)
    private String infoId;

    @ApiModelProperty(value = "状态码", position = 3)
    private Integer statusNum;

    @ApiModelProperty(value = "数据版本，乐观锁", position = 4)
    private Long versionNum;

    @ApiModelProperty(value = "逻辑删除标识符", position = 5)
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间", position = 6)
    private Long createTime;

    @ApiModelProperty(value = "创建人", position = 7)
    private String createUser;

    @ApiModelProperty(value = "更新时间", position = 8)
    private Long updateTime;

    @ApiModelProperty(value = "更新人", position = 9)
    private String updateUser;

}
