package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuBaseEntire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/**
 * 教务系统里的在校生数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Repository
public interface GsGxxsStuBaseEntireRepository extends JpaRepository<GsGxxsStuBaseEntire, String>,
        JpaSpecificationExecutor<GsGxxsStuBaseEntire>,
        QuerydslPredicateExecutor<GsGxxsStuBaseEntire> {

}
