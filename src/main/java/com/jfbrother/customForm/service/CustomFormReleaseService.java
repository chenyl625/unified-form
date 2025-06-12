package com.jfbrother.customForm.service;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.enums.ConnTypeEnum;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.jpa.CustomFormRelease;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormReleaseConnModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormReleaseModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 自定义表单发布Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
public interface CustomFormReleaseService extends BaseService {
    /**
     * 自定义表单发布添加
     *
     * @param model 数据
     * @return
     */
    CustomFormReleaseModelExtend post(CustomFormReleaseModelParam model);

    /**
     * 自定义表单发布更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormReleaseModelExtend put(String id, CustomFormReleaseModelParam model);

    /**
     * 自定义表单发布部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormReleaseModelExtend patch(String id, CustomFormReleaseModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormReleaseModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormReleaseModelExtend> get(CustomFormReleaseModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormReleaseModelExtend> get(CustomFormReleaseModelSearch search, Sort sort);

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
     * 自定义表单发布填报列表获取
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<CustomFormReleaseModelExtend> getFill(CustomFormReleaseModelSearch search, Pageable pageable);

    /**
     * 自定义表单字段获取
     *
     * @param id
     * @return
     */
    List<CustomFormFieldModelExtend> getField(String id);

    /**
     * 保存字段
     *
     * @param id
     * @param listModel
     */
    void saveField(String id, List<CustomFormFieldModelExtend> listModel);

    /**
     * 获取字段关联信息
     *
     * @param id
     * @return
     */
    List<CustomFormReleaseConnModelExtend> getReleaseConnList(String id);

    JSONObject getConnData(String id, Map<String, Object> param);

    /**
     * 获取接口数据
     *
     * @param id
     * @param connType
     * @param param
     * @return
     */
    Map<String, Object> getConnDataMap(String id, ConnTypeEnum connType, Map<String, Object> param);

    /**
     * 根据id获取发布信息
     *
     * @param id
     * @return
     */
    CustomFormRelease findCustomFormReleaseById(String id);

    /**
     * 获取详细信息
     *
     * @param model
     * @return
     */
    CustomFormReleaseModelExtend getDetail(CustomFormReleaseModelExtend model);

    List<Map<String, Object>> getData(String id);

    /**
     * 下载导入模版
     *
     * @param id
     */
    void downloadTemplate(String id);

    /**
     * 导入数据
     *
     * @param id
     * @param file
     * @return
     */
    List<String> importFile(String id, MultipartFile file);

    /**
     * 以releaseId作为key,itemDomId为value的map
     * @return
     */
    Map<String,String> getItemDomIdMap();
}
