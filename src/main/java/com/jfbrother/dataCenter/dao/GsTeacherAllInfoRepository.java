package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherAllInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 教师全部信息数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Repository
public interface GsTeacherAllInfoRepository extends JpaRepository<GsTeacherAllInfo, String>,
        JpaSpecificationExecutor<GsTeacherAllInfo>,
        QuerydslPredicateExecutor<GsTeacherAllInfo> {

}
