package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuXjChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生学籍异动最新记录数据库操作对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Repository
public interface GsGxxsStuXjChangeRepository extends JpaRepository<GsGxxsStuXjChange, String>,
        JpaSpecificationExecutor<GsGxxsStuXjChange>,
        QuerydslPredicateExecutor<GsGxxsStuXjChange> {

}
