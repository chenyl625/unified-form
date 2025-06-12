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

/**
 * 办事申请表JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_gxbg_apply_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxbgApplyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 当前任务流程进度
     */
    private Long flowStep;

    /**
     * 申请人id
     */
    private Long createdUserId;

    /**
     * 申请学期
     */
    private Long termId;

    /**
     * 删除状态 0:未删除 1:已删除
     */
    private Integer deleted;

    /**
     * 发布的任务id
     */
    private Long publishId;

    /**
     * 提醒结果
     */
    private String remind;

    /**
     * 任务申请状态 0:暂存 1:已退回 2:待审核 3:通过 4:撤销
     */
    private Integer status;

    /**
     * 提交申请时间
     */
    private String submitDate;

    /**
     * 申请人类型 1:学生 2:教师
     */
    private Integer applyerType;

    /**
     * 工号（工号和身份证不能同时为空）
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 用户类型 1:管理员 2:教师 3：学生 4：测试
     */
    private Integer userType;

    /**
     * 开始时间
     */
    private String termStartDate;

    /**
     * 结束时间
     */
    private String termEndDate;

    /**
     * 是否当前学期: 1，是 2，否
     */
    private Integer isCurrent;

    /**
     * collide_publish_id
     */
    private Long collidePublishId;

    /**
     * 任务状态 0:删除 1:关闭 2:发布中 3暂停
     */
    private Integer publishStatus;

    /**
     * 任务标题
     */
    private String publishName;

    /**
     * 开始时间
     */
    private String publishStartDate;

    /**
     * 截止时间
     */
    private String publishEndDate;

    /**
     * 任务表格
     */
    private Long tableId;

    /**
     * 表格名称
     */
    private String tabName;

    /**
     * 表格类型
     */
    private Long tableTypeId;

    /**
     * 表格类型代码
     */
    private String tableTypeCode;

    /**
     * 表格类型名称
     */
    private String tableTypeName;

    /**
     * 学年
     */
    private String schoolYear;

    /**
     * 学期
     */
    private String semester;

    /**
     * 用户状态（0停用，1正常，2离司）
     */
    private Integer userStatus;

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