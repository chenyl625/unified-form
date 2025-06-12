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
 * 自定义表单发布对应接口JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-06
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "custom_form_release_conn")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomFormReleaseConn extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发布id
     */
    private String releaseId;

    /**
     * 表单字段id
     */
    private String formFieldId;

    /**
     * 是否为表单子表字段
     */
    private Integer isChildField;

    /**
     * 表单字段子表id
     */
    private String childId;

    /**
     * 是否关联接口字段
     */
    private Integer isField;

    /**
     * 接口id
     */
    private String connId;

    /**
     * 接口字段名称
     */
    private String connFieldName;

    /**
     * 是否关联环节
     */
    private Integer isLink;

    /**
     * 环节名称
     */
    private String linkName;

    /**
     * 关联接口类型
     */
    private Integer connType;

    /**
     * 排序号
     */
    private Integer sortNum;


}