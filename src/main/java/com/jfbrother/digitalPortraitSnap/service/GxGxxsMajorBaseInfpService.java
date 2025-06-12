package com.jfbrother.digitalPortraitSnap.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.GxGxxsMajorBaseInfpModel;
import com.jfbrother.digitalPortraitSnap.model.param.GxGxxsMajorBaseInfpModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GxGxxsMajorBaseInfpModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GxGxxsMajorBaseInfpModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 全部专业基本信息Service接口
 * @author hjy@jfbrother.com 2023-03-08
 */
public interface GxGxxsMajorBaseInfpService extends BaseService{
    /**
     * 全部专业基本信息添加
     *
     * @param model 数据
     * @return
     */
    GxGxxsMajorBaseInfpModelExtend post(GxGxxsMajorBaseInfpModelParam model);

    /**
     * 全部专业基本信息更新
     *
     * @param id        主键id
     * @param model 数据
     * @return
     */
    GxGxxsMajorBaseInfpModelExtend put(String id, GxGxxsMajorBaseInfpModelParam model);

    /**
     * 全部专业基本信息部分更新
     *
     * @param id         主键id
     * @param model 数据
     * @return
     */
    GxGxxsMajorBaseInfpModelExtend patch(String id, GxGxxsMajorBaseInfpModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    GxGxxsMajorBaseInfpModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GxGxxsMajorBaseInfpModelExtend> get(GxGxxsMajorBaseInfpModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GxGxxsMajorBaseInfpModelExtend> get(GxGxxsMajorBaseInfpModelSearch search, Sort sort);

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
}
