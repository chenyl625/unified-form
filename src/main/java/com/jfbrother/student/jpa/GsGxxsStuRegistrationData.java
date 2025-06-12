package com.jfbrother.student.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
 *  学生注册数据JPA对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_registration_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuRegistrationData implements Serializable {
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
     * 学年
     */
    private String xn;

    /**
     * 学期
     */
    private String xq;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 注册状态
     */
    private String zczt;

    /**
     * 注册日期
     */
    private String zcrq;

    /**
     * 报道日期
     */
    private String bdrq;

    /**
     * 未注册原因
     */
    private String wzcyy;

    /**
     * 在校状态
     */
    private String zxzt;

    /**
     * 学籍状态
     */
    private String xjzt;

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