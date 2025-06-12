package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherYearKh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 教职工年底考核数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-21
 */
@Repository
public interface GsTeacherYearKhRepository extends JpaRepository<GsTeacherYearKh, String>,
        JpaSpecificationExecutor<GsTeacherYearKh>,
        QuerydslPredicateExecutor<GsTeacherYearKh> {

}
