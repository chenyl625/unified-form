package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsAllStuBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 所有学生基本数据子类数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Repository
public interface GsGxxsAllStuBaseInfoRepository extends JpaRepository<GsGxxsAllStuBaseInfo, String>,
        JpaSpecificationExecutor<GsGxxsAllStuBaseInfo>,
        QuerydslPredicateExecutor<GsGxxsAllStuBaseInfo> {

}
