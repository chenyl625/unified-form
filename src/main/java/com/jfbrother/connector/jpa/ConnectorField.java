package com.jfbrother.connector.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 接口字段JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-20
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "connector_field")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ConnectorField extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接口id
     */
    private String connId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 数据来源
     */
    private String fieldSource;

    /**
     * 是否隐藏
     */
    private Integer isHide;

    /**
     * 数据来源表
     */
    private String fieldSourceTable;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型
     */
    private String type;

    /**
     * 数据字典id
     */
    private String dictId;

    /**
     * 排序号
     */
    private Integer sortNum;


}