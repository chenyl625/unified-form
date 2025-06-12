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
 * 奖金发放-个人收入明细JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_cw_jj_person_detail")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsCwJjPersonDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 年月标识
     */
    private String nybs;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 工号
     */
    private String gh;

    /**
     * 应发
     */
    private BigDecimal yf;

    /**
     * 扣税
     */
    private BigDecimal ks;

    /**
     * 实发
     */
    private BigDecimal sf;

    /**
     * 用工类型
     */
    private String yglx;

    /**
     * 奖金类型
     */
    private Integer jjlx;

    /**
     * 来源明细
     */
    private String lymx;

    /**
     * 绩效内外
     */
    private Integer jxnw;

    /**
     * mainid
     */
    private Integer mainid;

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