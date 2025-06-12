package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherPersonComm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  教师个人通讯方式数据库操作对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Repository
public interface GsTeacherPersonCommRepository extends JpaRepository<GsTeacherPersonComm, String>,
        JpaSpecificationExecutor<GsTeacherPersonComm>,
        QuerydslPredicateExecutor<GsTeacherPersonComm> {

}
