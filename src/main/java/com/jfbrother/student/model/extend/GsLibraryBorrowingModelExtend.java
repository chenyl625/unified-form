package com.jfbrother.student.model.extend;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.jfbrother.student.model.GsLibraryBorrowingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  在借记录传输对象
* @author chenyl@jfbrother.com 2022-09-02
*/
@Data
@ApiModel("在借记录传输对象extend")
@JSONType(orders = { "etlJfuuid", "loanId", "name", "userBarcode", "title", "tmh", "locationName", "lendDate", "returnDate", "renewDate", "renewTimes", "loanType", "loanMode", "xn", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsLibraryBorrowingModelExtend extends GsLibraryBorrowingModel {
    @ApiModelProperty(value = "数据字段", position = 0, hidden = true)
    private JSONObject data;
}
