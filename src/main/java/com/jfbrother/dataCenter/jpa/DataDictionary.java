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
 * 数据字典表JPA对象
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "data_dictionary")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DataDictionary extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号
     */
    private Integer sortNum;

    /**
     * 同步的标准字典维护的id
     */
    private String infoId;

}