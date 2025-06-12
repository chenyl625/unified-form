package com.jfbrother.digitalPortraitSnap.model.param;

import com.jfbrother.digitalPortraitSnap.model.GsDataUseReferenceModel;
import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 数据使用参照,快照系统取数依据传输对象
 * @author xinz 2023-03-30
 */
@Data
@ApiModel("数据使用参照,快照系统取数依据传输对象param")
@JSONType(orders = { "id", "templateName", "startTime", "endTime", "bjdm", "xn", "xq", "status", "deleteFlag", "remark", "orderBy", "createId", "createTime", "updateId", "updateTime"})
public class GsDataUseReferenceModelParam extends GsDataUseReferenceModel {

}
