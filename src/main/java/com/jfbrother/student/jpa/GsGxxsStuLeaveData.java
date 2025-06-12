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
 *  学生请假数据JPA对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_leave_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuLeaveData implements Serializable {
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
     * 1:普通请假，2:长期请假
     */
    private String type;

    /**
     * 1:因公请假，2:因私请假，3:因病请假
     */
    private String category;

    /**
     * 请假开始时间
     */
    private String startTime;

    /**
     * 请假结束时间
     */
    private String endTime;

    /**
     * 请假开始节次
     */
    private String firstSection;

    /**
     * 请假结束节次
     */
    private String lastSection;

    /**
     * 请假节数
     */
    private String sectionLength;

    /**
     * 请假天数
     */
    private String sky;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 请假附件
     */
    private String file;

    /**
     * 教师姓名
     */
    private String teacherName;

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