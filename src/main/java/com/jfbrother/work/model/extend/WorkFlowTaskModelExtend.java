package com.jfbrother.work.model.extend;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.work.model.WorkFlowTaskModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  流程环节实例传输对象
* @author chenyl@jfbrother.com 2022-06-27
*/
@Data
@ApiModel("流程环节实例传输对象extend")
@JSONType(orders = { "id", "fillId", "taskId", "taskName", "mobileUrl", "pcUrl", "assigneeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class WorkFlowTaskModelExtend extends WorkFlowTaskModel {

    @ApiModelProperty(value = "办理时长", position = 6)
    private String diffTimes;
}
