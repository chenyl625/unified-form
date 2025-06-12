package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuDormHealthCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生寝室卫生检查数据库操作对象
 * @author xinz 2023-03-10
 */
@Repository
public interface GsStuDormHealthCheckRepository extends JpaRepository<GsStuDormHealthCheck, String>,
        JpaSpecificationExecutor<GsStuDormHealthCheck>,
        QuerydslPredicateExecutor<GsStuDormHealthCheck>{

}
