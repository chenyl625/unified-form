package com.jfbrother.dataCenter.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
*  数据字典子表JPA对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="data_dictionary_child")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DataDictionaryChild extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 父id
    */
    private String pid;

    /**
    * 代码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 排序号
    */
    private Integer sortNum;


}