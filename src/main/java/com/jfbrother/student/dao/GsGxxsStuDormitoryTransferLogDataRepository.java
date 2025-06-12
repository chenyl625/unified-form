package com.jfbrother.student.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.student.jpa.GsGxxsStuDormitoryTransferLogData;
import com.jfbrother.student.jpa.GsGxxsStuLeaveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生寝室调动日志数据数据库操作对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Repository
public interface GsGxxsStuDormitoryTransferLogDataRepository extends JpaRepository<GsGxxsStuDormitoryTransferLogData, String>,
        JpaSpecificationExecutor<GsGxxsStuDormitoryTransferLogData>,
        QuerydslPredicateExecutor<GsGxxsStuDormitoryTransferLogData>{

}
