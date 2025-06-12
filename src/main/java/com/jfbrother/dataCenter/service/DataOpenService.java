package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.extend.DataOpenModelExtend;
import com.jfbrother.dataCenter.model.param.DataOpenModelParam;
import com.jfbrother.dataCenter.model.search.DataOpenModelSearch;
import com.jfbrother.standard.model.extend.StandardDataThemeModelExtend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 数据开放管理Service接口
 *
 * @author chenyl@jfbrother.com 2022-09-16
 */
public interface DataOpenService extends BaseService {
    /**
     * 数据开放管理添加
     *
     * @param model 数据
     * @return
     */
    DataOpenModelExtend post(DataOpenModelParam model);

    /**
     * 数据开放管理更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    DataOpenModelExtend put(String id, DataOpenModelParam model);

    /**
     * 数据开放管理部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    DataOpenModelExtend patch(String id, DataOpenModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    DataOpenModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<DataOpenModelExtend> get(DataOpenModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<DataOpenModelExtend> get(DataOpenModelSearch search, Sort sort);

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
     * 数据开发列表获取
     *
     * @param roleId
     * @return
     */
    List<StandardDataThemeModelExtend> getByRole(String roleId);
}
