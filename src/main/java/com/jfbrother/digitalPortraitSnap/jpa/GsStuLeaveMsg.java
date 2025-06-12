package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *  学生家长/教师留言JPA对象
 * @author xinz 2023-03-16
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_stu_leave_msg")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class GsStuLeaveMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String id;

    /**
     * 工号/学号
     */
    private String code;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类别，0-学生家长 1-老师
     */
    private String type;

    /**
     * 留言时间
     */
    private Long leaveTime;

    /**
     * 留言内容
     */
    private String msg;

    /**
     * 删除标志，0-未删 1-已删
     */
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @CreatedDate
    private Long createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Long updateTime;

    /**
     * 关于谁的留言，学生学号
     */
    private String aboutStuCode;

    /**
     * 班主任留言已读未读，0-未读 1-已读
     */
    private String bzrReadOrNot;

    /**
     * 辅导员留言已读未读，0-未读 1-已读
     */
    private String fdyReadOrNot;

    /**
     * 家长留言已读未读，0-未读 1-已读
     */
    private String parentReadOrNot;

}