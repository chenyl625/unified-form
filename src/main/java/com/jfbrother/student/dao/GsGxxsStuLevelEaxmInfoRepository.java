package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuLevelEaxmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生等级考试情况数据库操作对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Repository
public interface GsGxxsStuLevelEaxmInfoRepository extends JpaRepository<GsGxxsStuLevelEaxmInfo, String>,
        JpaSpecificationExecutor<GsGxxsStuLevelEaxmInfo>,
        QuerydslPredicateExecutor<GsGxxsStuLevelEaxmInfo> {

}
