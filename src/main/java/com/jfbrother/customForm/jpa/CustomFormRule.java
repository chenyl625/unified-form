package com.jfbrother.customForm.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
*  自定义表单规则JPA对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="custom_form_rule")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomFormRule extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 表单id
    */
    private String formId;

    /**
    * 规则文本
    */
    private String text;

    /**
    * 排序号
    */
    private Integer sortNum;


}