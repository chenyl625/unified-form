package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.extend.GsGxjgAllDeptModelExtend;
import com.jfbrother.dataCenter.model.search.GsGxjgAllDeptModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 人事中历史所有部门信息Service接口
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
public interface GsGxjgAllDeptService extends BaseService {

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    GsGxjgAllDeptModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsGxjgAllDeptModelExtend> get(GsGxjgAllDeptModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsGxjgAllDeptModelExtend> get(GsGxjgAllDeptModelSearch search, Sort sort);

    /**
     * 部门同步
     */
    void syncDept();
}
