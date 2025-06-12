package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *  学生标签关系表JPA对象
 * @author xinz 2023-03-28
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_stu_tag_relation")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class GsStuTagRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String id;

    /**
     * 学号
     */
    private String stuCode;

    /**
     * 标签 id
     */
    private String tagId;

    /**
     * 删除标志，0-未删 1-已删
     */
    private String deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer orderBy;

    /**
     * 创建人
     */
    @CreatedBy
    private String createId;

    /**
     * 修改人
     */
    @LastModifiedBy
    private String updateId;

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
}