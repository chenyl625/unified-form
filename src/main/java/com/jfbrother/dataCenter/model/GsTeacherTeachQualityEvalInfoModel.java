package com.jfbrother.dataCenter.model;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*  教师维度的教学质量评价信息传输对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ApiModel("教师维度的教学质量评价信息传输对象")
@JSONType(orders = { "etlJfuuid", "scheduleDetailId", "zblx", "jscd", "jszt", "ydrs", "sdrs", "cdrs", "ztrs", "jxtd", "jxjy", "ktwt", "jxnr", "qtwt", "pjjs", "pjsj", "sksj", "tkjc", "weeks", "detailDeleted", "scheduleId", "weekDay", "nodes", "skjs", "dd", "termId", "kclb1", "kc", "jc", "skbj", "zc", "xf", "kclb2", "scheDeleted", "cddw", "skrs", "zxs", "schoolYear", "semester", "listenTypeId", "listenTypeName", "orderNo", "color", "js", "kcbh", "wtfk", "wtfkzt", "zypjb", "skjsXm", "jxdf", "xqdf", "readed", "xqpjb", "id", "etlPri", "etlIsDelete", "etlIsChecked", "etlMd5", "etlKeyMd5", "etlCheckType", "etlCheckDate"})
public class GsTeacherTeachQualityEvalInfoModel {
    @ApiModelProperty(value = "id", position = 0)
    private String etlJfuuid;

    @ApiModelProperty(value = "schedule_detail_id", position = 1)
    private Long scheduleDetailId;

    @ApiModelProperty(value = "指标类型", position = 2)
    private Integer zblx;

    @ApiModelProperty(value = "教师是否迟到 1是 2否", position = 3)
    private Integer jscd;

    @ApiModelProperty(value = "教师是否早退 1是 2否", position = 4)
    private Integer jszt;

    @ApiModelProperty(value = "应到人数", position = 5)
    private Integer ydrs;

    @ApiModelProperty(value = "实到人数", position = 6)
    private Integer sdrs;

    @ApiModelProperty(value = "迟到人数", position = 7)
    private Integer cdrs;

    @ApiModelProperty(value = "早退人数", position = 8)
    private Integer ztrs;

    @ApiModelProperty(value = "教学态度 1.. 2.. 3..", position = 9)
    private Integer jxtd;

    @ApiModelProperty(value = "教学建议", position = 10)
    private String jxjy;

    @ApiModelProperty(value = "课堂问题", position = 11)
    private String ktwt;

    @ApiModelProperty(value = "教学内容、过程及方法", position = 12)
    private String jxnr;

    @ApiModelProperty(value = "其他问题", position = 13)
    private String qtwt;

    @ApiModelProperty(value = "评价教师", position = 14)
    private String pjjs;

    @ApiModelProperty(value = "评价时间", position = 15)
    private String pjsj;

    @ApiModelProperty(value = "上课时间", position = 16)
    private String sksj;

    @ApiModelProperty(value = "听课节次", position = 17)
    private String tkjc;

    @ApiModelProperty(value = "周次", position = 18)
    private Integer weeks;

    @ApiModelProperty(value = "课程安排详细删除标志", position = 19)
    private Integer detailDeleted;

    @ApiModelProperty(value = "schedule_id", position = 20)
    private Long scheduleId;

    @ApiModelProperty(value = "第几周", position = 21)
    private Integer weekDay;

    @ApiModelProperty(value = "第几节", position = 22)
    private String nodes;

    @ApiModelProperty(value = "上课教师工号", position = 23)
    private String skjs;

    @ApiModelProperty(value = "地点", position = 24)
    private String dd;

    @ApiModelProperty(value = "term_id", position = 25)
    private Long termId;

    @ApiModelProperty(value = "课程类别1", position = 26)
    private String kclb1;

    @ApiModelProperty(value = "课程信息", position = 27)
    private String kc;

    @ApiModelProperty(value = "jc", position = 28)
    private String jc;

    @ApiModelProperty(value = "上课班级", position = 29)
    private String skbj;

    @ApiModelProperty(value = "zc", position = 30)
    private String zc;

    @ApiModelProperty(value = "学分", position = 31)
    private String xf;

    @ApiModelProperty(value = "课程类别2", position = 32)
    private String kclb2;

    @ApiModelProperty(value = "课程安排的删除标志", position = 33)
    private Integer scheDeleted;

    @ApiModelProperty(value = "承担单位", position = 34)
    private String cddw;

    @ApiModelProperty(value = "上课人数", position = 35)
    private String skrs;

    @ApiModelProperty(value = "总学时", position = 36)
    private String zxs;

    @ApiModelProperty(value = "学年", position = 37)
    private String schoolYear;

    @ApiModelProperty(value = "学期名称", position = 38)
    private String semester;

    @ApiModelProperty(value = "听课类型主键", position = 39)
    private String listenTypeId;

    @ApiModelProperty(value = "听课类型", position = 40)
    private String listenTypeName;

    @ApiModelProperty(value = "显示顺序", position = 41)
    private Integer orderNo;

    @ApiModelProperty(value = "颜色", position = 42)
    private String color;

    @ApiModelProperty(value = "教师信息", position = 43)
    private String js;

    @ApiModelProperty(value = "课程编号", position = 44)
    private String kcbh;

    @ApiModelProperty(value = "问题反馈接收人", position = 45)
    private String wtfk;

    @ApiModelProperty(value = "问题反馈状态 0 未读 1已读", position = 46)
    private Integer wtfkzt;

    @ApiModelProperty(value = "是否为专业评价表", position = 47)
    private Integer zypjb;

    @ApiModelProperty(value = "上课教师姓名", position = 48)
    private String skjsXm;

    @ApiModelProperty(value = "教学得分", position = 49)
    private String jxdf;

    @ApiModelProperty(value = "学情得分", position = 50)
    private String xqdf;

    @ApiModelProperty(value = "本人是否已阅", position = 51)
    private Integer readed;

    @ApiModelProperty(value = "是否学情评价表 0不是 1是", position = 52)
    private Integer xqpjb;

    @ApiModelProperty(value = "主键", position = 53)
    private String id;

    @ApiModelProperty(value = "业务主键", position = 54)
    private String etlPri;

    @ApiModelProperty(value = "gp删除标志位 1：删除 0未删除", position = 55)
    private Integer etlIsDelete;

    @ApiModelProperty(value = "gp是否已完成数据质检标志位 1：是 0：否", position = 56)
    private Integer etlIsChecked;

    @ApiModelProperty(value = "校验唯一性", position = 57)
    private String etlMd5;

    @ApiModelProperty(value = "多主键表使用", position = 58)
    private String etlKeyMd5;

    @ApiModelProperty(value = "变动类型", position = 59)
    private String etlCheckType;

    @ApiModelProperty(value = "变动时间", position = 60)
    private String etlCheckDate;

}
