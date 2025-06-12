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
 * 科研项目信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_project_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsProjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 项目编号
     */
    private String code;

    /**
     * 项目名称
     */
    private String name;

    /**
     * unit_id
     */
    private String unitId;

    /**
     * 单位代码
     */
    private String unitCode;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 负责人工号
     */
    private String chargerCode;

    /**
     * charger_name
     */
    private String chargerName;

    /**
     * project_type_id
     */
    private String projectTypeId;

    /**
     * 项目类型名称
     */
    private String projectTypeName;

    /**
     * subject_class_id
     */
    private String subjectClassId;

    /**
     * 科目分类名称
     */
    private String subjectClassName;

    /**
     * 学科代码
     */
    private String subjectCode;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * project_class_id
     */
    private String projectClassId;

    /**
     * 项目分类名称
     */
    private String projectClassName;

    /**
     * authorize_date
     */
    private String authorizeDate;

    /**
     * plan_end_date
     */
    private String planEndDate;

    /**
     * actual_end_date
     */
    private String actualEndDate;

    /**
     * project_status_id
     */
    private String projectStatusId;

    /**
     * 项目状态
     */
    private String projectStatusName;

    /**
     * check_status_id
     */
    private String checkStatusId;

    /**
     * 考核状态
     */
    private String checkStatusName;

    /**
     * project_level_id
     */
    private String projectLevelId;

    /**
     * 项目级别
     */
    private String projectLevelName;

    /**
     * 配套经费
     */
    private BigDecimal attachFee;

    /**
     * start_date
     */
    private String startDate;

    /**
     * 项目经费/合同金额
     */
    private BigDecimal feeAuthorize;

    /**
     * contract_type
     */
    private String contractType;

    /**
     * 合同类别名称
     */
    private String contractTypeName;

    /**
     * 总到账经费
     */
    private BigDecimal incomeFeeSum;

    /**
     * operate_time
     */
    private String operateTime;

    /**
     * is_sub_problem
     */
    private String isSubProblem;

    /**
     * payout_sum
     */
    private BigDecimal payoutSum;

    /**
     * outbound_sum
     */
    private BigDecimal outboundSum;

    /**
     * 余额
     */
    private BigDecimal balanceSum;

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