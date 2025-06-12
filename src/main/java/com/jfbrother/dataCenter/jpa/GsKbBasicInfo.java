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
import java.math.BigDecimal;

/**
 * 教师排课基本信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_kb_basic_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsKbBasicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 开课学年
     */
    private String kkxn;

    /**
     * 开课学期
     */
    private String kkxq;

    /**
     * 课程码
     */
    private String kch;

    /**
     * 上课时间周次
     */
    private String analyseZc;

    /**
     * 单双周（1-单周，2-双周）
     */
    private String dsz;

    /**
     * 学院代码
     */
    private String yxDm;

    /**
     * 上课时间
     */
    private String sksj;

    /**
     * 上课地点
     */
    private String skdd;

    /**
     * 上课人数
     */
    private String skrs;

    /**
     * 教室所在校区代码
     */
    private String jsszxqDm;

    /**
     * 教室类型码
     */
    private String jslxDm;

    /**
     * 教师工号
     */
    private String gh;

    /**
     * 教师姓名
     */
    private String jsxm;

    /**
     * 上课班级代码
     */
    private String skbjDm;

    /**
     * 上课班级名称
     */
    private String skbjName;

    /**
     * 课程名称
     */
    private String kcmc;

    /**
     * 教室号
     */
    private String jsh;

    /**
     * 起始周
     */
    private String startWeek;

    /**
     * 终止周
     */
    private String endWeek;

    /**
     * 总学分
     */
    private BigDecimal zxf;

    /**
     * 总学时
     */
    private BigDecimal zxs;

    /**
     * 实验学时
     */
    private Integer syxs;

    /**
     * 上课星期
     */
    private String skxq;

    /**
     * jcinfo
     */
    private String jcinfo;

    /**
     * 周次
     */
    private String stimezc;

    /**
     * 上课班级
     */
    private String realSkbj;

    /**
     * 课程类别
     */
    private String kclb;

    /**
     * 课程类别名称
     */
    private String kclbName;

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