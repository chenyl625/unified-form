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
 * 教师详细信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_detail_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherDetailInfo implements Serializable {
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
     * 现任职务
     */
    private String nowJobPositionDm;

    /**
     * 岗位等级
     */
    private String gwLevel;

    /**
     * 专业技术职务
     */
    private String zyjszwDm;

    /**
     * 最高学历代码
     */
    private String zgxlDm;

    /**
     * 政治面貌
     */
    private String zzmmDm;

    /**
     * 最高学位代码
     */
    private String zgxwDm;

    /**
     * 最高学历名称
     */
    private String zgxlName;

    /**
     * 进校时间
     */
    private String enterSchoolTime;

    /**
     * 职业资格等级
     */
    private String zyzgLevel;

    /**
     * 岗位类别
     */
    private String gwTypeDm;

    /**
     * 毕业学校
     */
    private String graduateSchool;

    /**
     * 类型
     */
    private String type;

    /**
     * 教职工类别编码
     */
    private String positionTypeCode;

    /**
     * 教职工类别名称
     */
    private String positionTypeName;

    /**
     * 部门号
     */
    private String bmh;

    /**
     * 现任职务
     */
    private String nowPosition;

    /**
     * 人员划分代码
     */
    private String peopleSortCode;

    /**
     * 人员划分
     */
    private String peopleSort;

    /**
     * 专业技术职务获取时间
     */
    private String zyjszwAcquireDate;

    /**
     * 工行银行卡号
     */
    private String bankCard;

    /**
     * 公务卡号
     */
    private String busBankCard;

    /**
     * 用工类别
     */
    private String yglbDm;

    /**
     * 用工类别名称
     */
    private String yglbName;

    /**
     * 教学归属部门编码
     */
    private String teachDeptCode;

    /**
     * 建设银行卡
     */
    private String constructionBankCard;

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