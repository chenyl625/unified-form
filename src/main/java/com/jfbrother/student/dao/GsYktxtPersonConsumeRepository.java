package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsYktxtPersonConsume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  个人消费记录数据库操作对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Repository
public interface GsYktxtPersonConsumeRepository extends JpaRepository<GsYktxtPersonConsume, String>,
        JpaSpecificationExecutor<GsYktxtPersonConsume>,
        QuerydslPredicateExecutor<GsYktxtPersonConsume> {

}
