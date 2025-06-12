package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuTagRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生标签关系表数据库操作对象
 * @author xinz 2023-03-28
 */
@Repository
public interface GsStuTagRelationRepository extends JpaRepository<GsStuTagRelation, String>,
        JpaSpecificationExecutor<GsStuTagRelation>,
        QuerydslPredicateExecutor<GsStuTagRelation> {

}
