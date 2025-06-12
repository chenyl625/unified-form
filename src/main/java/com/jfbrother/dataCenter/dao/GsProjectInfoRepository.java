package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 科研项目信息数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-26
 */
@Repository
public interface GsProjectInfoRepository extends JpaRepository<GsProjectInfo, String>,
        JpaSpecificationExecutor<GsProjectInfo>,
        QuerydslPredicateExecutor<GsProjectInfo> {

}
