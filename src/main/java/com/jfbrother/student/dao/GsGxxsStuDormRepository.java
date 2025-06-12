package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuDorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  学生住宿信息数据库操作对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Repository
public interface GsGxxsStuDormRepository extends JpaRepository<GsGxxsStuDorm, String>,
        JpaSpecificationExecutor<GsGxxsStuDorm>,
        QuerydslPredicateExecutor<GsGxxsStuDorm> {

}
