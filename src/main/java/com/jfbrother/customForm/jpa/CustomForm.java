package com.jfbrother.customForm.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 自定义表单JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "custom_form")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomForm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 中文解释
     */
    private String chineseExplain;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 填写范围
     */
    private String fillScope;

    /**
     * 审批流程
     */
    private String approvalProcess;

    /**
     * 开始使用时间
     */
    private Long startDateTime;

    /**
     * 结束使用时间
     */
    private Long endDateTime;

    /**
     * 表单配置
     */
    private String option;

    /**
     * 流程自定义标识
     */
    private String flowCustomDef;

    /**
     * 积木报表id
     */
    private String jimuReportId;

    /**
     * 排序号
     */
    private Integer sortNum;

}