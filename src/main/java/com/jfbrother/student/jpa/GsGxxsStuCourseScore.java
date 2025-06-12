package com.jfbrother.student.jpa;

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
 * 学生课程成绩表JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-29
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_gxxs_stu_course_score")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuCourseScore implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 学号
     */
    private String xh;

    /**
     * 修读学年
     */
    private String xn;

    /**
     * 修读学期
     */
    private String xq;

    /**
     * 学分
     */
    private BigDecimal zxf;

    /**
     * 课程号
     */
    private String kch;

    /**
     * 课程名称
     */
    private String kcmc;

    /**
     * 辅修标志
     */
    private String fxFlag;

    /**
     * 考核方式
     */
    private String khfsDm;

    /**
     * 考试成绩
     */
    private String kscj;

    /**
     * 重修补修标志
     */
    private String cxbkFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 绩点
     */
    private BigDecimal jd;

    /**
     * 学分绩点
     */
    private BigDecimal xfj;

    /**
     * 班级代码
     */
    private String bjdm;

    /**
     * 所属专业
     */
    private String zyId;

    /**
     * 性别
     */
    private String xb;

    /**
     * 考试成绩num类型
     */
    private BigDecimal kscjNum;

    /**
     * 绩点取整
     */
    private Integer floorJd;

    /**
     * 课程类别
     */
    private String kclbName;

    /**
     * 课程类别2
     */
    private String kclb2Name;

    /**
     * 课程类别3
     */
    private String kclb3Name;

    /**
     * 所属学院代码
     */
    private String yxDm;

    /**
     * 已取得学分
     */
    private BigDecimal getXf;

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