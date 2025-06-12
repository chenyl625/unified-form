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
*  自定义表单子表JPA对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="custom_form_child")
@GenericGenerator(name = "jpa-uuid", strategy = "com.jfbrother.baseserver.jpa.generator.CustomUUIDGenerator")
public class CustomFormChild extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 表单id
    */
    private String formId;

    /**
    * 名称
    */
    private String tableName;

    /**
    * 中文注释
    */
    private String tableComment;

    /**
    * 排序号
    */
    private Integer sortNum;


}