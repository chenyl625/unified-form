package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuSocialPrac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生社会实践数据库操作对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Repository
public interface GsGxxsStuSocialPracRepository extends JpaRepository<GsGxxsStuSocialPrac, String>,
        JpaSpecificationExecutor<GsGxxsStuSocialPrac>,
        QuerydslPredicateExecutor<GsGxxsStuSocialPrac> {

}
