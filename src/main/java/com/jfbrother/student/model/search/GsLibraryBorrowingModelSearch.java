package com.jfbrother.student.model.search;

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
@ApiModel("在借记录传输对象search")
@JSONType(orders = { "etlJfuuid", "loanId", "name", "userBarcode", "title", "tmh", "locationName", "lendDate", "returnDate", "renewDate", "renewTimes", "loanType", "loanMode", "xn", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsLibraryBorrowingModelSearch extends GsLibraryBorrowingModel {
    @ApiModelProperty(value = "是否启用角色", position = 1)
    private Integer izEnableRole;
    @ApiModelProperty(value = "接口url", position = 2)
    private String apiUrl;
}
