package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsCwProjectTeacherDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 项目发放-教职工收入数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Repository
public interface GsCwProjectTeacherDetailRepository extends JpaRepository<GsCwProjectTeacherDetail, String>,
        JpaSpecificationExecutor<GsCwProjectTeacherDetail>,
        QuerydslPredicateExecutor<GsCwProjectTeacherDetail> {

}
