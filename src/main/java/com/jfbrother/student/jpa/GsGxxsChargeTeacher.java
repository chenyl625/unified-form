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

/**
 * 在校班级的班主任JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-31
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_gxxs_charge_teacher")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsChargeTeacher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 班级代码
     */
    private String bjdm;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 班主任工号
     */
    private String bzrgh;

    /**
     * 班主任姓名
     */
    private String bzrxm;

    /**
     * 辅导员工号
     */
    private String fdyh;

    /**
     * 辅导员姓名
     */
    private String fdyxm;

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