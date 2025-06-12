package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsGxxsClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  班级基本信息数据库操作对象
 * @author xinz 2023-04-03
 */
@Repository
public interface GsGxxsClassInfoRepository extends JpaRepository<GsGxxsClassInfo, String>,
        JpaSpecificationExecutor<GsGxxsClassInfo>,
        QuerydslPredicateExecutor<GsGxxsClassInfo> {

}
