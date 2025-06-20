package com.jfbrother.dataCenter.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsKyPaperInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  科研论文信息传输对象
* @author chenyl@jfbrother.com 2022-09-05
*/
@Data
@ApiModel("科研论文信息传输对象extend")
@JSONType(orders = { "etlJfuuid", "unitId", "unitCode", "unitName", "name", "publishDate", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "paperModeId", "paperModeName", "publishUnit", "wordNumber", "authorNumber", "publishRangeId", "publishRange", "projectSourceId", "projectSourceName", "issn", "productAbstract", "firstAuthorName", "firstAuthorCode", "members", "checkStatusId", "checkStatusName", "operateTime", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPaperInfoModelExtend extends GsKyPaperInfoModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
