package com.jfbrother.work.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 流程环节实例JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "work_flow_task")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class WorkFlowTask extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 填报id
     */
    private String fillId;

    /**
     * 环节id
     */
    private int taskId;

    /**
     * 办理序号
     */
    private int orderId;

    /**
     * 环节名称
     */
    private String name;

    /**
     * 办理时限
     */
    private String timeLimit;

    /**
     * 办理时长
     */
    private String diffHours;

    /**
     * 办理时间
     */
    private Long assignDate;

    /**
     * 结束时间
     */
    private Long endDate;

    /**
     * 状态（待办，进行中，已完成，已取消，待审）
     */
    private String status;

    /**
     * 办理人
     */
    private String assigeeName;

    /**
     * 办理人工号
     */
    private String assigneeAccount;

    /**
     * 是否为当前环节
     */
    private Integer isCurrTask;

    /**
     * 排序号
     */
    private Integer sortNum;


}