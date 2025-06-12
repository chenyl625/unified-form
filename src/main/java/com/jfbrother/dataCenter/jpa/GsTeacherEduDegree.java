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
 * 教师学历学位JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-09
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_edu_degree")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherEduDegree implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 工号
     */
    private String gh;

    /**
     * 教师类别
     */
    private String rsType;

    /**
     * 最高学历
     */
    private String eduTopCode;

    /**
     * 最高学历名称
     */
    private String eduTopName;

    /**
     * 最高学位
     */
    private String degreeTopCode;

    /**
     * 最高学位名称
     */
    private String degreeTopName;

    /**
     * 获得学历
     */
    private String eduCode;

    /**
     * 获得学历名称
     */
    private String eduName;

    /**
     * 获得学历的国家（地区）
     */
    private String eduCountryCode;

    /**
     * 获得学历的国家（地区）名称
     */
    private String eduCountryName;

    /**
     * 获得学历的院校或机构
     */
    private String eduCollege;

    /**
     * 所学专业
     */
    private String eduMajor;

    /**
     * 入学时间
     */
    private String startTime;

    /**
     * 毕业时间
     */
    private String endTime;

    /**
     * 学位编码
     */
    private String degreeCode;

    /**
     * 学位名称
     */
    private String degreeName;

    /**
     * 获得学位的国家／地区
     */
    private String degreeCountryCode;

    /**
     * 获得学位的国家／地区名称
     */
    private String degreeCountryName;

    /**
     * 获得学位的单位名称
     */
    private String degreeCollege;

    /**
     * 获学位日期
     */
    private String degreeTime;

    /**
     * 学习方式1
     */
    private String styleCode;

    /**
     * 学习方式名称
     */
    private String styleName;

    /**
     * 在学单位类别
     */
    private String unitType;

    /**
     * 主键序号
     */
    private Integer idSort;

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