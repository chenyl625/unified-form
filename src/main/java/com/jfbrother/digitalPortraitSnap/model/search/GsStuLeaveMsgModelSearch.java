package com.jfbrother.digitalPortraitSnap.model.search;

import com.jfbrother.digitalPortraitSnap.model.GsStuLeaveMsgModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *  学生家长/教师留言传输对象
 * @author xinz 2023-03-16
 */
@Data
@ApiModel("学生家长/教师留言传输对象search")
@JSONType(orders = { "id", "code", "name", "type", "leaveTime", "msg", "deleteFlag", "remark", "createTime", "updateTime"})
public class GsStuLeaveMsgModelSearch extends GsStuLeaveMsgModel {

}
