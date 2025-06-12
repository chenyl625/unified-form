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
 * 自定义表单填报JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "custom_form_fill")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomFormFill extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 发布表单id
     */
    private String releaseId;

    /**
     * 流程状态（进行中，已完成，已取消）
     */
    private String flowStatus;

    /**
     * 流程id
     */
    private Integer flowId;

    /**
     * 启动时间
     */
    private Long startDate;

    /**
     * 结束时间
     */
    private Long endDate;

    /**
     * 填报时间
     */
    private Long fillDateTime;

    /**
     * 排序号
     */
    private Integer sortNum;


}