package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生联系方式数据库操作对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Repository
public interface GsGxxsStuContactRepository extends JpaRepository<GsGxxsStuContact, String>,
        JpaSpecificationExecutor<GsGxxsStuContact>,
        QuerydslPredicateExecutor<GsGxxsStuContact> {

}
