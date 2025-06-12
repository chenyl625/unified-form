package com.jfbrother.echarts.service.impl;

import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.enums.DataCheckStatusEnum;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.DateUtils;
import com.jfbrother.connector.jpa.QConnectorField;
import com.jfbrother.customForm.jpa.QCustomFormFill;
import com.jfbrother.dataCenter.jpa.QDataErrorCorrection;
import com.jfbrother.echarts.service.EchartsDataService;
import com.jfbrother.standard.jpa.QStandardDataSource;
import com.jfbrother.work.jpa.QWorkFlowTask;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 图表数据管理表service实现
 *
 * @author chenyl@jfbrother.com 2019-12-05
 */
@SuppressWarnings("Duplicates")
@Service
public class EchartsDataServiceImpl extends BaseServiceImpl implements EchartsDataService {

    QCustomFormFill qCustomFormFill = QCustomFormFill.customFormFill;
    QWorkFlowTask qWorkFlowTask = QWorkFlowTask.workFlowTask;
    QDataErrorCorrection qDataErrorCorrection = QDataErrorCorrection.dataErrorCorrection;
    QConnectorField qConnectorField = QConnectorField.connectorField;
    QStandardDataSource qStandardDataSource = QStandardDataSource.standardDataSource;

    @Override
    public Map<String, Object> getFillDataAnalysis() {
        StringTemplate simpleTemplate = Expressions.stringTemplate("DATE_FORMAT(FROM_UNIXTIME({0}/1000),'%Y-%m')", qCustomFormFill.fillDateTime);

        List<String> xData = new ArrayList<>();
        List<Long> counts = new ArrayList<>();
        //填报量
        List<Tuple> items = queryFactory.select(simpleTemplate, qCustomFormFill.id.count())
                .from(qCustomFormFill)
                .where(qCustomFormFill.statusNum.eq(BooleanEnum.YES.getStatus()))
                .groupBy(simpleTemplate).orderBy(simpleTemplate.desc())
                //最近5个月
                .limit(5)
                .fetch().stream()
                .sorted(Comparator.comparing((Tuple tuple) -> tuple.get(simpleTemplate))).collect(Collectors.toList());

        items.forEach(tuple -> {
            xData.add(tuple.get(simpleTemplate));
            counts.add(tuple.get(qCustomFormFill.id.count()));
        });

        Map<String, Object> data = new HashMap<>();
        data.put("xData", xData);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(new HashMap<String, Object>() {
            {
                put("name", "填报量");
                put("value", counts);
            }
        });
        data.put("list", list);

        return data;
    }

    @Override
    public List<WorkFlowTaskModelExtend> getFillEfficiencyAnalysis() {
        NumberTemplate<Long> numberTemplate = Expressions.numberTemplate(Long.class, "IFNULL({0},UNIX_TIMESTAMP(NOW())*1000)", qWorkFlowTask.endDate);

        return queryFactory.select(qWorkFlowTask, numberTemplate.subtract(qWorkFlowTask.assignDate))
                .from(qWorkFlowTask)
                .leftJoin(qCustomFormFill).on(qWorkFlowTask.fillId.eq(qCustomFormFill.id))
                .where(qWorkFlowTask.assignDate.isNotNull().and(qWorkFlowTask.status.ne("已取消"))
                        .and(qWorkFlowTask.name.ne("开始").and(qWorkFlowTask.name.ne("结束环节")))
                        .and(qWorkFlowTask.assigeeName.isNotNull()))
                .orderBy(numberTemplate.subtract(qWorkFlowTask.assignDate).asc())
                //最近6条
                .limit(6)
                .fetch().stream()
                .map(tuple -> {
                    WorkFlowTaskModelExtend model = CopyUtils.copyBean(tuple.get(qWorkFlowTask), WorkFlowTaskModelExtend.class);
                    //办理时长
                    model.setDiffTimes(DateUtils.getDiffDateStr(tuple.get(numberTemplate.subtract(qWorkFlowTask.assignDate))));

                    return model;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getDataErrorSystemCount() {
        List<Map<String, Object>> list = queryFactory.select(qStandardDataSource.name, qStandardDataSource.id.count())
                .from(qDataErrorCorrection)
                .leftJoin(qConnectorField).on(qDataErrorCorrection.connFieldId.eq(qConnectorField.id))
                .leftJoin(qStandardDataSource).on(qConnectorField.fieldSource.eq(qStandardDataSource.id))
                .where(qDataErrorCorrection.checkStatus.eq(DataCheckStatusEnum.PASS.getStatus()))
                .groupBy(qStandardDataSource.id)
                .orderBy(qStandardDataSource.sortNum.asc())
                .fetch().stream()
                .map(tuple -> {
                    Map<String, Object> entity = new HashMap<>(2);
                    entity.put("name", tuple.get(qStandardDataSource.name));
                    entity.put("value", tuple.get(qStandardDataSource.id.count()));
                    return entity;
                }).collect(Collectors.toList());

        return list;
    }
}
