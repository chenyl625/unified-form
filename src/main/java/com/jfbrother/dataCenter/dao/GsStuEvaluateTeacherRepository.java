package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsStuEvaluateTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 学评教-学生评价老师汇总表数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Repository
public interface GsStuEvaluateTeacherRepository extends JpaRepository<GsStuEvaluateTeacher, String>,
        JpaSpecificationExecutor<GsStuEvaluateTeacher>,
        QuerydslPredicateExecutor<GsStuEvaluateTeacher> {

}
