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
 * 学生等级考试情况JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-29
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_gxxs_stu_level_eaxm_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuLevelEaxmInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 年级
     */
    private String nj;

    /**
     * 学号
     */
    private String xh;

    /**
     * user_xh
     */
    private String userXh;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 班级代码
     */
    private String bjdm;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 所属院系
     */
    private String yxId;

    /**
     * 学院名称
     */
    private String college;

    /**
     * 所属专业
     */
    private String zyId;

    /**
     * 专业名称
     */
    private String major;

    /**
     * 等级类别id
     */
    private String lbId;

    /**
     * 等级id
     */
    private String djId;

    /**
     * 等级简称
     */
    private String shortName;

    /**
     * 成绩
     */
    private BigDecimal score;

    /**
     * 综合成绩
     */
    private String compreScore;

    /**
     * 报名时间
     */
    private String signTime;

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