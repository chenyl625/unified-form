package com.jfbrother.work.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.work.jpa.WorkFlowTask;
import org.springframework.stereotype.Repository;

/**
 * 流程环节实例数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
@Repository
public interface WorkFlowTaskRepository extends BaseRepository<WorkFlowTask> {

}
