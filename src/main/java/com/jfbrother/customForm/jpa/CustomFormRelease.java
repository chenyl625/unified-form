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
 * 自定义表单发布JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "custom_form_release")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomFormRelease extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表单id
     */
    private String formId;

    /**
     * 发布名称
     */
    private String releaseName;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 填写范围
     */
    private String fillScope;

    /**
     * 开始使用时间
     */
    private Long startDateTime;

    /**
     * 结束使用时间
     */
    private Long endDateTime;

    /**
     * 流程自定义标识
     */
    private String flowCustomDef;

    /**
     * 是否流程
     */
    private Integer isFlow;

    /**
     * 门户事项档案号,具备事项的唯一性
     */
    private String itemDomId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号
     */
    private Integer sortNum;


}