package com.jfbrother.student.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.student.jpa.GsGxxsStuLeaveData;
import com.jfbrother.student.jpa.GsGxxsStuPhysiqueResultData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生请假数据数据库操作对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Repository
public interface GsGxxsStuLeaveDataRepository extends JpaRepository<GsGxxsStuLeaveData, String>,
        JpaSpecificationExecutor<GsGxxsStuLeaveData>,
        QuerydslPredicateExecutor<GsGxxsStuLeaveData>{

}
