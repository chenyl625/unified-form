package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherTeachQualityEvalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  教师维度的教学质量评价信息数据库操作对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Repository
public interface GsTeacherTeachQualityEvalInfoRepository extends JpaRepository<GsTeacherTeachQualityEvalInfo, String>,
        JpaSpecificationExecutor<GsTeacherTeachQualityEvalInfo>,
        QuerydslPredicateExecutor<GsTeacherTeachQualityEvalInfo> {

}
