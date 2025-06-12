package com.jfbrother.digitalPortraitSnap.service;

import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagRelationModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagRelationModelExtend;

import java.util.Map;

/**
 * 学生标签关系表Service接口
 * @author xinz 2023-03-28
 */
public interface GsStuTagRelationService extends BaseService{
    /**
     * 学生标签关系表添加
     * @param model 数据
     * @return
     */
    GsStuTagRelationModelExtend post(GsStuTagRelationModelParam model);

    /**
     * 删除学生已有标签
     * @param body
     */
    void deleteStuTag(Map<String,Object> body);

}
