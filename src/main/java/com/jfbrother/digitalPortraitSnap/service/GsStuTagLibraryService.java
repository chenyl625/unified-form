package com.jfbrother.digitalPortraitSnap.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagLibraryModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagLibraryModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagLibraryModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 学生标签库Service接口
 * @author xinz 2023-03-27
 */
public interface GsStuTagLibraryService extends BaseService{
    /**
     * 学生标签库添加
     * @param model 数据
     * @return
     */
    GsStuTagLibraryModelExtend post(GsStuTagLibraryModelParam model);

    /**
     * 学生标签库更新
     * @param id        主键id
     * @param model 数据
     * @return
     */
    GsStuTagLibraryModelExtend put(String id, GsStuTagLibraryModelParam model);

    /**
     * 学生标签库部分更新
     * @param id         主键id
     * @param model 数据
     * @return
     */
    GsStuTagLibraryModelExtend patch(String id, GsStuTagLibraryModelParam model);

    /**
     * 根据主键id获取数据
     * @param id 主键id
     * @return
     */
    GsStuTagLibraryModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsStuTagLibraryModelExtend> get(GsStuTagLibraryModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsStuTagLibraryModelExtend> get(GsStuTagLibraryModelSearch search, Sort sort);

    /**
     * 根据ids删除数据
     * @param ids id列表
     */
    void delete(List<String> ids);

    /**
     * 根据ids逻辑删除数据
     * @param ids id列表
     */
    void deleteLogical(List<String> ids);

    /**
     * 查询符合标签学生列表,分页
     * @param body
     * @return
     */
    Map<String,Object> getConformToTagStuListPage(Map<String,Object> body);

    /**
     * 给学生标记标签
     * @param body
     * @return
     */
    void markTagToStu(Map<String,Object> body);

    /**
     * 学生标签列表,分页获取
     * @param params
     * @return
     */
    Map<String,Object> getStuTagListPage(Map<String,Object> params);

    /**
     * 标签统计
     * @return
     */
    Map<String,Object> getLabelStatistics();
}
