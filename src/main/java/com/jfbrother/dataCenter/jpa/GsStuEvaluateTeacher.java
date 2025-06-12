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
*  学评教-学生评价老师汇总表JPA对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_stu_evaluate_teacher")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsStuEvaluateTeacher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 学年
    */
    private String xn;

    /**
    * 学期
    */
    private String xq;

    /**
    * 教师id
    */
    private String jsid;

    /**
    * 综合得分
    */
    private BigDecimal totalScore;

    /**
    * 教职工号
    */
    private String gh;

    /**
    * 课程名称
    */
    private String kcmc;

    /**
    * 课程id
    */
    private String kcid;

    /**
    * 参加评定人数
    */
    private String cprs;

    /**
    * 教师姓名
    */
    private String jsxm;

    /**
    * 平均评估得分
    */
    private BigDecimal avgPgdf;

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