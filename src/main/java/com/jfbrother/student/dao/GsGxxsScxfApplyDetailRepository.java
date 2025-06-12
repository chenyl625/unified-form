package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsScxfApplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  三创学分申请详情数据库操作对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Repository
public interface GsGxxsScxfApplyDetailRepository extends JpaRepository<GsGxxsScxfApplyDetail, String>,
        JpaSpecificationExecutor<GsGxxsScxfApplyDetail>,
        QuerydslPredicateExecutor<GsGxxsScxfApplyDetail> {

}
