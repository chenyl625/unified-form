package com.jfbrother.student.model;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

/**
 *  学生体质测试成绩数据传输对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ApiModel("学生体质测试成绩数据传输对象")
@JSONType(orders = { "etlJfuuid", "rxnj", "ssbjM", "bjmc", "xjzt", "mzM", "xm", "xh", "xbM", "csrq", "semesterid", "height", "weight", "weightScore", "weightLevel", "vitalCapacityResult", "vitalCapacityScore", "vitalCapacityLevel", "fiftyMeterRunResult", "fiftyMeterRunScore", "fiftyMeterRunLevel", "standingLongJumpResult", "standingLongJumpScore", "standingLongJumpLevel", "sittingForwardFlexionResult", "sittingForwardFlexionScore", "sittingForwardFlexionLevel", "eightHundredMeterRunResult", "eightHundredMeterRunScore", "eightHundredMeterRunLevel", "oneThousandMeterRunResult", "oneThousandMeterRunScore", "oneThousandMeterRunLevel", "abdominalCurlResult", "abdominalCurlResultScore", "abdominalCurlLevel", "pullUpResult", "pullUpScore", "pullUpLevel", "lyslzy", "lyslyy", "qgbzzy", "qgbzyy", "cjzy", "cjyy", "standardScore", "plus1", "plus2", "finalscore", "totalLevel", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsGxxsStuPhysiqueResultDataModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "入学年级", position = 1)
    private String rxnj;

    @ApiModelProperty(value = "班级代码", position = 2)
    private String ssbjM;

    @ApiModelProperty(value = "班级名称", position = 3)
    private String bjmc;

    @ApiModelProperty(value = "学籍状态", position = 4)
    private String xjzt;

    @ApiModelProperty(value = "民族代码", position = 5)
    private String mzM;

    @ApiModelProperty(value = "姓名", position = 6)
    private String xm;

    @ApiModelProperty(value = "学号", position = 7)
    private String xh;

    @ApiModelProperty(value = "性别", position = 8)
    private String xbM;

    @ApiModelProperty(value = "出生日期", position = 9)
    private String csrq;

    @ApiModelProperty(value = "学期代码", position = 10)
    private BigDecimal semesterid;

    @ApiModelProperty(value = "身高", position = 11)
    private BigDecimal height;

    @ApiModelProperty(value = "体重", position = 12)
    private BigDecimal weight;

    @ApiModelProperty(value = "体重评分", position = 13)
    private BigDecimal weightScore;

    @ApiModelProperty(value = "体重等级", position = 14)
    private String weightLevel;

    @ApiModelProperty(value = "肺活量", position = 15)
    private BigDecimal vitalCapacityResult;

    @ApiModelProperty(value = "肺活量评分", position = 16)
    private BigDecimal vitalCapacityScore;

    @ApiModelProperty(value = "肺活量等级", position = 17)
    private String vitalCapacityLevel;

    @ApiModelProperty(value = "五十米跑", position = 18)
    private BigDecimal fiftyMeterRunResult;

    @ApiModelProperty(value = "五十米跑评分", position = 19)
    private BigDecimal fiftyMeterRunScore;

    @ApiModelProperty(value = "五十米跑等级", position = 20)
    private String fiftyMeterRunLevel;

    @ApiModelProperty(value = "立定跳远", position = 21)
    private BigDecimal standingLongJumpResult;

    @ApiModelProperty(value = "立定跳远评分", position = 22)
    private BigDecimal standingLongJumpScore;

    @ApiModelProperty(value = "立定跳远等级", position = 23)
    private String standingLongJumpLevel;

    @ApiModelProperty(value = "坐位体前驱", position = 24)
    private BigDecimal sittingForwardFlexionResult;

    @ApiModelProperty(value = "坐位体前屈评分", position = 25)
    private BigDecimal sittingForwardFlexionScore;

    @ApiModelProperty(value = "坐位体前驱等级", position = 26)
    private String sittingForwardFlexionLevel;

    @ApiModelProperty(value = "八百米跑", position = 27)
    private BigDecimal eightHundredMeterRunResult;

    @ApiModelProperty(value = "八百米跑评分", position = 28)
    private BigDecimal eightHundredMeterRunScore;

    @ApiModelProperty(value = "八百米跑等级", position = 29)
    private String eightHundredMeterRunLevel;

    @ApiModelProperty(value = "一千米跑", position = 30)
    private BigDecimal oneThousandMeterRunResult;

    @ApiModelProperty(value = "一千米跑评分", position = 31)
    private BigDecimal oneThousandMeterRunScore;

    @ApiModelProperty(value = "一千米跑等级", position = 32)
    private String oneThousandMeterRunLevel;

    @ApiModelProperty(value = "仰卧起坐", position = 33)
    private BigDecimal abdominalCurlResult;

    @ApiModelProperty(value = "仰卧起坐评分", position = 34)
    private BigDecimal abdominalCurlResultScore;

    @ApiModelProperty(value = "仰卧起坐等级", position = 35)
    private String abdominalCurlLevel;

    @ApiModelProperty(value = "引体向上", position = 36)
    private BigDecimal pullUpResult;

    @ApiModelProperty(value = "引体向上评分", position = 37)
    private BigDecimal pullUpScore;

    @ApiModelProperty(value = "引体向上等级", position = 38)
    private String pullUpLevel;

    @ApiModelProperty(value = "左眼裸眼视力", position = 39)
    private BigDecimal lyslzy;

    @ApiModelProperty(value = "右眼裸眼视力", position = 40)
    private BigDecimal lyslyy;

    @ApiModelProperty(value = "左眼屈光不正", position = 41)
    private BigDecimal qgbzzy;

    @ApiModelProperty(value = "右眼屈光不正", position = 42)
    private BigDecimal qgbzyy;

    @ApiModelProperty(value = "左眼串镜", position = 43)
    private BigDecimal cjzy;

    @ApiModelProperty(value = "右眼串镜", position = 44)
    private BigDecimal cjyy;

    @ApiModelProperty(value = "标准分", position = 45)
    private BigDecimal standardScore;

    @ApiModelProperty(value = "附加分1", position = 46)
    private BigDecimal plus1;

    @ApiModelProperty(value = "附加分2", position = 47)
    private BigDecimal plus2;

    @ApiModelProperty(value = "总分", position = 48)
    private BigDecimal finalscore;

    @ApiModelProperty(value = "总分等级", position = 49)
    private String totalLevel;

    @ApiModelProperty(value = "业务主键", position = 50)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 51)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 52)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 53)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 54)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 55)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 56)
    private String etlCheckDate;

}
