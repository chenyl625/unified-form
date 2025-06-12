package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuParentsBindHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生-家长绑定历史信息数据库操作对象
 * @author xinz 2023-03-21
 */
@Repository
public interface GsStuParentsBindHistoryRepository extends JpaRepository<GsStuParentsBindHistory, String>,
        JpaSpecificationExecutor<GsStuParentsBindHistory>,
        QuerydslPredicateExecutor<GsStuParentsBindHistory> {

}
