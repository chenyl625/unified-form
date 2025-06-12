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
 * 自定义表单字段JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "custom_form_field")
@GenericGenerator(name = "jpa-uuid", strategy = "com.jfbrother.baseserver.jpa.generator.CustomUUIDGenerator")
public class CustomFormField extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    private String pid;

    /**
     * 字段ID
     */
    private String field;

    /**
     * 类型
     */
    private String fieldType;

    /**
     * 字段名称
     */
    private String comment;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 最长长度
     */
    private Long maxlength;

    /**
     * 精度
     */
    private Integer fieldPrecision;

    /**
     * 是否为附件上传组件
     */
    private Integer izFileUpload;

    /**
     * 排序号
     */
    private Integer sortNum;


}