package com.jfbrother.digitalPortraitSnap.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagMajorItemModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagMajorItemModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagMajorItemModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 学生标签大类Service接口
 * @author xinz 2023-03-27
 */
public interface GsStuTagMajorItemService extends BaseService{

    /**
     * 学生标签大类添加
     * @param model 数据
     * @return
     */
    GsStuTagMajorItemModelExtend post(GsStuTagMajorItemModelParam model);

    /**
     * 学生标签大类更新
     * @param id    主键id
     * @param model 数据
     * @return
     */
    GsStuTagMajorItemModelExtend put(String id, GsStuTagMajorItemModelParam model);

    /**
     * 学生标签大类部分更新
     * @param id    主键id
     * @param model 数据
     * @return
     */
    GsStuTagMajorItemModelExtend patch(String id, GsStuTagMajorItemModelParam model);

    /**
     * 根据主键id获取数据
     * @param id 主键id
     * @return
     */
    GsStuTagMajorItemModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsStuTagMajorItemModelExtend> get(GsStuTagMajorItemModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsStuTagMajorItemModelExtend> get(GsStuTagMajorItemModelSearch search, Sort sort);

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
}
