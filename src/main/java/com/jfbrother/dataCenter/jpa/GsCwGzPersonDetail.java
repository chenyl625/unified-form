package com.jfbrother.dataCenter.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
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
 * 工资发放-个人收入明细JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_cw_gz_person_detail")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsCwGzPersonDetail implements Serializable {
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
    private Integer xmrlzy;

    /**
     * 工号
     */
    private String gh;

    /**
     * 应发工资
     */
    private BigDecimal yfgz;

    /**
     * 实发工资
     */
    private BigDecimal sfgz;

    /**
     * 岗位工资
     */
    private BigDecimal gw;

    /**
     * 薪级工资
     */
    private BigDecimal xjgz;

    /**
     * 岗位津贴
     */
    private BigDecimal gwjt;

    /**
     * 生活补贴
     */
    private BigDecimal shbt;

    /**
     * 公积金
     */
    private BigDecimal gjj;

    /**
     * 养老金
     */
    private BigDecimal yanglj;

    /**
     * 医疗金
     */
    private BigDecimal yilj;

    /**
     * 失业金
     */
    private BigDecimal syj;

    /**
     * 工会费
     */
    private BigDecimal ghf;

    /**
     * 预扣年金
     */
    private BigDecimal yknj;

    /**
     * 其他
     */
    private BigDecimal qtbfk;

    /**
     * 所得税
     */
    private BigDecimal sds;

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