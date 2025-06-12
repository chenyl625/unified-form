package com.jfbrother.student.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
 *  学生寝室调动日志数据JPA对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_dormitory_transfer_log_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuDormitoryTransferLogData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 学号
     */
    private String xh;

    /**
     * 年份
     */
    private String schoolYear;

    /**
     * 1:第一学期，2:第二学期
     */
    private String term;

    /**
     * 原寝室名称
     */
    private String bedroomName;

    /**
     * 变更后寝室名称
     */
    private String changeBedroomName;

    /**
     * 原床位号
     */
    private String bedCode;

    /**
     * 变更后床位号
     */
    private String changeBedCode;

    /**
     * 调寝类型(1:调入，2:互调)
     */
    private String type;

    /**
     * 办理人
     */
    private String transactors;

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