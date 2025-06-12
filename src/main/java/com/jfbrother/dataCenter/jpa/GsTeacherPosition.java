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
 * 教师专业技术职务JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_position")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherPosition implements Serializable {
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
     * 专业技术职务编号
     */
    private String positionCode;

    /**
     * 专业技术职务名称
     */
    private String positionName;

    /**
     * 专业技术职务取得时间
     */
    private String acquireDate;

    /**
     * 部门号
     */
    private String bmh;

    /**
     * 职称代码
     */
    private String finalPositionCode;

    /**
     * 职称编号最后一位
     */
    private String subPositionCode;

    /**
     * 排名时用到的职务代码
     */
    private String rankPositionCode;

    /**
     * 人事定义的专业技术职务的父节点
     */
    private String rsZyjszwParentid;

    /**
     * 主键
     */
    private String id;

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