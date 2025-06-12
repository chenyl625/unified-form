package com.jfbrother.dataCenter.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
*  数据开放管理JPA对象
* @author chenyl@jfbrother.com 2022-09-16
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="data_open")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DataOpen extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 所属主题
    */
    private String themeId;

    /**
    * 数据项名称
    */
    private String dataItemName;

    /**
    * 接口地址
    */
    private String connId;

    /**
    * 图标
    */
    private String icons;

    /**
    * 开放角色
    */
    private String openRoles;

    /**
    * 排序号
    */
    private Integer sortNum;


}