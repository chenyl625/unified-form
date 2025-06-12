package com.jfbrother.dataCenter.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
*  教职工年底考核JPA对象
* @author chenyl@jfbrother.com 2022-07-21
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_teacher_year_kh")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherYearKh implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 工号
    */
    private String gh;

    /**
    * 考核结果类型
    */
    private String resultType;

    /**
    * 学年
    */
    private String xn;

    /**
    * 结果类型名称
    */
    private String resultName;

    /**
    * 类型转换分数
    */
    private Integer typeConvertValue;

    /**
    * 业务主键
    */
    private String etlPri;

    /**
    * gp删除标志位 1：删除 0未删除
    */
    private Integer etlIsDelete;

    /**
    * gp是否已完成数据质检标志位 1：是 0：否
    */
    private Integer etlIsChecked;

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