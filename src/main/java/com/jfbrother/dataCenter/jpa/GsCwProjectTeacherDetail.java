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
 * 项目发放-教职工收入JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_cw_project_teacher_detail")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsCwProjectTeacherDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 编号
     */
    private String bh;

    /**
     * 姓名
     */
    private Integer xm;

    /**
     * 工号
     */
    private String gh;

    /**
     * 金额
     */
    private BigDecimal je;

    /**
     * 建行卡号
     */
    private String gxkh;

    /**
     * 公务卡号
     */
    private String gwkh;

    /**
     * 用工类型文本
     */
    private String yglxwb;

    /**
     * 用工类型
     */
    private Integer yglx;

    /**
     * 备注
     */
    private String bz;

    /**
     * 年月标识
     */
    private String nybs;

    /**
     * 经费来源
     */
    private Integer jfly;

    /**
     * 经费日期
     */
    private String jfrq;

    /**
     * 经费来源（成本中心）
     */
    private String jfspr;

    /**
     * 发放内容
     */
    private String ffnr;

    /**
     * 项目/课题代码
     */
    private String xmktbh;

    /**
     * 项目类型
     */
    private Integer xmlx;

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