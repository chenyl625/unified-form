package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuLeaveMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  学生家长/教师留言数据库操作对象
 * @author xinz 2023-03-16
 */
@Repository
public interface GsStuLeaveMsgRepository extends JpaRepository<GsStuLeaveMsg, String>,
        JpaSpecificationExecutor<GsStuLeaveMsg>,
        QuerydslPredicateExecutor<GsStuLeaveMsg> {
    /**
     * @param ids
     * @function 根据主键id列表逻辑删除数据
     */
    @Modifying
    @Query("update #{#entityName} s set s.deleteFlag=1 where s.id in (?1)")
    void deleteBatchLogical(List<String> ids);
}
