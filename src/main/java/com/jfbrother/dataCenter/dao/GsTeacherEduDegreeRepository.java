package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsTeacherEduDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  教师学历学位数据库操作对象
* @author chenyl@jfbrother.com 2022-08-09
*/
@Repository
public interface GsTeacherEduDegreeRepository extends JpaRepository<GsTeacherEduDegree, String>,
        JpaSpecificationExecutor<GsTeacherEduDegree>,
        QuerydslPredicateExecutor<GsTeacherEduDegree> {

}
