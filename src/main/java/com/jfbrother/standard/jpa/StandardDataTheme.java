package com.jfbrother.standard.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
*  数据开放主题管理JPA对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="standard_data_theme")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class StandardDataTheme extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主题名称
    */
    private String themeName;

    /**
     * 备注
     */
    private String remark;

    /**
    * 排序号
    */
    private Integer sortNum;


}