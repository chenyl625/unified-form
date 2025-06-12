package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsChargeTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  在校班级的班主任数据库操作对象
* @author chenyl@jfbrother.com 2022-08-31
*/
@Repository
public interface GsGxxsChargeTeacherRepository extends JpaRepository<GsGxxsChargeTeacher, String>,
        JpaSpecificationExecutor<GsGxxsChargeTeacher>,
        QuerydslPredicateExecutor<GsGxxsChargeTeacher> {

}
