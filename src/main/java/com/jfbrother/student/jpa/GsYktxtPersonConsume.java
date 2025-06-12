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
 * 个人消费记录JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_yktxt_person_consume")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsYktxtPersonConsume implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 交易流水号
     */
    private Integer id;

    /**
     * 工号/学号
     */
    private String code;

    /**
     * 记账日
     */
    private String bookkeepdate;

    /**
     * 交易时间
     */
    private String dealtime;

    /**
     * 费用缴纳方式
     */
    private String feepaymentway;

    /**
     * 交易类型编码
     */
    private Integer businessdeptcode;

    /**
     * 交易金额
     */
    private BigDecimal mondeal;

    /**
     * 优惠金额
     */
    private BigDecimal mondiscount;

    /**
     * 商户编号
     */
    private Integer businessnum;

    /**
     * 商户名称
     */
    private String businessname;

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