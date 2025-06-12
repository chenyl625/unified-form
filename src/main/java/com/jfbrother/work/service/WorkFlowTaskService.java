package com.jfbrother.work.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.search.WorkFlowTaskModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 流程环节实例Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
public interface WorkFlowTaskService extends BaseService {
    /**
     * 流程环节实例添加
     *
     * @param model 数据
     * @return
     */
    WorkFlowTaskModelExtend post(WorkFlowTaskModelParam model);

    /**
     * 流程环节实例更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    WorkFlowTaskModelExtend put(String id, WorkFlowTaskModelParam model);

    /**
     * 流程环节实例部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    WorkFlowTaskModelExtend patch(String id, WorkFlowTaskModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    WorkFlowTaskModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<WorkFlowTaskModelExtend> get(WorkFlowTaskModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<WorkFlowTaskModelExtend> get(WorkFlowTaskModelSearch search, Sort sort);

    /**
     * 根据ids删除数据
     *
     * @param ids id列表
     */
    void delete(List<String> ids);

    /**
     * 根据ids逻辑删除数据
     *
     * @param ids id列表
     */
    void deleteLogical(List<String> ids);

    /**
     * 根据填报id获取数据
     *
     * @param fillId
     * @return
     */
    List<WorkFlowTaskModelExtend> getByFillId(String fillId);

    /**
     * 根据填报id删除流程数据
     *
     * @param fillId
     * @return
     */
    void deleteByFillId(String fillId);

    WorkFlowTaskModelExtend updateStatusByTaskId(int taskId,String status);

}
