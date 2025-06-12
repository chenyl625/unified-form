package com.jfbrother.digitalPortraitSnap.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.param.GsDataUseReferenceModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsDataUseReferenceModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsDataUseReferenceModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;

/**
 * 数据使用参照,快照系统取数依据Service接口
 * @author xinz 2023-03-30
 */
public interface GsDataUseReferenceService extends BaseService{
    /**
     * 数据使用参照,快照系统取数依据添加
     * @param model 数据
     * @return
     */
    GsDataUseReferenceModelExtend post(GsDataUseReferenceModelParam model);

    /**
     * 数据使用参照,快照系统取数依据更新
     * @param id    主键id
     * @param model 数据
     * @return
     */
    GsDataUseReferenceModelExtend put(String id, GsDataUseReferenceModelParam model);

    /**
     * 数据使用参照,快照系统取数依据部分更新
     * @param id    主键id
     * @param model 数据
     * @return
     */
    GsDataUseReferenceModelExtend patch(String id, GsDataUseReferenceModelParam model);

    /**
     * 根据主键id获取数据
     * @param id 主键id
     * @return
     */
    GsDataUseReferenceModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsDataUseReferenceModelExtend> get(GsDataUseReferenceModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsDataUseReferenceModelExtend> get(GsDataUseReferenceModelSearch search, Sort sort);

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
     * 获取数据使用模板
     * @param stuCode
     * @return
     */
    Map<String,Object> getDataUseTemplate(String stuCode);
}