package com.jfbrother.customForm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.*;
import com.jfbrother.customForm.dao.CustomFormRepository;
import com.jfbrother.customForm.dao.CustomFormRuleRepository;
import com.jfbrother.customForm.jpa.*;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import com.jfbrother.customForm.model.JimuReportDbModel;
import com.jfbrother.customForm.model.JimuReportModel;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormRuleModelExtend;
import com.jfbrother.customForm.model.param.CustomFormChildModelParam;
import com.jfbrother.customForm.model.param.CustomFormModelParam;
import com.jfbrother.customForm.model.param.CustomFormRuleModelParam;
import com.jfbrother.customForm.model.response.CustomFormSelfYearResponseModel;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import com.jfbrother.customForm.model.search.CustomFormModelSearch;
import com.jfbrother.customForm.model.search.CustomFormRuleModelSearch;
import com.jfbrother.customForm.service.CustomFormChildService;
import com.jfbrother.customForm.service.CustomFormFieldService;
import com.jfbrother.customForm.service.CustomFormRuleService;
import com.jfbrother.customForm.service.CustomFormService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 自定义表单service实现
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Service
@Slf4j
public class CustomFormServiceImpl extends BaseServiceImpl implements CustomFormService {
    @Autowired
    private CustomFormRepository repository;
    @Autowired
    private CustomFormRuleRepository ruleRepository;
    @Autowired
    private CustomFormChildService childService;
    @Autowired
    private CustomFormFieldService fieldService;
    @Autowired
    private CustomFormRuleService ruleService;

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${jimuUrl}")
    private String jimuUrl;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    protected JdbcTemplate _jdbcTemplate;

    @Autowired
    @Qualifier("mysqlReportJdbcTemplate")
    protected JdbcTemplate jdbcTemplateReport;

    QCustomForm qCustomForm = QCustomForm.customForm;
    QCustomFormRule qCustomFormRule = QCustomFormRule.customFormRule;
    QCustomFormField qCustomFormField = QCustomFormField.customFormField;
    QCustomFormChild qCustomFormChild = QCustomFormChild.customFormChild;
    QCustomFormFill qCustomFormFill = QCustomFormFill.customFormFill;
    QCustomFormRelease qCustomFormRelease = QCustomFormRelease.customFormRelease;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormModelExtend post(CustomFormModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (StringUtils.isEmpty(model.getName())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单名不能为空.");
        }
        long count = queryFactory.selectFrom(qCustomForm).where(qCustomForm.name.eq(model.getName())).fetchCount();
        if (count > 0) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单名不能重复.");
        }

        CustomForm entity = CopyUtils.copyBean(model, CustomForm.class);
        entity = repository.save(entity);

        String id = entity.getId();
        //保存表单规则
        ruleService.post(new CustomFormRuleModelParam() {
            {
                setFormId(id);
                setText(model.getRuleText());
            }
        });
        //创建物理表
        fieldService.createPhysicalTable(model.getName(), model.getChineseExplain(), model.getTableColumn());
        //保存表字段
        if (!StringUtils.isEmpty(model.getTableColumn()) && model.getTableColumn().size() > 0) {
            fieldService.saveAll(id, model.getTableColumn());
        }
        //保存子表信息
        if (!StringUtils.isEmpty(model.getTableChild()) && model.getTableChild().size() > 0) {
            childService.saveAll(id, model.getTableChild());
        }

        return CopyUtils.copyBean(entity, CustomFormModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormModelExtend put(String id, CustomFormModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        if (StringUtils.isEmpty(model.getName())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单名不能为空.");
        }
        long count = queryFactory.selectFrom(qCustomForm).where(qCustomForm.id.ne(id).and(qCustomForm.name.eq(model.getName()))).fetchCount();
        if (count > 0) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单名不能重复.");
        }

        CustomForm entity = findCustomFormById(id);
        final String oldTableName = entity.getName();
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);

        //更新表单规则
        saveFormRule(id, model.getRuleText());

        //更新物理表名
        if (!model.getName().equals(oldTableName)) {
            log.info("更新表名【{}】为【{}】", oldTableName, model.getName());
            try {
                _jdbcTemplate.execute("ALTER TABLE " + oldTableName + " RENAME " + model.getName() + ";");
            } catch (Exception e) {
                throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
            }
        }
        List<CustomFormFieldModel> oldTableColumn = queryFactory.selectFrom(qCustomFormField).where(qCustomFormField.pid.eq(id)).fetch().stream().map(item -> CopyUtils.copyBean(item, CustomFormFieldModel.class)).collect(Collectors.toList());
        //更新物理表
        fieldService.updatePhysicalTable(model.getName(), model.getChineseExplain(), model.getTableColumn(), oldTableColumn);
        //更新表单字段
        saveFormField(id, model.getTableColumn(), oldTableColumn.stream().map(CustomFormFieldModel::getId).collect(Collectors.toList()));
        //保存子表信息
        saveFormChild(id, model.getTableChild());

        return CopyUtils.copyBean(entity, CustomFormModelExtend.class);
    }

    private void saveFormRule(String id, String ruleText) {
        List<CustomFormRuleModelExtend> list = ruleService.get(new CustomFormRuleModelSearch() {
            {
                setFormId(id);
            }
        }, Sort.by(Sort.Direction.ASC, "createTime"));

        CustomFormRule entity;
        if (list == null || list.size() == 0) {
            entity = new CustomFormRule();
            entity.setFormId(id);
        } else {
            entity = CopyUtils.copyBean(list.get(0), CustomFormRule.class);
        }
        entity.setText(ruleText);

        ruleRepository.save(entity);
    }

    private void saveFormField(String id, List<CustomFormFieldModel> tableColumn, List<String> oldIds) {
        List<String> delIds;
        if (StringUtils.isEmpty(tableColumn) || tableColumn.size() == 0) {
            delIds = oldIds;
        } else {
            List<String> newIds = tableColumn.stream().filter(item -> !StringUtils.isEmpty(item.getId())).map(CustomFormFieldModel::getId).collect(Collectors.toList());
            //差集
            delIds = oldIds.stream().filter(fieldId -> !newIds.contains(fieldId)).collect(Collectors.toList());
            fieldService.saveAll(id, tableColumn);
        }

        if (delIds.size() > 0) {
            fieldService.delete(delIds);
        }
    }

    private void saveFormChild(String id, List<CustomFormChildModelParam> tableChild) {
        List<CustomFormChild> oldList = queryFactory.selectFrom(qCustomFormChild).where(qCustomFormChild.formId.eq(id)).fetch();
        //待更新的子表表名map
        Map<String, String> oldTableNameMap = oldList.stream().collect(Collectors.toMap(CustomFormChild::getId, CustomFormChild::getTableName));
        //待更新的子表id
        List<String> oldIds = oldList.stream().map(CustomFormChild::getId).collect(Collectors.toList());

        Map<String, List<CustomFormFieldModel>> fieldMap = new HashMap<>();
        if (oldIds.size() > 0) {
            fieldMap = queryFactory.select(qCustomFormField).from(qCustomFormField)
                    .where(qCustomFormField.pid.in(oldIds)).fetch().stream()
                    .map(item -> CopyUtils.copyBean(item, CustomFormFieldModel.class)).collect(Collectors.groupingBy(CustomFormFieldModel::getPid));
        }

        List<String> delIds;
        if (StringUtils.isEmpty(tableChild) || tableChild.size() == 0) {
            delIds = oldIds;
        } else {
            List<String> newIds = tableChild.stream().filter(item -> !StringUtils.isEmpty(item.getId())).map(CustomFormChildModelParam::getId).collect(Collectors.toList());
            //差集
            delIds = oldIds.stream().filter(fieldId -> !newIds.contains(fieldId)).collect(Collectors.toList());

            for (CustomFormChildModelParam item : tableChild) {
                item.setFormId(id);

                String childId = item.getId();
                if (oldIds.contains(childId)) {
                    //更新物理表名
                    if (!item.getTableName().equals(oldTableNameMap.get(childId))) {
                        _jdbcTemplate.execute("ALTER TABLE " + oldTableNameMap.get(childId) + " RENAME " + item.getTableName());
                    }
                    //更新物理表
                    fieldService.updatePhysicalTable(item.getTableName(), item.getTableComment(), item.getTableColumn(), fieldMap.get(childId));
                    //更新表字段
                    saveFormField(childId, item.getTableColumn(), fieldMap.get(childId) == null ? new ArrayList<>() : fieldMap.get(childId).stream().map(CustomFormFieldModel::getId).collect(Collectors.toList()));
                    childService.put(childId, item);
                } else {
                    childService.save(id, item);
                }
            }
        }

        if (delIds.size() > 0) {
            childService.delete(delIds);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormModelExtend patch(String id, CustomFormModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomForm entity = findCustomFormById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concatAll(String.class, CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormModelExtend.class);
    }

    @Override
    public CustomFormModelExtend get(String id) {
        CustomForm entity = findCustomFormById(id);

        CustomFormModelExtend model = CopyUtils.copyBean(entity, CustomFormModelExtend.class);
        //使用时间范围
        List<Long> dateTimes = new ArrayList<>();
        if (!StringUtils.isEmpty(model.getStartDateTime())) {
            dateTimes.add(model.getStartDateTime());
        }
        if (!StringUtils.isEmpty(model.getEndDateTime())) {
            dateTimes.add(1, model.getEndDateTime());
        }
        model.setDateTimes(dateTimes);
        //表单规则
        CustomFormRuleModelExtend customFormRule = ruleService.findCustomFormRuleByFormId(id);
        if (customFormRule != null) {
            model.setRuleText(customFormRule.getText());
            model.setCustomFormRule(customFormRule);
        }
        //表单字段
        model.setTableColumn(fieldService.get(new CustomFormFieldModelSearch() {{
            setPid(id);
        }}, Sort.by(Sort.Direction.ASC, "createTime")));
        //子表信息
        model.setTableChild(childService.getChildFormsByFormId(id));

        return model;
    }

    @Override
    public PageOb<CustomFormModelExtend> get(CustomFormModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomForm, qCustomForm.jimuReportId)
                .from(qCustomForm);

        Predicate pre = fillPredicate(search);
        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qCustomForm);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        //获取自定义报表名称
        List<String> jimuReportIds = fetchResults.getResults().stream().filter(tuple -> !StringUtils.isEmpty(tuple.get(qCustomForm.jimuReportId))).map(tuple -> tuple.get(qCustomForm.jimuReportId)).collect(Collectors.toList());
        Map<String, Object> args = new HashMap<>();
        args.put("ids", jimuReportIds);
        NamedParameterJdbcTemplate givenParamJdbcTemp = new NamedParameterJdbcTemplate(jdbcTemplateReport);
        List<JimuReportModel> jimuReportList = givenParamJdbcTemp.query("SELECT id,name FROM jimu_report WHERE id in (:ids)", args, new BeanPropertyRowMapper(JimuReportModel.class));

        Map<String, String> jimuMap = jimuReportList.stream().collect(Collectors.toMap(JimuReportModel::getId, JimuReportModel::getName));

        return CopyUtils.copyPageObByQueryResults(fetchResults, CustomFormModelExtend.class, tuple -> {
            CustomFormModelExtend model = CopyUtils.copyBean(tuple.get(qCustomForm), CustomFormModelExtend.class);
            if (!StringUtils.isEmpty(model.getJimuReportId())) {
                model.setJimuReportName(jimuMap.get(model.getJimuReportId()));
            }

            return model;
        });
    }

    @Override
    public List<CustomFormModelExtend> get(CustomFormModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomForm> pageCustomForm = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomForm.getContent(), CustomFormModelExtend.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        List<CustomForm> list = queryFactory.selectFrom(qCustomForm).where(qCustomForm.id.in(ids)).fetch();

        List<String> ruleIds = queryFactory.select(qCustomFormRule.id).from(qCustomFormRule).where(qCustomFormRule.formId.in(ids)).fetch();
        List<String> fieldIds = queryFactory.select(qCustomFormField.id).from(qCustomFormField).where(qCustomFormField.pid.in(ids)).fetch();
        List<String> childIds = queryFactory.select(qCustomFormChild.id).from(qCustomFormChild).where(qCustomFormChild.formId.in(ids)).fetch();

        repository.deleteBatch(ids);

        //删除规则
        if (ruleIds.size() > 0) {
            ruleService.delete(ruleIds);
        }
        //删除字段
        if (fieldIds.size() > 0) {
            fieldService.delete(fieldIds);
        }
        //删除子表
        if (childIds.size() > 0) {
            childService.delete(childIds);
        }

        try {
            //删除物理表
            List<String> tableNameList = list.stream().filter(item -> !StringUtils.isEmpty(item.getName())).map(CustomForm::getName).collect(Collectors.toList());
            StringBuffer deleteSql = new StringBuffer();
            deleteSql.append("DROP TABLE IF EXISTS ").append(String.join(",", tableNameList))
                    .append(";");

            log.info("删除表sql:{}", deleteSql);
            _jdbcTemplate.execute(deleteSql.toString());
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLogical(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }

        repository.deleteBatchLogical(ids);
    }

    @Override
    public List<CustomFormSelfYearResponseModel> getSelfYear(CustomFormModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomForm, qCustomFormFill)
                .from(qCustomForm)
                .leftJoin(qCustomFormFill)
                .on(qCustomForm.id.eq(qCustomFormFill.releaseId)
                        .and(qCustomFormFill.userId.eq(LoginUtils.getUserInfo().getId()))
                )
                //启用
                .where(qCustomForm.isEnable.eq(BooleanEnum.YES.getStatus()).and(qCustomForm.startDateTime.isNotNull()));

        Predicate pre = fillPredicate(search);
        Map<Integer, List<CustomFormModelExtend>> listMap = jpaQuery.where(pre)
                .orderBy(qCustomForm.endDateTime.desc(), qCustomForm.startDateTime.desc())
                .fetch().stream()
                .map(tuple -> {
                    CustomFormModelExtend dto = CopyUtils.copyBean(tuple.get(qCustomForm), CustomFormModelExtend.class);
                    //所属年份
                    if (!StringUtils.isEmpty(dto.getStartDateTime())) {
                        dto.setYears(LocalDateUtils.LongToLocalDate(dto.getStartDateTime()).getYear());
                    }
                    //填报信息
                    if (tuple.get(qCustomFormFill) != null) {
                        CustomFormFillModelExtend fillData = CopyUtils.copyBean(tuple.get(qCustomFormFill), CustomFormFillModelExtend.class);
                        dto.setFillData(fillData);
                    }
                    //截止天数
                    if (!StringUtils.isEmpty(dto.getEndDateTime())) {
                        dto.setDeadline(LocalDateUtils.getBetweenDays(LocalDate.now(), LocalDateUtils.LongToLocalDate(dto.getEndDateTime())));
                    }

                    return dto;
                }).collect(Collectors.groupingBy(CustomFormModelExtend::getYears));

        List<CustomFormSelfYearResponseModel> list = new ArrayList<>();
        for (Map.Entry<Integer, List<CustomFormModelExtend>> entry : listMap.entrySet()) {
            CustomFormSelfYearResponseModel model = new CustomFormSelfYearResponseModel();
            model.setYear(entry.getKey());
            model.setChildren(entry.getValue());
            list.add(model);
        }

        return list.stream()
                .sorted(Comparator.comparing(CustomFormSelfYearResponseModel::getYear).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public CustomFormModelExtend getByReleaseId(String releaseId) {
        if (StringUtils.isEmpty(releaseId)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "发布id不能为空.");
        }
        String formId = queryFactory.select(qCustomFormRelease.formId).from(qCustomFormRelease).where(qCustomFormRelease.id.eq(releaseId)).fetchFirst();
        if (StringUtils.isEmpty(formId)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "表单id不能为空.");
        }

        return get(formId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJimuReport(String id) {
        //获取表单数据
        CustomForm customForm = findCustomFormById(id);

        if (StringUtils.isEmpty(customForm.getJimuReportId())) {
            //生成报表
            insertJimuReport(customForm);
        } else {
            //更新报表
            updateJimuReport(customForm);
        }
    }

    @Override
    public List<JimuReportModel> getJimuReport() {
        List<JimuReportModel> list = jdbcTemplateReport.query("SELECT id,code,name FROM jimu_report WHERE type = ? AND del_flag = ? AND template = ? ORDER BY create_time desc", new BeanPropertyRowMapper(JimuReportModel.class), "printinfo", 0, 1);

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void copyJimuReport(String id, String copyJimuReportId) {
        CustomForm customForm = findCustomFormById(id);

        //复制一个报表
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(jimuUrl + "/jmreport/reportCopy?id={1}", String.class, copyJimuReportId);

        log.info("复制积木报表【{}】，响应体【{}】", copyJimuReportId, responseEntity.getBody());

        //获取复制的报表id
        String copyJimuReportCode = jdbcTemplateReport.queryForObject("SELECT code FROM jimu_report WHERE id = ?", String.class, copyJimuReportId);
        String jimuReportId = jdbcTemplateReport.queryForObject("SELECT id FROM jimu_report WHERE code LIKE CONCAT(?,'%') ORDER BY create_time DESC LIMIT 1 ", String.class, copyJimuReportCode);

        customForm.setJimuReportId(jimuReportId);
        //删除旧的数据集
        List<String> dbIdList = jdbcTemplateReport.queryForList("SELECT id FROM jimu_report_db WHERE jimu_report_id = ?", String.class, jimuReportId);
        dbIdList.stream().forEach(dbId -> {
            ResponseEntity<String> _responseEntity = restTemplate.getForEntity(jimuUrl + "/jmreport/delDbData/{1}", String.class, dbId);

            log.info("删除积木报表【{}】的数据集【{}】，响应体【{}】", jimuReportId, dbId, _responseEntity.getBody());
        });
        //生成相关数据集
        createDS(jimuReportId, customForm.getId(), customForm.getChineseExplain(), customForm.getName(), 0, null);
        //生成子表数据集
        queryFactory.selectFrom(qCustomFormChild)
                .where(qCustomFormChild.formId.eq(customForm.getId()))
                .fetch().stream()
                .forEach(item -> createDS(jimuReportId, item.getId(), item.getTableComment(), item.getTableName(), 1, null));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeJimuReport(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        queryFactory.update(qCustomForm).set(qCustomForm.jimuReportId, (Expression<? extends String>) null).where(qCustomForm.id.in(ids)).execute();
    }

    public void insertJimuReport(CustomForm customForm) {
        String jimuReportId = UUID.randomUUID().toString().replaceAll("-", "");
        customForm.setJimuReportId(jimuReportId);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        JSONObject params = new JSONObject();
        Date now = new Date();
        params.put("code", DateUtils.DateToString(now, DateStyle.YYYYMMDDHHMMSS));
        params.put("createBy", "admin");
        params.put("delFlag", 0);
        params.put("id", jimuReportId);
        //
        JSONObject jsonStrObj = JSONObject.parseObject("{\"loopBlockList\":[],\"area\":false,\"excel_config_id\":\"714648706157465600\",\"printConfig\":{\"paper\":\"A4\",\"width\":210,\"height\":297,\"definition\":1,\"isBackend\":false,\"marginX\":10,\"marginY\":10,\"layout\":\"portrait\"},\"zonedEditionList\":[],\"rows\":{\"len\":100},\"dbexps\":[],\"dicts\":[],\"rpbar\":{\"show\":true,\"pageSize\":\"\",\"btnList\":[]},\"freeze\":\"A1\",\"dataRectWidth\":0,\"displayConfig\":{},\"background\":false,\"name\":\"sheet1\",\"autofilter\":{},\"styles\":[],\"validations\":[],\"cols\":{\"len\":50},\"merges\":[]}");
        jsonStrObj.put("excel_config_id", jimuReportId);
        params.put("jsonStr", jsonStrObj.toJSONString());
        params.put("name", customForm.getChineseExplain());
        params.put("template", 0);
        params.put("type", "printinfo");

        HttpEntity<Object> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(jimuUrl + "/jmreport/excelQueryName", entity, String.class);
        //生成报表
        createReport(jsonStrObj, params);

        //生成数据集
        createDS(jimuReportId, customForm.getId(), customForm.getChineseExplain(), customForm.getName(), 0, null);
        //生成子表数据集
        queryFactory.selectFrom(qCustomFormChild)
                .where(qCustomFormChild.formId.eq(customForm.getId()))
                .fetch().stream().forEach(item -> createDS(jimuReportId, item.getId(), item.getTableComment(), item.getTableName(), 1, null));
    }

    private void updateJimuReport(CustomForm customForm) {
        String jimuReportId = customForm.getJimuReportId();

        //获取保存的表名
        List<JimuReportDbModel> dbList = jdbcTemplateReport.query("SELECT db_code,id FROM jimu_report_db WHERE jimu_report_id = ?", new BeanPropertyRowMapper(JimuReportDbModel.class), jimuReportId);
        Map<String, String> dbMap = dbList.stream().collect(Collectors.toMap(JimuReportDbModel::getDbCode, JimuReportDbModel::getId));

        //更新数据集
        createDS(jimuReportId, customForm.getId(), customForm.getChineseExplain(), customForm.getName(), 0, dbMap.get(customForm.getName()));
        dbMap.remove(customForm.getName());

        //更新子表数据集
        queryFactory.selectFrom(qCustomFormChild)
                .where(qCustomFormChild.formId.eq(customForm.getId()))
                .fetch().stream()
                .forEach(item -> {
                    createDS(jimuReportId, item.getId(), item.getTableComment(), item.getTableName(), 1, dbMap.get(item.getTableName()));
                    dbMap.remove(item.getTableName());
                });

        //删除多余的数据集
        RestTemplate restTemplate = new RestTemplate();
        for (Map.Entry<String, String> db : dbMap.entrySet()) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(jimuUrl + "/jmreport/delDbData/{1}", String.class, db.getValue());

            log.info("删除积木报表【{}】的数据集【{}】，响应体【{}】", jimuReportId, db.getKey(), responseEntity.getBody());
        }
    }

    /**
     * 生成报表
     *
     * @param params
     * @param designerObj
     */
    private void createReport(JSONObject params, JSONObject designerObj) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        params.put("designerObj", designerObj);

        HttpEntity<Object> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(jimuUrl + "/jmreport/save", entity, String.class);

        log.info("生成积木报表【{}】，响应体【{}】", params.get("excel_config_id"), responseEntity.getBody());
    }

    /**
     * 生成数据集
     *
     * @param jimuReportId 积木报表id
     * @param pid          表id
     * @param dbChName     表中文注释
     * @param dbCode       表名
     * @param izList
     * @param id           数据集id
     */
    private void createDS(String jimuReportId, String pid, String dbChName, String dbCode, Integer izList, String id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        JSONObject params = new JSONObject();
        params.put("apiConvert", "");
        params.put("apiMethod", 0);
        params.put("apiUrl", baseUrl + "/api/v1/customFormFill/'${id}'/formFieldData/" + dbCode);
        params.put("dbChName", dbChName);
        params.put("dbCode", dbCode);
        params.put("dbSource", "");
        params.put("dbType", 1);
        params.put("fieldList", fieldService.getField(pid));
        params.put("id", StringUtils.isEmpty(id) ? "" : id);
        params.put("isList", izList);
        params.put("isPage", 0);
        params.put("jimuReportId", jimuReportId);
        params.put("jsonData", "");
        //整理参数
        Map<String, Object> param = new HashMap<>();
        param.put("orderNum", 1);
        param.put("paramName", "id");
        param.put("tableIndex", 1);
        param.put("_index", 0);
        param.put("_rowKey", 35);
        param.put("extJson", "");
        param.put("paramTxt", "");
        param.put("paramValue", "");
        params.put("paramList", new ArrayList<Map<String, Object>>() {
            {
                add(param);
            }
        });

        HttpEntity<Object> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(jimuUrl + "/jmreport/saveDb", entity, String.class);

        log.info("生成积木报表【{}】的数据集【{}】，响应体【{}】", jimuReportId, dbChName, responseEntity.getBody());
    }

    /**
     * 通过id查询自定义表单
     *
     * @param id
     * @return
     */
    private CustomForm findCustomFormById(String id) {
        CustomForm entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomForm found.");
        }
        return entity;
    }

    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, qCustomForm.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getChineseExplain())) {
            pre = ExpressionUtils.and(pre, qCustomForm.chineseExplain.like("%" + search.getChineseExplain() + "%"));
        }
        if (!StringUtils.isEmpty(search.getRemark())) {
            pre = ExpressionUtils.and(pre, qCustomForm.remark.like("%" + search.getRemark() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsEnable())) {
            pre = ExpressionUtils.and(pre, qCustomForm.isEnable.eq(search.getIsEnable()));
        }
        if (!StringUtils.isEmpty(search.getFillScope())) {
            pre = ExpressionUtils.and(pre, qCustomForm.fillScope.like("%" + search.getFillScope() + "%"));
        }
        if (!StringUtils.isEmpty(search.getApprovalProcess())) {
            pre = ExpressionUtils.and(pre, qCustomForm.approvalProcess.like("%" + search.getApprovalProcess() + "%"));
        }
        if (!StringUtils.isEmpty(search.getStartDateTime())) {
            pre = ExpressionUtils.and(pre, qCustomForm.startDateTime.eq(search.getStartDateTime()));
        }
        if (!StringUtils.isEmpty(search.getEndDateTime())) {
            pre = ExpressionUtils.and(pre, qCustomForm.endDateTime.eq(search.getEndDateTime()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomForm.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomForm.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomForm.isDelete.eq(0), qCustomForm.isDelete.isNull()));

        return pre;
    }
}
