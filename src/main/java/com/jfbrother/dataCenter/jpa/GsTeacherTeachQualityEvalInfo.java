package com.jfbrother.dataCenter.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
*  教师维度的教学质量评价信息JPA对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_teacher_teach_quality_eval_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherTeachQualityEvalInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * schedule_detail_id
    */
    private Long scheduleDetailId;

    /**
    * 指标类型
    */
    private Integer zblx;

    /**
    * 教师是否迟到 1是 2否
    */
    private Integer jscd;

    /**
    * 教师是否早退 1是 2否
    */
    private Integer jszt;

    /**
    * 应到人数
    */
    private Integer ydrs;

    /**
    * 实到人数
    */
    private Integer sdrs;

    /**
    * 迟到人数
    */
    private Integer cdrs;

    /**
    * 早退人数
    */
    private Integer ztrs;

    /**
    * 教学态度 1.. 2.. 3..
    */
    private Integer jxtd;

    /**
    * 教学建议
    */
    private String jxjy;

    /**
    * 课堂问题
    */
    private String ktwt;

    /**
    * 教学内容、过程及方法
    */
    private String jxnr;

    /**
    * 其他问题
    */
    private String qtwt;

    /**
    * 评价教师
    */
    private String pjjs;

    /**
    * 评价时间
    */
    private String pjsj;

    /**
    * 上课时间
    */
    private String sksj;

    /**
    * 听课节次
    */
    private String tkjc;

    /**
    * 周次
    */
    private Integer weeks;

    /**
    * 课程安排详细删除标志
    */
    private Integer detailDeleted;

    /**
    * schedule_id
    */
    private Long scheduleId;

    /**
    * 第几周
    */
    private Integer weekDay;

    /**
    * 第几节
    */
    private String nodes;

    /**
    * 上课教师工号
    */
    private String skjs;

    /**
    * 地点
    */
    private String dd;

    /**
    * term_id
    */
    private Long termId;

    /**
    * 课程类别1
    */
    private String kclb1;

    /**
    * 课程信息
    */
    private String kc;

    /**
    * jc
    */
    private String jc;

    /**
    * 上课班级
    */
    private String skbj;

    /**
    * zc
    */
    private String zc;

    /**
    * 学分
    */
    private String xf;

    /**
    * 课程类别2
    */
    private String kclb2;

    /**
    * 课程安排的删除标志
    */
    private Integer scheDeleted;

    /**
    * 承担单位
    */
    private String cddw;

    /**
    * 上课人数
    */
    private String skrs;

    /**
    * 总学时
    */
    private String zxs;

    /**
    * 学年
    */
    private String schoolYear;

    /**
    * 学期名称
    */
    private String semester;

    /**
    * 听课类型主键
    */
    private String listenTypeId;

    /**
    * 听课类型
    */
    private String listenTypeName;

    /**
    * 显示顺序
    */
    private Integer orderNo;

    /**
    * 颜色
    */
    private String color;

    /**
    * 教师信息
    */
    private String js;

    /**
    * 课程编号
    */
    private String kcbh;

    /**
    * 问题反馈接收人
    */
    private String wtfk;

    /**
    * 问题反馈状态 0 未读 1已读
    */
    private Integer wtfkzt;

    /**
    * 是否为专业评价表
    */
    private Integer zypjb;

    /**
    * 上课教师姓名
    */
    private String skjsXm;

    /**
    * 教学得分
    */
    private String jxdf;

    /**
    * 学情得分
    */
    private String xqdf;

    /**
    * 本人是否已阅
    */
    private Integer readed;

    /**
    * 是否学情评价表 0不是 1是
    */
    private Integer xqpjb;

    /**
    * 业务主键
    */
    private String etlPri;

    /**
    * gp删除标志位 1：删除 0未删除
    */
    private Integer etlIsDelete;

    /**
    * gp是否已完成数据质检标志位 1：是 0：否
    */
    private Integer etlIsChecked;

    /**
    * 校验唯一性
    */
    private String etlMd5;

    /**
    * 多主键表使用
    */
    private String etlKeyMd5;

    /**
    * 变动类型
    */
    private String etlCheckType;

    /**
    * 变动时间
    */
    private String etlCheckDate;


}