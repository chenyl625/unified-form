package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 教师基本信息数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@Repository
public interface GsTeacherBaseInfoRepository extends JpaRepository<GsTeacherBaseInfo, String>,
        JpaSpecificationExecutor<GsTeacherBaseInfo>,
        QuerydslPredicateExecutor<GsTeacherBaseInfo> {

}
