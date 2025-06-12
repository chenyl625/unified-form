package com.jfbrother.echarts.service;

import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;

import java.util.List;
import java.util.Map;

/**
 * 图表数据Service接口
 *
 * @author chenyl@jfbrother.com 2019-12-05
 */
public interface EchartsDataService extends BaseService {
    Map<String, Object> getFillDataAnalysis();

    List<WorkFlowTaskModelExtend> getFillEfficiencyAnalysis();

    List<Map<String,Object>> getDataErrorSystemCount();
}
