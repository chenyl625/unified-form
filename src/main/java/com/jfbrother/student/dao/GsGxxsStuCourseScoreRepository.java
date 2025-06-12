package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuCourseScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生课程成绩表数据库操作对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Repository
public interface GsGxxsStuCourseScoreRepository extends JpaRepository<GsGxxsStuCourseScore, String>,
        JpaSpecificationExecutor<GsGxxsStuCourseScore>,
        QuerydslPredicateExecutor<GsGxxsStuCourseScore> {

}
