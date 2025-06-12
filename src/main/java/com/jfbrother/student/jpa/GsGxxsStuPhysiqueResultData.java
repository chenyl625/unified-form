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
 *  学生体质测试成绩数据JPA对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_physique_result_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuPhysiqueResultData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 入学年级
     */
    private String rxnj;

    /**
     * 班级代码
     */
    private String ssbjM;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 学籍状态
     */
    private String xjzt;

    /**
     * 民族代码
     */
    private String mzM;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 学号
     */
    private String xh;

    /**
     * 性别
     */
    private String xbM;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 学期代码
     */
    private BigDecimal semesterid;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 体重
     */
    private BigDecimal weight;

    /**
     * 体重评分
     */
    private BigDecimal weightScore;

    /**
     * 体重等级
     */
    private String weightLevel;

    /**
     * 肺活量
     */
    private BigDecimal vitalCapacityResult;

    /**
     * 肺活量评分
     */
    private BigDecimal vitalCapacityScore;

    /**
     * 肺活量等级
     */
    private String vitalCapacityLevel;

    /**
     * 五十米跑
     */
    private BigDecimal fiftyMeterRunResult;

    /**
     * 五十米跑评分
     */
    private BigDecimal fiftyMeterRunScore;

    /**
     * 五十米跑等级
     */
    private String fiftyMeterRunLevel;

    /**
     * 立定跳远
     */
    private BigDecimal standingLongJumpResult;

    /**
     * 立定跳远评分
     */
    private BigDecimal standingLongJumpScore;

    /**
     * 立定跳远等级
     */
    private String standingLongJumpLevel;

    /**
     * 坐位体前驱
     */
    private BigDecimal sittingForwardFlexionResult;

    /**
     * 坐位体前屈评分
     */
    private BigDecimal sittingForwardFlexionScore;

    /**
     * 坐位体前驱等级
     */
    private String sittingForwardFlexionLevel;

    /**
     * 八百米跑
     */
    private BigDecimal eightHundredMeterRunResult;

    /**
     * 八百米跑评分
     */
    private BigDecimal eightHundredMeterRunScore;

    /**
     * 八百米跑等级
     */
    private String eightHundredMeterRunLevel;

    /**
     * 一千米跑
     */
    private BigDecimal oneThousandMeterRunResult;

    /**
     * 一千米跑评分
     */
    private BigDecimal oneThousandMeterRunScore;

    /**
     * 一千米跑等级
     */
    private String oneThousandMeterRunLevel;

    /**
     * 仰卧起坐
     */
    private BigDecimal abdominalCurlResult;

    /**
     * 仰卧起坐评分
     */
    private BigDecimal abdominalCurlResultScore;

    /**
     * 仰卧起坐等级
     */
    private String abdominalCurlLevel;

    /**
     * 引体向上
     */
    private BigDecimal pullUpResult;

    /**
     * 引体向上评分
     */
    private BigDecimal pullUpScore;

    /**
     * 引体向上等级
     */
    private String pullUpLevel;

    /**
     * 左眼裸眼视力
     */
    private BigDecimal lyslzy;

    /**
     * 右眼裸眼视力
     */
    private BigDecimal lyslyy;

    /**
     * 左眼屈光不正
     */
    private BigDecimal qgbzzy;

    /**
     * 右眼屈光不正
     */
    private BigDecimal qgbzyy;

    /**
     * 左眼串镜
     */
    private BigDecimal cjzy;

    /**
     * 右眼串镜
     */
    private BigDecimal cjyy;

    /**
     * 标准分
     */
    private BigDecimal standardScore;

    /**
     * 附加分1
     */
    private BigDecimal plus1;

    /**
     * 附加分2
     */
    private BigDecimal plus2;

    /**
     * 总分
     */
    private BigDecimal finalscore;

    /**
     * 总分等级
     */
    private String totalLevel;

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