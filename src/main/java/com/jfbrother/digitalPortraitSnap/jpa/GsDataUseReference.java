package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 数据使用参照,快照系统取数依据JPA对象
 * @author xinz 2023-03-30
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_data_use_reference")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class GsDataUseReference implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String id;

    /**
     * 数据模板名称
     */
    private String templateName;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 班级代码
     */
    private String bjdm;

    /**
     * 学年
     */
    private String xn;

    /**
     * 学期
     */
    private String xq;

    /**
     * 是否启用，0-未启用 1-已启用
     */
    private String status;

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

    /**
     * 班级名称
     */
    private String bjmc;
}