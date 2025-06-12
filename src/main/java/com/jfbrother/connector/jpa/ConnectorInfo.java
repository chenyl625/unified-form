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
 * 接口管理JPA对象
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "connector_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ConnectorInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    private String connName;

    /**
     * 接口地址
     */
    private String connUrl;

    /**
     * 接口地址（分页）
     */
    private String connUrlPage;

    /**
     * 携带参数
     */
    private String withParams;

    /**
     * 接口类型
     */
    private String connType;

    /**
     * 排序号
     */
    private Integer sortNum;


}