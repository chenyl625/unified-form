package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuDormNightSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生寝室晚归签到数据库操作对象
 * @author xinz@jfbrother.com 2023-03-09
 */
@Repository
public interface GsStuDormNightSignRepository extends JpaRepository<GsStuDormNightSign, String>,
        JpaSpecificationExecutor<GsStuDormNightSign>,
        QuerydslPredicateExecutor<GsStuDormNightSign>{



}
