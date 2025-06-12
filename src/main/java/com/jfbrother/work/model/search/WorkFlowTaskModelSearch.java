package com.jfbrother.work.model.search;

import com.jfbrother.work.model.WorkFlowTaskModel;
import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;

import lombok.Data;

/**
*  流程环节实例传输对象
* @author chenyl@jfbrother.com 2022-06-27
*/
@Data
@ApiModel("流程环节实例传输对象search")
@JSONType(orders = { "id", "fillId", "taskId", "taskName", "mobileUrl", "pcUrl", "assigneeName", "sortNum", "statusNum", "versionNum", "isDelete", "createTime", "createUser", "updateTime", "updateUser"})
public class WorkFlowTaskModelSearch extends WorkFlowTaskModel {

}
