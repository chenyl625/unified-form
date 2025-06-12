package com.jfbrother.student.jpa;

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
*  三创学分申请详情JPA对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_scxf_apply_detail")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsScxfApplyDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 申请人
    */
    private String applyUserXgh;

    /**
    * 申请时间
    */
    private String applyTime;

    /**
    * 学分
    */
    private String score;

    /**
    * 申请方式
    */
    private String way;

    /**
    * 状态
    */
    private String status;

    /**
    * 申请描述
    */
    private String applyDescribe;

    /**
    * 姓名
    */
    private String xm;

    /**
    * 学期名称
    */
    private String termName;

    /**
    * 学年
    */
    private String xn;

    /**
    * 项目名称
    */
    private String projectName;

    /**
    * 认定要求
    */
    private String cognizanceRequire;

    /**
    * 认定状态(0:待认定 1:不通过 2:通过)
    */
    private Integer applyStatus;

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