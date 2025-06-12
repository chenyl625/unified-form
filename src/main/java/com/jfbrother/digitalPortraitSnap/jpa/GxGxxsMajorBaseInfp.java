package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
 *  全部专业基本信息JPA对象
 * @author hjy@jfbrother.com 2023-03-08
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gx_gxxs_major_base_infp")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GxGxxsMajorBaseInfp extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 学院id
     */
    private String yxId;

    /**
     * 学院名称
     */
    private String yxmc;

    /**
     * 院系代码
     */
    private String yxdm;

    /**
     * 专业名称
     */
    private String zymc;

    /**
     * 专业代码
     */
    private String zyDm;

    /**
     * 国标专业代码
     */
    private String gbzydm;

    /**
     * 学制
     */
    private String xz;

    /**
     * 业务主键
     */
    private String etlPri;

    /**
     * 校验唯一性
     */
    private String etlMd5;

    /**
     * 多主键表使用
     */
    private String etlKeyMd5;

    /**
     * 变动类型
     */
    private String etlCheckType;

    /**
     * 变动时间
     */
    private String etlCheckDate;


}