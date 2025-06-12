package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.DataErrorCorrectionModel;
import com.jfbrother.dataCenter.model.param.DataErrorCorrectionModelParam;
import com.jfbrother.dataCenter.model.extend.DataErrorCorrectionModelExtend;
import com.jfbrother.dataCenter.model.search.DataErrorCorrectionModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 数据纠错补录表Service接口
* @author chenyl@jfbrother.com 2022-08-05
*/
public interface DataErrorCorrectionService extends BaseService{
    /**
    * 数据纠错补录表添加
    *
    * @param model 数据
    * @return
    */
    DataErrorCorrectionModelExtend post(DataErrorCorrectionModelParam model);

    /**
    * 数据纠错补录表更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    DataErrorCorrectionModelExtend put(String id, DataErrorCorrectionModelParam model);

    /**
    * 数据纠错补录表部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    DataErrorCorrectionModelExtend patch(String id, DataErrorCorrectionModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    DataErrorCorrectionModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<DataErrorCorrectionModelExtend> get(DataErrorCorrectionModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<DataErrorCorrectionModelExtend> get(DataErrorCorrectionModelSearch search, Sort sort);

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
