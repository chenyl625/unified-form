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
 * 数据纠错补录表JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-05
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "data_error_correction")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DataErrorCorrection extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 接口字段id
     */
    private String connFieldId;

    /**
     * 旧值
     */
    private String oldValue;

    /**
     * 新值
     */
    private String newValue;

    /**
     * 字段数据json
     */
    private String rowJson;

    /**
     * 审核人
     */
    private String checkUser;

    /**
     * 审核时间
     */
    private Long checkDateTime;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    /**
     * 申请时间
     */
    private Long applyDateTime;

    /**
     * 字段json
     */
    private String labelJson;

    /**
     * 排序号
     */
    private Integer sortNum;


}