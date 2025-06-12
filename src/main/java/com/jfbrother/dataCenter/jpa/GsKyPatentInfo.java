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
 * 科研专利成果信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-27
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_ky_patent_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsKyPatentInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 专利名称
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
     * patent_type_id
     */
    private String patentTypeId;

    /**
     * 专利类型
     */
    private String patentTypeName;

    /**
     * patent_scope_id
     */
    private String patentScopeId;

    /**
     * 专利范围
     */
    private String patentScopeName;

    /**
     * patent_state_id
     */
    private String patentStateId;

    /**
     * 专利状态
     */
    private String patentStateName;

    /**
     * 申请日期
     */
    private String applyDate;

    /**
     * 专利号
     */
    private String applyCode;

    /**
     * 公开日期
     */
    private String openDate;

    /**
     * 授权日期
     */
    private String authorizeDate;

    /**
     * authorize_code
     */
    private String authorizeCode;

    /**
     * author_number
     */
    private String authorNumber;

    /**
     * is_duty_patent
     */
    private String isDutyPatent;

    /**
     * 是否为职务专利
     */
    private String isDutyPatentName;

    /**
     * first_author_name
     */
    private String firstAuthorName;

    /**
     * first_author_code
     */
    private String firstAuthorCode;

    /**
     * 是否为pct专利
     */
    private String isPct;

    /**
     * 专利发明（设计）人
     */
    private String members;

    /**
     * check_status_id
     */
    private String checkStatusId;

    /**
     * 审核名称
     */
    private String checkStatusName;

    /**
     * operate_time
     */
    private String operateTime;

    /**
     * cooperation_type
     */
    private String cooperationType;

    /**
     * 合作类型
     */
    private String cooperationTypeName;

    /**
     * 申请人
     */
    private String applyInfo;

    /**
     * 专利权人
     */
    private String patentee;

    /**
     * 是否失效
     */
    private String invalidFlag;

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