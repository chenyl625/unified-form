package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 *  班级基本信息JPA对象
 * @author xinz 2023-04-03
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_class_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsClassInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String id;

    /**
     * 年级
     */
    private String nj;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 学制
     */
    private String xz;

    /**
     * 班级代码user_dm
     */
    private String bjdm;

    /**
     * 所属专业id
     */
    private String majorDm;

    /**
     * 所属学院id
     */
    private String xyDm;

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

    /**
     * 专业id
     */
    private String zyId;
}