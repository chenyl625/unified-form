package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
 *  学生寝室卫生检查JPA对象
 * @author xinz 2023-03-10
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_stu_dorm_health_check")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsStuDormHealthCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String etlJfuuid;

    /**
     * 寝室id
     */
    private String bedroomId;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 寝室名称
     */
    private String bedroomName;

    /**
     * 寝室楼号
     */
    private String bedroomLou;

    /**
     * 寝室楼层
     */
    private String bedroomFloor;

    /**
     * 寝室区域
     */
    private String bedroomArea;

    /**
     * 检查人员类型名称
     */
    private String userTypeName;

    /**
     * 有无违禁品
     */
    private String danger;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 检查日期
     */
    private String checkDate;

    /**
     * 检查人员姓名
     */
    private String checkUser;

    /**
     * 检查人员工号
     */
    private String checkUserCode;

    /**
     * 等级
     */
    private String levelType;

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