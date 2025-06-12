package com.jfbrother.dataCenter.model.search;

import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.dataCenter.model.GsKyPaperInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  科研论文信息传输对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Data
@ApiModel("科研论文信息传输对象search")
@JSONType(orders = { "etlJfuuid", "unitId", "unitCode", "unitName", "name", "publishDate", "subjectClassId", "subjectClassName", "subjectCode", "subjectName", "paperModeId", "paperModeName", "publishUnit", "wordNumber", "authorNumber", "publishRangeId", "publishRange", "projectSourceId", "projectSourceName", "issn", "productAbstract", "firstAuthorName", "firstAuthorCode", "members", "checkStatusId", "checkStatusName", "operateTime", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsKyPaperInfoModelSearch extends GsKyPaperInfoModel {
    @ApiModelProperty(value = "接口url", position = 1)
    private String apiUrl;
    @ApiModelProperty(value = "是否启用角色", position = 2)
    private Integer izEnableRole;
}
