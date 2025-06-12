package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.GsTeacherFamilyMemModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 教师家庭成员Service接口
* @author chenyl@jfbrother.com 2022-07-19
*/
public interface GsTeacherFamilyMemService extends BaseService{
    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    GsTeacherFamilyMemModel get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<GsTeacherFamilyMemModel> get(GsTeacherFamilyMemModel search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<GsTeacherFamilyMemModel> get(GsTeacherFamilyMemModel search, Sort sort);

}
