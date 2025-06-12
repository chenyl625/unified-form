package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 教师专业技术职务数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Repository
public interface GsTeacherPositionRepository extends JpaRepository<GsTeacherPosition, String>,
        JpaSpecificationExecutor<GsTeacherPosition>,
        QuerydslPredicateExecutor<GsTeacherPosition> {

}
