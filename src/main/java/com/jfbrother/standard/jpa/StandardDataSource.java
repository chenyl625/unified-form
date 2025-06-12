package com.jfbrother.standard.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
*  数据来源标准库JPA对象
* @author chenyl@jfbrother.com 2022-08-04
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="standard_data_source")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class StandardDataSource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 名称
    */
    private String name;

    /**
    * 审核人
    */
    private String checkUser;

    /**
    * 排序号
    */
    private Integer sortNum;


}