package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsKyPatentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 科研专利成果信息数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-07-27
 */
@Repository
public interface GsKyPatentInfoRepository extends JpaRepository<GsKyPatentInfo, String>,
        JpaSpecificationExecutor<GsKyPatentInfo>,
        QuerydslPredicateExecutor<GsKyPatentInfo> {

}
