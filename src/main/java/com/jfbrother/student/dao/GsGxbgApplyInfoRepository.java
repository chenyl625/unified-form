package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxbgApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  办事申请表数据库操作对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Repository
public interface GsGxbgApplyInfoRepository extends JpaRepository<GsGxbgApplyInfo, String>,
        JpaSpecificationExecutor<GsGxbgApplyInfo>,
        QuerydslPredicateExecutor<GsGxbgApplyInfo> {

}
