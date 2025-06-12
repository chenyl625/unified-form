package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsGxjgAllDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  人事中历史所有部门信息数据库操作对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Repository
public interface GsGxjgAllDeptRepository extends JpaRepository<GsGxjgAllDept, String>,
        JpaSpecificationExecutor<GsGxjgAllDept>,
        QuerydslPredicateExecutor<GsGxjgAllDept> {

}
