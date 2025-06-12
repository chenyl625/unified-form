package com.jfbrother.student.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  在借记录传输对象
* @author chenyl@jfbrother.com 2022-09-02
*/
@Data
@ApiModel("在借记录传输对象")
@JSONType(orders = { "etlJfuuid", "loanId", "name", "userBarcode", "title", "tmh", "locationName", "lendDate", "returnDate", "renewDate", "renewTimes", "loanType", "loanMode", "xn", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsLibraryBorrowingModel {
    @ApiModelProperty(value = "etlJfuuid", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "借阅id", position = 1)
    private Integer loanId;

    @ApiModelProperty(value = "姓名", position = 2)
    private String name;

    @ApiModelProperty(value = "读者条码", position = 3)
    private String userBarcode;

    @ApiModelProperty(value = "题名", position = 4)
    private String title;

    @ApiModelProperty(value = "条形码", position = 5)
    private String tmh;

    @ApiModelProperty(value = "所属馆藏name", position = 6)
    private String locationName;

    @ApiModelProperty(value = "借出日期", position = 7)
    private String lendDate;

    @ApiModelProperty(value = "应还日期", position = 8)
    private String returnDate;

    @ApiModelProperty(value = "续借日期", position = 9)
    private String renewDate;

    @ApiModelProperty(value = "续借次数", position = 10)
    private Integer renewTimes;

    @ApiModelProperty(value = "操作类型 0表示借阅 1表示续借 2表示催还", position = 11)
    private String loanType;

    @ApiModelProperty(value = "借阅方式 0普通借阅，1委托借阅，2预约借阅，3pda借阅，4物流到家", position = 12)
    private String loanMode;

    @ApiModelProperty(value = "学年", position = 13)
    private String xn;

    @ApiModelProperty(value = "业务主键", position = 14)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 15)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 16)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 17)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 18)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 19)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 20)
    private String etlCheckDate;

}
