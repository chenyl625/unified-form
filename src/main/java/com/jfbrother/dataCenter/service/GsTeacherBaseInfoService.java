package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import com.jfbrother.dataCenter.model.extend.GsTeacherBaseInfoModelExtend;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 教师基本信息Service接口
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
public interface GsTeacherBaseInfoService extends BaseService {
    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    GsTeacherBaseInfoModel get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsTeacherBaseInfoModelExtend> get(GsTeacherBaseInfoModel search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsTeacherBaseInfoModel> get(GsTeacherBaseInfoModel search, Sort sort);

    /**
     * 教师和学生账号自动生成
     */
    void autoCreate();

    /**
     * 教师现任职务列表
     *
     * @return
     */
    List<Map<String, String>> getCurPosition();

    /**
     * 批量设置权限
     *
     * @param model
     */
    void setAuth(UserIdsAndRoleIdsModelOther model);
}
