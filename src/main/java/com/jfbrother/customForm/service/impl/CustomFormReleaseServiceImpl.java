package com.jfbrother.customForm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.constant.RoleConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.enums.ConnTypeEnum;
import com.jfbrother.baseserver.jpa.QSysConnUserRole;
import com.jfbrother.baseserver.jpa.QSysRoles;
import com.jfbrother.baseserver.jpa.SysConnUserRole;
import com.jfbrother.baseserver.jwt.JwtUser;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.*;
import com.jfbrother.connector.jpa.QConnectorField;
import com.jfbrother.connector.jpa.QConnectorInfo;
import com.jfbrother.connector.service.ConnectorInfoService;
import com.jfbrother.customForm.dao.CustomFormReleaseConnRepository;
import com.jfbrother.customForm.dao.CustomFormReleaseRepository;
import com.jfbrother.customForm.dao.CustomFormReleaseTaskRepository;
import com.jfbrother.customForm.jpa.*;
import com.jfbrother.customForm.model.extend.*;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.param.CustomFormReleaseModelParam;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import com.jfbrother.customForm.model.search.CustomFormReleaseModelSearch;
import com.jfbrother.customForm.service.CustomFormFieldService;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.customForm.service.CustomFormReleaseService;
import com.jfbrother.customForm.service.CustomFormService;
import com.jfbrother.standard.jpa.QStandardDataSource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * 自定义表单发布service实现
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Service
@Slf4j
public class CustomFormReleaseServiceImpl extends BaseServiceImpl implements CustomFormReleaseService {
    @Autowired
    private CustomFormReleaseRepository repository;
    @Autowired
    private CustomFormReleaseTaskRepository customFormReleaseTaskRepository;
    @Autowired
    private CustomFormReleaseConnRepository customFormReleaseConnRepository;

    @Autowired
    private CustomFormService customFormService;
    @Autowired
    private ConnectorInfoService connectorInfoService;
    @Autowired
    private CustomFormFieldService customFormFieldService;
    @Autowired
    private CustomFormFillService customFormFillService;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    protected JdbcTemplate _jdbcTemplate;

    QCustomFormRelease qCustomFormRelease = QCustomFormRelease.customFormRelease;
    QCustomForm qCustomForm = QCustomForm.customForm;
    QSysRoles qSysRoles = QSysRoles.sysRoles;
    QCustomFormReleaseTask qCustomFormReleaseTask = QCustomFormReleaseTask.customFormReleaseTask;
    QCustomFormChild qCustomFormChild = QCustomFormChild.customFormChild;
    QCustomFormField qCustomFormField = QCustomFormField.customFormField;
    QCustomFormReleaseConn qCustomFormReleaseConn = QCustomFormReleaseConn.customFormReleaseConn;
    QConnectorInfo qConnectorInfo = QConnectorInfo.connectorInfo;
    QCustomFormFill qCustomFormFill = QCustomFormFill.customFormFill;
    QConnectorField qConnectorField = QConnectorField.connectorField;
    QStandardDataSource qStandardDataSource = QStandardDataSource.standardDataSource;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormReleaseModelExtend post(CustomFormReleaseModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        CustomFormRelease entity = CopyUtils.copyBean(model, CustomFormRelease.class);

        entity = repository.save(entity);

        //保存流程环节标识
        if (model.getIsFlow() == BooleanEnum.YES.getStatus()) {
            saveReleaseTask(model.getReleaseTaskList(), entity.getId());
        }

        return CopyUtils.copyBean(entity, CustomFormReleaseModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormReleaseModelExtend put(String id, CustomFormReleaseModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormRelease entity = findCustomFormReleaseById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);

        //保存流程环节标识
        if (model.getIsFlow() == BooleanEnum.YES.getStatus()) {
            queryFactory.delete(qCustomFormReleaseTask).where(qCustomFormReleaseTask.releaseId.eq(id)).execute();
            saveReleaseTask(model.getReleaseTaskList(), entity.getId());
        }

        return CopyUtils.copyBean(entity, CustomFormReleaseModelExtend.class);
    }

    private void saveReleaseTask(List<CustomFormReleaseTaskModelExtend> tasks, String releaseId) {
        if (!StringUtils.isEmpty(tasks) && tasks.size() > 0) {
            AtomicInteger i = new AtomicInteger(1);
            List<CustomFormReleaseTask> releaseTaskList = tasks.stream().map(item -> {
                CustomFormReleaseTask task = CopyUtils.copyBean(item, CustomFormReleaseTask.class);
                task.setReleaseId(releaseId);
                task.setSortNum(i.getAndIncrement());
                return task;
            }).collect(Collectors.toList());

            customFormReleaseTaskRepository.saveAll(releaseTaskList);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormReleaseModelExtend patch(String id, CustomFormReleaseModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormRelease entity = findCustomFormReleaseById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormReleaseModelExtend.class);
    }

    @Override
    public CustomFormReleaseModelExtend get(String id) {
        CustomFormRelease entity = findCustomFormReleaseById(id);
        CustomFormReleaseModelExtend model = CopyUtils.copyBean(entity, CustomFormReleaseModelExtend.class);
        //使用时间范围
        List<Long> dateTimes = new ArrayList<>();
        if (!StringUtils.isEmpty(model.getStartDateTime())) {
            dateTimes.add(model.getStartDateTime());
        }
        if (!StringUtils.isEmpty(model.getEndDateTime())) {
            dateTimes.add(1, model.getEndDateTime());
        }
        model.setDateTimes(dateTimes);

        return getDetail(model);
    }

    @Override
    public List<CustomFormReleaseConnModelExtend> getReleaseConnList(String id) {
        return queryFactory.select(qCustomFormReleaseConn, qCustomFormField.field)
                .from(qCustomFormReleaseConn)
                .leftJoin(qCustomFormField).on(qCustomFormReleaseConn.formFieldId.eq(qCustomFormField.id))
                .where(qCustomFormReleaseConn.releaseId.eq(id)
                        .and(qCustomFormReleaseConn.isLink.eq(BooleanEnum.YES.getStatus()))
                        .and(qCustomFormReleaseConn.linkName.isNotNull().or(qCustomFormReleaseConn.linkName.ne("")))
                )
                .fetch().stream()
                .map(tuple -> {
                    CustomFormReleaseConnModelExtend connModel = CopyUtils.copyBean(tuple.get(qCustomFormReleaseConn), CustomFormReleaseConnModelExtend.class);
                    connModel.setFieldName(tuple.get(qCustomFormField.field));
                    return connModel;
                }).collect(Collectors.toList());
    }

    @Override
    public PageOb<CustomFormReleaseModelExtend> get(CustomFormReleaseModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomFormRelease
                , qCustomForm.chineseExplain
                , qSysRoles.roleName)
                .from(qCustomFormRelease)
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .leftJoin(qSysRoles).on(qCustomFormRelease.fillScope.eq(qSysRoles.id));

        Predicate pre = fillPredicate(search);
        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qCustomFormRelease);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, CustomFormReleaseModelExtend.class, tuple -> {
            CustomFormReleaseModelExtend model = CopyUtils.copyBean(tuple.get(qCustomFormRelease), CustomFormReleaseModelExtend.class);
            model.setFormName(tuple.get(qCustomForm.chineseExplain));
            model.setFillScopeName(tuple.get(qSysRoles.roleName));

            return model;
        });
    }

    @Override
    public List<CustomFormReleaseModelExtend> get(CustomFormReleaseModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomFormRelease> pageCustomFormRelease = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomFormRelease.getContent(), CustomFormReleaseModelExtend.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        //判断是否有填报的表
        long count = queryFactory.selectFrom(qCustomFormFill).where(qCustomFormFill.releaseId.in(ids)).fetchCount();
        if (count > 0) {
            throw new ServiceException(ResultCode.FORBIDDEN, "存在已经填报的表单!");
        }

        repository.deleteBatch(ids);
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
    public PageOb<CustomFormReleaseModelExtend> getFill(CustomFormReleaseModelSearch search, Pageable pageable) {
        //启用
        search.setIsEnable(BooleanEnum.YES.getStatus());

        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomFormRelease, qSysRoles.roleName)
                .from(qCustomFormRelease)
                .leftJoin(qSysRoles).on(qCustomFormRelease.fillScope.eq(qSysRoles.id));

        Predicate pre = fillPredicate(search);
        //按角色权限查询
        pre = getPredicateByRole(queryFactory, pre);
        //填报时间段
        long currentTime = System.currentTimeMillis();
        pre = ExpressionUtils.and(pre, qCustomFormRelease.startDateTime.loe(currentTime).and(qCustomFormRelease.endDateTime.goe(currentTime)));

        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qCustomFormRelease);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, CustomFormReleaseModelExtend.class, tuple -> {
            CustomFormReleaseModelExtend model = CopyUtils.copyBean(tuple.get(qCustomFormRelease), CustomFormReleaseModelExtend.class);
            model.setFillScopeName(tuple.get(qSysRoles.roleName));

            //截止天数
            if (!StringUtils.isEmpty(model.getEndDateTime())) {
                model.setDeadline(LocalDateUtils.getBetweenDays(LocalDate.now(), LocalDateUtils.LongToLocalDate(model.getEndDateTime())));
            }

            return model;
        });
    }

    @Override
    public List<CustomFormFieldModelExtend> getField(String id) {
        CustomFormRelease customFormRelease = findCustomFormReleaseById(id);
        String formId = customFormRelease.getFormId();

        return queryFactory.select(qCustomFormField, qCustomFormReleaseConn, qCustomFormChild.id)
                .from(qCustomFormField)
                .leftJoin(qCustomFormReleaseConn)
                .on(qCustomFormReleaseConn.formFieldId.eq(qCustomFormField.id).and(qCustomFormReleaseConn.releaseId.eq(id)))
                .leftJoin(qCustomFormChild)
                .on(qCustomFormField.pid.eq(qCustomFormChild.id))
                .where(qCustomFormField.pid.eq(formId).or(qCustomFormChild.formId.eq(formId)))
                .orderBy(qCustomFormField.sortNum.asc(), qCustomFormField.createTime.asc())
                .fetch().stream()
                .map(tuple -> {
                    CustomFormFieldModelExtend model = CopyUtils.copyBean(tuple.get(qCustomFormField), CustomFormFieldModelExtend.class);

                    CustomFormReleaseConn releaseConn = tuple.get(qCustomFormReleaseConn);
                    if (releaseConn != null) {
                        CustomFormReleaseConnModelExtend releaseConnModelExtend = CopyUtils.copyBean(releaseConn, CustomFormReleaseConnModelExtend.class);
                        if (!StringUtils.isEmpty(releaseConn.getConnType())) {
                            releaseConnModelExtend.setConnTypeEnum(ConnTypeEnum.getByStatus(releaseConn.getConnType()));
                        }
                        model.setReleaseConn(releaseConnModelExtend);
                    }
                    //
                    String childId = tuple.get(qCustomFormChild.id);
                    if (!StringUtils.isEmpty(childId)) {
                        model.setIsChildField(BooleanEnum.YES.getStatus());
                        model.setChildId(childId);
                    } else {
                        model.setIsChildField(BooleanEnum.NO.getStatus());
                    }

                    return model;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveField(String id, List<CustomFormFieldModelExtend> listModel) {
        //删除
        queryFactory.delete(qCustomFormReleaseConn).where(qCustomFormReleaseConn.releaseId.eq(id)).execute();
        //保存
        List<CustomFormReleaseConn> list = new ArrayList<>();
        listModel.stream().forEach(item -> {
            CustomFormReleaseConnModelExtend releaseConnModel = item.getReleaseConn();
            if (releaseConnModel != null) {
                CustomFormReleaseConn entity = CopyUtils.copyBean(releaseConnModel, CustomFormReleaseConn.class, Constant.UPDATE_IGNORE_FIELDS);
                entity.setReleaseId(id);
                entity.setFormFieldId(item.getId());
                entity.setChildId(item.getChildId());
                entity.setIsChildField(item.getIsChildField());
                //关联类型
                Integer connType;
                if (StringUtils.isEmpty(releaseConnModel.getConnTypeEnum())) {
                    //默认自动
                    connType = !StringUtils.isEmpty(releaseConnModel.getConnId()) && !StringUtils.isEmpty(releaseConnModel.getConnFieldName()) ? ConnTypeEnum.AUTO_REQUEST.getStatus() : null;
                } else {
                    connType = releaseConnModel.getConnTypeEnum().getStatus();
                }
                entity.setConnType(connType);
                list.add(entity);
            }
        });
        if (list.size() > 0) {
            customFormReleaseConnRepository.saveAll(list);
        }
    }

    @Override
    public JSONObject getConnData(String id, Map<String, Object> param) {
        Map<String, String> connMap = queryFactory.select(qCustomFormReleaseConn.connId, qConnectorInfo.connUrl)
                .from(qCustomFormReleaseConn)
                .leftJoin(qCustomFormRelease).on(qCustomFormReleaseConn.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qConnectorInfo).on(qCustomFormReleaseConn.connId.eq(qConnectorInfo.id))
                .where(qCustomFormRelease.id.eq(id).and(qCustomFormReleaseConn.connId.isNotNull().and(qCustomFormReleaseConn.connId.trim().length().gt(0))))
                .groupBy(qCustomFormReleaseConn.connId)
                .fetch().stream().collect(Collectors.toMap(tuple -> tuple.get(qCustomFormReleaseConn.connId), tuple -> tuple.get(qConnectorInfo.connUrl)));

        //根据接口获取数据
        JSONObject connData = new JSONObject();
        if (connMap.size() > 0) {
            for (Map.Entry<String, String> conn : connMap.entrySet()) {
                String connId = conn.getKey();
                //获取数据字典map
                Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(conn.getValue());

                JSONArray arrayData = connectorInfoService.getData(connId, param);
                if (dictMap.size() > 0) {
                    //转化数据字典
                    for (Object dataObj : arrayData) {
                        JSONObject data = (JSONObject) JSON.toJSON(dataObj);
                        for (Map.Entry<String, Map<String, String>> entry : dictMap.entrySet()) {
                            String key = entry.getKey();
                            if (StringUtils.isEmpty(data.get(key))) {
                                continue;
                            }
                            data.put(key, entry.getValue().get(data.get(key).toString()));
                        }
                    }
                }
                connData.put(connId, arrayData);
            }
        }
        return connData;
    }

    @Override
    public Map<String, Object> getConnDataMap(String id, ConnTypeEnum connType, Map<String, Object> param) {
        List<Tuple> list = queryFactory.select(qCustomFormReleaseConn
                , qConnectorInfo.connUrl
        )
                .from(qCustomFormReleaseConn)
                .leftJoin(qCustomFormRelease).on(qCustomFormReleaseConn.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qConnectorInfo).on(qCustomFormReleaseConn.connId.eq(qConnectorInfo.id))
                .where(qCustomFormRelease.id.eq(id)
                        .and(qCustomFormReleaseConn.connType.eq(connType.getStatus()))
                        .and(qCustomFormReleaseConn.connId.isNotNull().and(qCustomFormReleaseConn.connId.trim().length().gt(0)))
                        .and(qCustomFormReleaseConn.connFieldName.isNotNull().or(qCustomFormReleaseConn.connFieldName.ne("")))
                ).fetch();

        //根据接口获取数据
        Map<String, JSONArray> connData = new HashMap<>();
        for (Tuple tuple : list) {
            String connId = tuple.get(qCustomFormReleaseConn).getConnId();
            if (!StringUtils.isEmpty(connData.get(connId))) {
                continue;
            }
            //获取数据字典map
            Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(tuple.get(qConnectorInfo.connUrl));
            JSONArray arrayData = connectorInfoService.getData(connId, param);
            if (dictMap.size() > 0) {
                //转化数据字典
                for (Object dataObj : arrayData) {
                    JSONObject data = (JSONObject) JSON.toJSON(dataObj);
                    for (Map.Entry<String, Map<String, String>> entry : dictMap.entrySet()) {
                        String key = entry.getKey();
                        if (StringUtils.isEmpty(data.get(key))) {
                            continue;
                        }
                        data.put(key, entry.getValue().get(data.get(key).toString()));
                    }
                }
            }
            connData.put(connId, arrayData);
        }

        //
        Map<String, Object> map = new HashMap<>();
        list.stream().forEach(tuple -> {
            CustomFormReleaseConn releaseConn = tuple.get(qCustomFormReleaseConn);

            JSONArray dataList = connData.get(releaseConn.getConnId());

            map.put(releaseConn.getFormFieldId(), dataList.stream().map(item -> {
                JSONObject data = (JSONObject) item;
                return data.get(releaseConn.getConnFieldName());
            }).collect(Collectors.toList()));
        });

        return map;
    }

    @Override
    public CustomFormReleaseModelExtend getDetail(CustomFormReleaseModelExtend model) {
        String id = model.getId();
        //流程环节标识
        List<CustomFormReleaseTaskModelExtend> releaseTaskList = queryFactory.selectFrom(qCustomFormReleaseTask).where(qCustomFormReleaseTask.releaseId.eq(id)).orderBy(qCustomFormReleaseTask.sortNum.asc())
                .fetch().stream().map(item -> CopyUtils.copyBean(item, CustomFormReleaseTaskModelExtend.class))
                .collect(Collectors.toList());
        model.setReleaseTaskList(releaseTaskList);
        //字段关联信息
        List<CustomFormReleaseConnModelExtend> connList = queryFactory.select(qCustomFormReleaseConn
                , qCustomFormField.field
                , qConnectorField.fieldSource
                , qStandardDataSource.name
        )
                .from(qCustomFormReleaseConn)
                .leftJoin(qCustomFormField).on(qCustomFormReleaseConn.formFieldId.eq(qCustomFormField.id))
                .leftJoin(qConnectorField).on(qCustomFormReleaseConn.connId.eq(qConnectorField.connId).and(qCustomFormReleaseConn.connFieldName.eq(qConnectorField.fieldName)))
                .leftJoin(qStandardDataSource).on(qConnectorField.fieldSource.eq(qStandardDataSource.id))
                .where(qCustomFormReleaseConn.releaseId.eq(id))
                .fetch().stream()
                .map(tuple -> {
                    CustomFormReleaseConn releaseConn = tuple.get(qCustomFormReleaseConn);
                    CustomFormReleaseConnModelExtend connModel = CopyUtils.copyBean(releaseConn, CustomFormReleaseConnModelExtend.class, "connType");
                    //处理响应模型
                    if (!StringUtils.isEmpty(releaseConn.getConnType())) {
                        connModel.setConnTypeEnum(ConnTypeEnum.getByStatus(releaseConn.getConnType()));
                    }
                    connModel.setFieldName(tuple.get(qCustomFormField.field));
                    connModel.setConnFieldSource(tuple.get(qConnectorField.fieldSource));
                    connModel.setConnFieldSourceName(tuple.get(qStandardDataSource.name));
                    return connModel;
                }).collect(Collectors.toList());
        //环节列表
        model.setReleaseConnList(connList.stream().filter(item -> item.getIsLink() == BooleanEnum.YES.getStatus()).collect(Collectors.toList()));
        //接口列表
        model.setConnectorList(connList.stream().filter(item -> !StringUtils.isEmpty(item.getConnId()) && !StringUtils.isEmpty(item.getConnFieldName())).collect(Collectors.toList()));
        //表单信息
        model.setFormData(customFormService.get(model.getFormId()));

        return model;
    }

    @Override
    public List<Map<String, Object>> getData(String id) {
        CustomForm customForm = queryFactory.select(qCustomForm)
                .from(qCustomFormRelease)
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .where(qCustomFormRelease.id.eq(id)).fetchFirst();
        if (customForm == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到自定义表单信息.");
        }
        //获取表字段
        List<String> tableFieldList = queryFactory.select(qCustomFormField.field).from(qCustomFormField).where(qCustomFormField.pid.eq(customForm.getId())).fetch();

        List<Map<String, Object>> list = new ArrayList<>();
        if (tableFieldList.size() > 0 && !StringUtils.isEmpty(customForm.getName())) {
            String _tableName = StringUtils.underlineToCamelCase(customForm.getName());

            StringBuffer sql = new StringBuffer();
            sql.append("select ")
                    .append(tableFieldList.stream().map(str -> _tableName + "." + str).collect(Collectors.joining(",")))
                    //流程状态
                    .append(",fill.flow_status,FROM_UNIXTIME(fill_date_time/1000,'%Y-%m-%d %H:%i:%S') as fill_date_time,teacher.xm as fill_user_name," +
                            "flow.flow_task_name from ")
                    .append(customForm.getName()).append(" ")
                    .append(_tableName).append(" ")
                    .append("left join custom_form_fill fill on fill.id = ")
                    .append(_tableName).append(".business_id ")
                    .append("left join sys_users users on users.id = ")
                    .append(_tableName).append(".create_user ")
                    .append("left join gs_teacher_base_info teacher on teacher.gh = ")
                    .append("fill.user_id ")
                    .append("left join (SELECT fill_id,GROUP_CONCAT(name,',') as flow_task_name from work_flow_task where `status`='待办' GROUP BY fill_id) flow on flow.fill_id=")
                    .append("fill.id ")
                    //提交的数据
                    .append("where fill.release_id = ? and fill.status_num = 1 ");
            //设置用户权限
            connSqlByRole(sql).append(" order by fill.create_time asc;");

            list = _jdbcTemplate.queryForList(sql.toString(), new Object[]{id});
        }

        return list;
    }

    private StringBuffer connSqlByRole(StringBuffer sql) {
        //判断角色
        JwtUser user = LoginUtils.getUserInfo();
        if (user == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到当前用户信息.");
        }

        QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
        List<SysConnUserRole> listSysConnUserRole = queryFactory.select(qSysConnUserRole)
                .from(qSysConnUserRole)
                .where(qSysConnUserRole.userId.eq(user.getId()))
                .fetch();
        List<String> roleIds = new ArrayList<>();
        for (SysConnUserRole sysConnUserRole : listSysConnUserRole) {
            roleIds.add(sysConnUserRole.getRoleId());
        }

        if ("admin".equals(user.getId()) || roleIds.contains(RoleConstant.SCHOOL_LEADER)) {
            //管理员或校级领导
            return sql;
        }
        if (roleIds.contains(RoleConstant.BRANCH_LEADER)) {
            //分院领导
            sql.append(" and users.dept_id = '").append(user.getDeptId()).append("'");
        } else {
            //创建人为当前登录人
            sql.append(" and users.username = '").append(user.getUsername()).append("'");
        }

        return sql;
    }


    @Override
    public void downloadTemplate(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "发布id不能为空.");
        }
        String pid = queryFactory.select(qCustomForm.id)
                .from(qCustomFormRelease)
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .where(qCustomFormRelease.id.eq(id))
                .fetchFirst();
        if (StringUtils.isEmpty(pid)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到该表单信息.");
        }

        List<String> fieldList = customFormFieldService.get(new CustomFormFieldModelSearch() {
            {
                setPid(pid);
            }
        }, Sort.by(Sort.Direction.ASC, "sortNum")).stream().map(CustomFormFieldModelExtend::getComment).collect(Collectors.toList());

        try {
            //生成excel
            Workbook wb = exportExcel(fieldList);
            FileUtils.downFile(wb);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> importFile(String id, MultipartFile file) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "发布id不能为空.");
        }
        //获取表名
        Tuple tuple = queryFactory.select(qCustomForm.id, qCustomFormRelease.isFlow)
                .from(qCustomFormRelease)
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .where(qCustomFormRelease.id.eq(id)).fetchFirst();
        if (tuple == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到自定义表单信息.");
        }
        CustomForm customForm = tuple.get(qCustomForm);
        //获取字段
        List<CustomFormFieldModelExtend> tableColumn = customFormFieldService.get(new CustomFormFieldModelSearch() {
            {
                setPid(customForm.getId());
            }
        }, Sort.by(Sort.Direction.ASC, "sortNum"));
        Map<String, String> fieldMap = tableColumn.stream().collect(Collectors.toMap(CustomFormFieldModelExtend::getComment, CustomFormFieldModelExtend::getField));

        //生成一条填报记录
        CustomFormFillModelParam fillModelParam = new CustomFormFillModelParam();
        fillModelParam.setUserId(LoginUtils.getUserInfo().getUsername());
        fillModelParam.setReleaseId(id);
        fillModelParam.setFillDateTime(System.currentTimeMillis());
        fillModelParam.setStatusNum(BooleanEnum.YES.getStatus());
        if (!StringUtils.isEmpty(tuple.get(qCustomFormRelease.isFlow))) {
            fillModelParam.setFlowStatus("已完成");
        }
        CustomFormFillModelExtend fillModelExtend = customFormFillService.post(fillModelParam);
        try {
            Workbook book = WorkbookFactory.create(file.getInputStream());

            JSONArray dataList = new JSONArray();
            // 循环sheet
            for (Sheet sheet : book) {
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 第一行
                Row textRow = sheet.getRow(0);
                // 从第二行开始保存数据
                for (int i = 1; i <= rowNum; i++) {
                    Row row = sheet.getRow(i);
                    JSONObject data = new JSONObject();

                    //设置每个单元格格式为string
                    int j = 0;
                    for (Cell cell : row) {
                        cell.setCellType(CellType.STRING);

                        data.put(fieldMap.get(textRow.getCell(j).getStringCellValue()), row.getCell(j));
                        j++;
                    }
                    dataList.add(data);
                }
            }

            //插入数据
            customFormFillService.createPhysicalData(customForm.getName(), tableColumn, dataList, fillModelExtend.getId(), 0);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return null;
    }

    @Override
    public Map<String, String> getItemDomIdMap() {
        Map<String, String> collect = queryFactory.selectFrom(qCustomFormRelease).fetch().stream().filter(item->!StringUtils.isEmpty(item.getItemDomId())).collect(Collectors.toMap(key -> key.getId(), value -> value.getItemDomId()));
        return collect;
    }

    private Workbook exportExcel(List<String> fieldList) {
        //创建工作薄
        Workbook wb = new XSSFWorkbook();
        //标题和页码
        CellStyle titleStyle = wb.createCellStyle();
        // 设置单元格对齐方式,水平居左
        titleStyle.setAlignment(HorizontalAlignment.LEFT);
        // 设置字体样式
        Font titleFont = wb.createFont();
        // 字体高度
        titleFont.setFontHeightInPoints((short) 12);
        // 字体样式
        titleFont.setFontName("黑体");
        titleStyle.setFont(titleFont);
        //创建sheet
        Sheet sheet = wb.createSheet("接入详情");
        // 自动设置宽度
        sheet.autoSizeColumn(0);
        //在sheet中添加填报说明
        Row fillInstruction = sheet.createRow(1);
        // 在sheet中添加标题行// 行数从0开始
        Row row = sheet.createRow((int) 0);
        for (int i = 0; i < fieldList.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(fieldList.get(i));
            cell.setCellStyle(titleStyle);
            Cell cell0 = fillInstruction.createCell(i);
            if (fieldList.get(i).contains("日期") || fieldList.get(i).contains("时间")) {
                cell0.setCellValue("2022-01-01");
            } else {
                cell0.setCellValue("xxxxxx");
            }

        }

        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        CellStyle dataStyle = wb.createCellStyle();
        // 设置居中样式，水平居中
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        return wb;
    }

    private Predicate getPredicateByRole(JPAQueryFactory queryFactory, Predicate pre) {
        //判断角色
        JwtUser user = LoginUtils.getUserInfo();
        if (user == null) {
            return pre;
        }
        if (!"admin".equals(user.getId())) {
            //条件集合(使用set去重)，使用or相关联
            Set<BooleanExpression> expressions = new HashSet<>();
            //填报域为空
            expressions.add(qCustomFormRelease.fillScope.isNull().or(qCustomFormRelease.fillScope.eq("")));

            QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
            List<String> roleIds = queryFactory.select(qSysConnUserRole)
                    .from(qSysConnUserRole)
                    .where(qSysConnUserRole.userId.eq(user.getId()))
                    .fetch().stream().map(SysConnUserRole::getRoleId).collect(Collectors.toList());
            if (roleIds.size() > 0) {
                expressions.add(qCustomFormRelease.fillScope.in(roleIds));
            }

            //先使用or拼接
            Predicate listOr = null;
            for (BooleanExpression expression : expressions) {
                listOr = ExpressionUtils.or(listOr, expression);
            }
            //然后使用and拼接到总条件
            pre = ExpressionUtils.and(pre, listOr);
        }

        return pre;
    }

    /**
     * 通过id查询自定义表单发布
     *
     * @param id
     * @return
     */
    @Override
    public CustomFormRelease findCustomFormReleaseById(String id) {
        CustomFormRelease entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormRelease found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormReleaseModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFormId())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.formId.like("%" + search.getFormId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsEnable())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.isEnable.eq(search.getIsEnable()));
        }
        if (!StringUtils.isEmpty(search.getFillScope())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.fillScope.like("%" + search.getFillScope() + "%"));
        }
        if (!StringUtils.isEmpty(search.getStartDateTime())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.startDateTime.eq(search.getStartDateTime()));
        }
        if (!StringUtils.isEmpty(search.getEndDateTime())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.endDateTime.eq(search.getEndDateTime()));
        }
        if (!StringUtils.isEmpty(search.getFlowCustomDef())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.flowCustomDef.like("%" + search.getFlowCustomDef() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormRelease.isDelete.eq(0), qCustomFormRelease.isDelete.isNull()));

        return pre;
    }
}
