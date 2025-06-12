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
 * 教师家庭成员JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_family_mem")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherFamilyMem implements Serializable {
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
     * 与本人关系编号
     */
    private String relationCode;

    /**
     * 与本人关系名称
     */
    private String relationName;

    /**
     * 成员姓名
     */
    private String memberName;

    /**
     * 成员手机号
     */
    private String phone;

    /**
     * 成员工作单位及职务
     */
    private String memberCompany;

    /**
     * 成员家庭地址
     */
    private String address;

    /**
     * 成员政治面貌编号
     */
    private String zzmmCode;

    /**
     * 成员政治面貌名称
     */
    private String zzmmName;

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