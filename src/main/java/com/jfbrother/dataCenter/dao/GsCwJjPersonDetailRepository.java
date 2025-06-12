package com.jfbrother.dataCenter.dao;


import com.jfbrother.dataCenter.jpa.GsCwJjPersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 奖金发放-个人收入明细数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-12
 */
@Repository
public interface GsCwJjPersonDetailRepository extends JpaRepository<GsCwJjPersonDetail, String>,
        JpaSpecificationExecutor<GsCwJjPersonDetail>,
        QuerydslPredicateExecutor<GsCwJjPersonDetail> {

}
