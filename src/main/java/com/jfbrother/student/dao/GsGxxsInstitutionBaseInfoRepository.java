package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsInstitutionBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学院基本信息数据库操作对象
* @author chenyl@jfbrother.com 2022-08-31
*/
@Repository
public interface GsGxxsInstitutionBaseInfoRepository extends JpaRepository<GsGxxsInstitutionBaseInfo, String>,
        JpaSpecificationExecutor<GsGxxsInstitutionBaseInfo>,
        QuerydslPredicateExecutor<GsGxxsInstitutionBaseInfo> {

}
