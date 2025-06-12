package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsKyPaperInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  科研论文信息数据库操作对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Repository
public interface GsKyPaperInfoRepository extends JpaRepository<GsKyPaperInfo, String>,
        JpaSpecificationExecutor<GsKyPaperInfo>,
        QuerydslPredicateExecutor<GsKyPaperInfo> {

}
