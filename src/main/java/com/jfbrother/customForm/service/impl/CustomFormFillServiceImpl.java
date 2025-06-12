package com.jfbrother.customForm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.dao.SysUsersRepository;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.enums.CharacterFieldTypeEnum;
import com.jfbrother.baseserver.enums.PortalToDoStatusEnum;
import com.jfbrother.baseserver.enums.WorkFlowTaskStatusEnum;
import com.jfbrother.baseserver.jpa.*;
import com.jfbrother.baseserver.model.FileModel;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.SysFileService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.service.impl.RedisServiceImpl;
import com.jfbrother.baseserver.utils.*;
import com.jfbrother.customForm.dao.CustomFormFillRepository;
import com.jfbrother.customForm.jpa.*;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormReleaseModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.request.CustomFormFillRequestModel;
import com.jfbrother.customForm.model.request.CustomFormFillTodoRequestModel;
import com.jfbrother.customForm.model.response.CustomFormFillAuditResponseModel;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import com.jfbrother.customForm.model.search.CustomFormFillModelSearch;
import com.jfbrother.customForm.service.CustomFormFieldService;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.customForm.service.CustomFormReleaseService;
import com.jfbrother.customForm.service.CustomFormService;
import com.jfbrother.work.dao.WorkFlowTaskRepository;
import com.jfbrother.work.jpa.MessageSendingData;
import com.jfbrother.work.jpa.QWorkFlowTask;
import com.jfbrother.work.jpa.WorkFlowTask;
import com.jfbrother.work.model.PortalToDoModel;
import com.jfbrother.work.model.PortalToDoSynModel;
import com.jfbrother.work.model.WorkFlowTaskModel;
import com.jfbrother.work.model.extend.MessageSendingDataModelExtend;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.request.*;
import com.jfbrother.work.model.response.WorkFlowResponseModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import com.jfbrother.work.service.PortalTodoService;
import com.jfbrother.work.service.WorkFlowService;
import com.jfbrother.work.service.WorkFlowTaskService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.xxl.job.core.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 自定义表单填报service实现
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
@Service
@Slf4j
public class CustomFormFillServiceImpl extends BaseServiceImpl implements CustomFormFillService {
    @Autowired
    private CustomFormFillRepository repository;
    @Autowired
    private WorkFlowTaskRepository workFlowTaskRepository;
    @Autowired
    private SysUsersRepository sysUsersRepository;

    @Autowired
    private CustomFormReleaseService customFormReleaseService;
    @Autowired
    private CustomFormService customFormService;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private WorkFlowTaskService workFlowTaskService;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private CustomFormFieldService customFormFieldService;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    protected JdbcTemplate _jdbcTemplate;
    @Autowired
    private PortalTodoService portalTodoService;
    @Autowired
    private RedisService redisService;

    QCustomFormFill qCustomFormFill = QCustomFormFill.customFormFill;
    QCustomForm qCustomForm = QCustomForm.customForm;
    QCustomFormChild qCustomFormChild = QCustomFormChild.customFormChild;
    QWorkFlowTask qWorkFlowTask = QWorkFlowTask.workFlowTask;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QSysDepartment qSysDepartment = QSysDepartment.sysDepartment;
    QCustomFormRelease qCustomFormRelease = QCustomFormRelease.customFormRelease;
    QCustomFormRule qCustomFormRule = QCustomFormRule.customFormRule;
    QCustomFormReleaseTask qCustomFormReleaseTask = QCustomFormReleaseTask.customFormReleaseTask;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFillModelExtend post(CustomFormFillModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        CustomFormFill entity = CopyUtils.copyBean(model, CustomFormFill.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormFillModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFillModelExtend put(String id, CustomFormFillModelParam model, Integer saveType) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormFill entity = findCustomFormFillById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        if (saveType == 0) {
            //保存
            entity.setStatusNum(0);
        } else if (saveType == 1) {
            //提交
            entity.setStatusNum(1);
        }
        entity = repository.save(entity);

        CustomFormRelease customFormRelease = queryFactory.selectFrom(qCustomFormRelease).where(qCustomFormRelease.id.eq(entity.getReleaseId())).fetchFirst();
        //表单数据
        updateFormData(id, customFormRelease.getFormId(), model.getData());

        if (saveType == 1 && customFormRelease.getIsFlow() == BooleanEnum.YES.getStatus()) {
            startWorkFlow(entity, customFormRelease.getFlowCustomDef(), model.getAssigneeData(), entity.getUserId());

        }

        return CopyUtils.copyBean(entity, CustomFormFillModelExtend.class);
    }

    /**
     * 更新表单数据
     *
     * @param id     填报id
     * @param formId 表单id
     * @param data   表单数据
     */
    private void updateFormData(String id, String formId, JSONObject data) {
        //表单信息
        CustomFormModelExtend formModel = customFormService.get(formId);
        //更新主表
        updatePhysicalData(formModel.getName(), formModel.getTableColumn(), data, 0);
        //更新子表
        if (!StringUtils.isEmpty(formModel.getTableChild())) {
            formModel.getTableChild().stream().forEach(item -> {
                JSONArray insertArray = new JSONArray();

                String tableName = item.getTableName();
                List<String> ids = this.querySqlDataByBusinessId(tableName, id, "id").stream().map(paramMap -> String.valueOf(paramMap.get("id"))).collect(Collectors.toList());
                List<String> containIds = new ArrayList<>();
                Object obj = data.get(tableName);
                if (!StringUtils.isEmpty(obj)) {
                    JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(obj));
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject childData = (JSONObject) jsonArray.get(i);
                        String dataId = childData.getString("id");
                        if (StringUtils.isEmpty(dataId)) {
                            //新增
                            insertArray.add(childData);
                        } else {
                            //更新
                            updatePhysicalData(tableName, item.getTableColumn(), childData, i);
                        }
                        containIds.add(dataId);
                    }
                }

                //新增数据
                if (insertArray.size() > 0) {
                    createPhysicalData(tableName, item.getTableColumn(), insertArray, id, containIds.size() > 0 ? containIds.size() - 1 : 0);
                }
                //删除数据
                List<String> deleteIds = ids.stream().filter(oldId -> !containIds.contains(oldId)).collect(Collectors.toList());
                if (deleteIds.size() > 0) {
                    deletePhysicalData(tableName, deleteIds, item.getTableColumn());
                }
            });
        }
    }

    /**
     * 更新物理数据
     *
     * @param tableName   表名
     * @param tableColumn 表单字段
     * @param data        值
     * @param sortNum     排序号
     */
    private void updatePhysicalData(String tableName, List<CustomFormFieldModelExtend> tableColumn, JSONObject data, int sortNum) {
        //物理主键
        String id = data.getString("id");
        //版本号
        Long versionNum = _jdbcTemplate.queryForObject("select version_num from " + tableName + " where id = ?", new Object[]{id}, Long.class);

        Map<String, String> fieldMap = tableColumn.stream().collect(Collectors.toMap(CustomFormFieldModelExtend::getField, CustomFormFieldModelExtend::getFieldType));
        //处理附件字段
        List<String> fileFieldList = tableColumn.stream().filter(item -> !StringUtils.isEmpty(item.getIzFileUpload()) && item.getIzFileUpload() == BooleanEnum.YES.getStatus()).map(CustomFormFieldModelExtend::getField).collect(Collectors.toList());
        List<Map<String, Object>> fileFileListMap = querySqlDataById(tableName, new ArrayList<String>() {
            {
                add(id);
            }
        }, StringUtils.join(fileFieldList, ","));


        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ").append(tableName).append(" SET ");
        for (Map.Entry entry : data.entrySet()) {
            Object key = entry.getKey();
            if ("id".equals(key) || fileFieldList.contains(key)) {
                continue;
            }

            String fieldType = fieldMap.get(key);
            if (!StringUtils.isEmpty(fieldType)) {
                sql.append(key).append(" = ");
                CharacterFieldTypeEnum fieldTypeEnum = CharacterFieldTypeEnum.getByName(fieldType);
                if (fieldTypeEnum != null) {
                    sql.append("'").append(entry.getValue()).append("'");
                } else {
                    sql.append(entry.getValue());
                }
                sql.append(",");
            }
        }
        sql.append("sort_num = ").append(sortNum).append(",")
                .append("version_num = ").append(++versionNum).append(",")
                .append("update_time = ").append(System.currentTimeMillis()).append(",")
                .append("update_user = '").append(LoginUtils.getUserInfo().getId()).append("'")
                .append(" WHERE id = '").append(id).append("';");

        try {
            log.info("更新数据sql:{}", sql);
            _jdbcTemplate.execute(sql.toString());
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }

        //保存附件
        if (!StringUtils.isEmpty(fileFileListMap) && fileFileListMap.size() > 0) {
            for (String key : fileFileListMap.get(0).keySet()) {
                String dataItem = JSON.toJSONString(data.get(key));
                try {
                    List<FileModel> tempFiles = JSON.parseArray(dataItem, FileModel.class);
                    if (tempFiles != null && tempFiles.size() > 0) {
                        tempFiles.forEach(file -> file.setBussinessName(StringUtils.underlineToCamelCase(tableName)));
                        sysFileService.saveFileModel(String.valueOf(fileFileListMap.get(0).get(key)), tempFiles);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    private void deletePhysicalData(String tableName, List<String> ids, List<CustomFormFieldModelExtend> tableColumn) {
        if (ids.size() == 0) {
            return;
        }
        //带附件的字段map
        List<String> fileFieldList = tableColumn.stream().filter(item -> !StringUtils.isEmpty(item.getIzFileUpload()) && item.getIzFileUpload() == BooleanEnum.YES.getStatus()).map(CustomFormFieldModelExtend::getField).collect(Collectors.toList());
        if (fileFieldList.size() > 0) {
            List<String> bussinessIds = new ArrayList<>();
            List<Map<String, Object>> dataList = querySqlDataById(tableName, ids, StringUtils.join(fileFieldList, ","));
            for (Map<String, Object> data : dataList) {
                for (Map.Entry entry : data.entrySet()) {
                    bussinessIds.add(String.valueOf(entry.getValue()));
                }
            }
            //删除附件
            sysFileService.removeByBussinessId(bussinessIds);
        }

        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM ")
                .append(tableName)
                .append(" WHERE id IN (");
        ids.stream().forEach(id -> sql.append("'").append(id).append("',"));

        String deleteSql = sql.substring(0, sql.lastIndexOf(",")) + ");";
        try {
            log.info("删除数据sql:{}", deleteSql);
            _jdbcTemplate.execute(deleteSql);
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFillModelExtend patch(String id, CustomFormFillModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormFill entity = findCustomFormFillById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormFillModelExtend.class);
    }

    @Override
    public CustomFormFillModelExtend get(String id) {
        Tuple tuple = queryFactory.select(qCustomFormFill
                , qCustomFormRelease.releaseName
                , qCustomFormRelease.formId
                , qCustomFormRelease.isFlow
                , qSysUsers.name
                , qCustomForm.name
                , qCustomFormRule.text
        )
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .leftJoin(qCustomFormRule).on(qCustomForm.id.eq(qCustomFormRule.formId))
                .leftJoin(qSysUsers).on(qCustomFormFill.userId.eq(qSysUsers.username))
                .leftJoin(qSysDepartment).on(qSysUsers.deptId.eq(qSysDepartment.id))
                .where(qCustomFormFill.id.eq(id))
                .fetchFirst();
        if (tuple == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到该表单填报信息!");
        }
        CustomFormFillModelExtend model = CopyUtils.copyBean(tuple.get(qCustomFormFill), CustomFormFillModelExtend.class);
        model.setFillUserName(tuple.get(qSysUsers.name));
        model.setReleaseName(tuple.get(qCustomFormRelease.releaseName));
        model.setFormId(tuple.get(qCustomFormRelease.formId));
        model.setIsFlow(tuple.get(qCustomFormRelease.isFlow));
        model.setRuleText(tuple.get(qCustomFormRule.text));

        //填报数据
        JSONArray tableData = JSONArray.parseArray(JSON.toJSONString(getTableData(tuple.get(qCustomForm.name), id, null, model.getFormId())));
        JSONObject data = JSON.parseObject(tableData.get(0).toString());
        //子表数据
        queryFactory.select(qCustomFormChild.id, qCustomFormChild.tableName)
                .from(qCustomFormChild)
                .where(qCustomFormChild.formId.eq(model.getFormId()))
                .fetch().stream()
                .forEach(childTable -> data.put(childTable.get(qCustomFormChild.tableName), JSONArray.parseArray(JSON.toJSONString(getTableData(childTable.get(qCustomFormChild.tableName), id, null, childTable.get(qCustomFormChild.id))))));
        model.setData(data);
        //环节信息
        model.setTaskList(workFlowTaskService.getByFillId(id));
        //表单发布信息
        model.setRelease(customFormReleaseService.get(model.getReleaseId()));

        return model;
    }

    private List<Map<String, Object>> getTableData(String tableName, String businessId, String query, String formId) {
        //获取物理数据
        List<Map<String, Object>> list = this.querySqlDataByBusinessId(tableName, businessId, query);

        //获取附件
        List<CustomFormFieldModelExtend> fileFieldList = customFormFieldService.get(new CustomFormFieldModelSearch() {
            {
                setPid(formId);
                setIzFileUpload(BooleanEnum.YES.getStatus());
            }
        }, Sort.by(Sort.Direction.ASC, "sortNum"));
        if (fileFieldList.size() > 0) {
            for (Map<String, Object> map : list) {
                for (CustomFormFieldModelExtend field : fileFieldList) {
                    Object o = map.get(field.getField());
                    if (StringUtils.isEmpty(o)) {
                        continue;
                    }
                    //附件信息
                    List<FileModel> fileModel = sysFileService.getFileModel(o.toString());

                    map.put(field.getField(), fileModel);
                }
            }
        }

        return list;
    }

    /**
     * 根据业务id获取物理数据
     *
     * @param tableName
     * @param businessId
     * @param query
     * @return
     */
    private List<Map<String, Object>> querySqlDataByBusinessId(String tableName, String businessId, String query) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        if (StringUtils.isEmpty(query)) {
            query = "*";
        }
        sql.append(query).append(" FROM ")
                .append(tableName)
                .append(" WHERE business_id = '")
                .append(businessId)
                .append("' ORDER BY sort_num ASC;");

        List<Map<String, Object>> list;
        try {
            log.info("查询数据sql:{}，业务id【{}】", sql, businessId);
            list = _jdbcTemplate.queryForList(sql.toString());
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }

        return list;
    }

    @Override
    public PageOb<CustomFormFillModelExtend> get(CustomFormFillModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomFormFill
                , qCustomFormRelease.releaseName
                , qSysUsers.name
                , qSysDepartment.deptName
                , qCustomForm.jimuReportId
        )
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .leftJoin(qSysUsers).on(qCustomFormFill.userId.eq(qSysUsers.username))
                .leftJoin(qSysDepartment).on(qSysUsers.deptId.eq(qSysDepartment.id));

        Predicate pre = fillPredicate(search);
        //按角色权限查询
        if(!StringUtils.isEmpty(search.getIsSelfSearch())&&!search.getIsSelfSearch()){
            pre = getPredicateByRole(pre);
        }else{
            pre=ExpressionUtils.and(pre,qCustomFormFill.userId.eq(LoginUtils.getUserInfo().getUsername()));
        }


        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qCustomFormFill);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        List<String> ids = fetchResults.getResults().stream().map(item -> item.get(qCustomFormFill).getId()).collect(Collectors.toList());
        //获取当前流程环节
//        Map<String, List<WorkFlowTaskModelExtend>> currentTaskMap = queryFactory.select(qWorkFlowTask).from(qWorkFlowTask)
//                .where(qWorkFlowTask.isCurrTask.eq(BooleanEnum.YES.getStatus())).fetch()
//                .stream().map(item -> CopyUtils.copyBean(item, WorkFlowTaskModelExtend.class)).collect(Collectors.groupingBy(WorkFlowTaskModelExtend::getFillId));

        return CopyUtils.copyPageObByQueryResults(fetchResults, CustomFormFillModelExtend.class, tuple -> {
            CustomFormFillModelExtend model = CopyUtils.copyBean(tuple.get(qCustomFormFill), CustomFormFillModelExtend.class);
            model.setFillUserName(tuple.get(qSysUsers.name));
            model.setReleaseName(tuple.get(qCustomFormRelease.releaseName));
            model.setDeptName(tuple.get(qSysDepartment.deptName));
            model.setJimuReportId(tuple.get(qCustomForm.jimuReportId));

            //model.setCurrentTaskList(currentTaskMap.get(model.getId()));

            return model;
        });
    }

    private Predicate getPredicateByRole(Predicate pre) {
        String adminRoleId="402881f86a00d0fb016a00d1a2700000";
        String schoolRoleId="ff808081831beddc01831bf2ee130000";
        String deptRoleId="ff808081831af7fc01831b3190c1004f";
        String userId = LoginUtils.getUserInfo().getUsername();
        QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
        List<String> roleIds = queryFactory.select(qSysConnUserRole)
                .from(qSysConnUserRole)
                .where(qSysConnUserRole.userId.eq(LoginUtils.getUserInfo().getId()))
                .fetch().stream().map(SysConnUserRole::getRoleId).collect(Collectors.toList());
        if ("admin".equals(userId)||roleIds.contains(adminRoleId)||roleIds.contains(schoolRoleId)) {
        } else {
            //条件集合(使用set去重)，使用or相关联
            Set<BooleanExpression> expressions = new HashSet<>();
            if(roleIds.contains(deptRoleId)&&!StringUtils.isEmpty(LoginUtils.getUserInfo().getDeptId())){
                BooleanExpression deptExpression = qSysDepartment.id.eq(LoginUtils.getUserInfo().getDeptId());
                expressions.add(deptExpression);
            }
            expressions.add(qCustomFormFill.userId.eq(LoginUtils.getUserInfo().getUsername()));
            Predicate listOr=null;
            for (BooleanExpression expression : expressions) {
                listOr=ExpressionUtils.or(listOr,expression);
            }
            pre=ExpressionUtils.and(pre,listOr);
        }

        return pre;
    }

    @Override
    public List<CustomFormFillModelExtend> get(CustomFormFillModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);


        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomFormFill, qCustomFormRelease).from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormRelease.id.eq(qCustomFormFill.releaseId));

        List<CustomFormFillModelExtend> collect = jpaQuery.where(pre).fetch().stream().map(tuple -> {
            CustomFormFillModelExtend extend = CopyUtils.copyBean(tuple.get(qCustomFormFill), CustomFormFillModelExtend.class);
            if(tuple.get(qCustomFormRelease)!=null){
                extend.setRelease(CopyUtils.copyBean(tuple.get(qCustomFormRelease),CustomFormReleaseModelExtend.class));
            }
            return extend;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        //todo 考虑是否做逻辑删除，或前端提示用户该操作会删除数据
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        //基础表名map
        List<Tuple> list = queryFactory.select(qCustomFormFill.id, qCustomForm.name, qCustomForm.id)
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qCustomForm).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .where(qCustomFormFill.id.in(ids).and(qCustomForm.name.isNotNull())).fetch();
        Map<String, String> formNameMap = list.stream()
                .collect(Collectors.toMap(tuple -> tuple.get(qCustomFormFill.id), tuple -> tuple.get(qCustomForm.name)));
        Map<String, String> formIdMap = list.stream()
                .collect(Collectors.toMap(tuple -> tuple.get(qCustomFormFill.id), tuple -> tuple.get(qCustomForm.id)));
        //子表表名map
        Map<String, List<Tuple>> childFormMap = queryFactory.select(qCustomFormFill.id, qCustomFormChild.tableName, qCustomFormChild.id)
                .from(qCustomFormChild)
                .leftJoin(qCustomForm).on(qCustomFormChild.formId.eq(qCustomForm.id))
                .leftJoin(qCustomFormRelease).on(qCustomForm.id.eq(qCustomFormRelease.formId))
                .leftJoin(qCustomFormFill).on(qCustomFormRelease.id.eq(qCustomFormFill.releaseId))
                .where(qCustomFormFill.id.in(ids).and(qCustomFormChild.tableName.isNotNull())).fetch()
                .stream().collect(Collectors.groupingBy(tuple -> tuple.get(qCustomFormFill.id)));

        repository.deleteBatch(ids);

        //删除填报数据
        ids.stream().forEach(id -> {
            //删除主表填报
            deletePhysicalDataByBusinessId(formNameMap.get(id), id, formIdMap.get(id));
            //删除子表填报
            List<Tuple> childList = childFormMap.get(id);
            if (childList != null && childList.size() > 0) {
                childList.stream().forEach(tuple -> deletePhysicalDataByBusinessId(tuple.get(qCustomFormChild.tableName), id, tuple.get(qCustomFormChild.id)));
            }
        });
        //todo 删除流程和待办

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
    @Transactional
    public CustomFormFillModelExtend fillForm(String releaseId, Integer saveType, CustomFormFillRequestModel model) {
        //判断表单是否在填报时间内
        CustomFormRelease customFormRelease = customFormReleaseService.findCustomFormReleaseById(releaseId);
        long fillDateTime = System.currentTimeMillis();

        if (fillDateTime < customFormRelease.getStartDateTime() || fillDateTime > customFormRelease.getEndDateTime()) {
            throw new ServiceException(ResultCode.FORBIDDEN, "不在填报时间范围内.");
        }

        //填报人
        String userId = model.getUserId();
        if (StringUtils.isEmpty(userId)) {
            userId = LoginUtils.getUserInfo().getUsername();
        }

        //保存填报记录
        CustomFormFill entity = new CustomFormFill();
        entity.setUserId(userId);
        entity.setReleaseId(releaseId);
        entity.setFillDateTime(fillDateTime);
        if (saveType == 0) {
            //保存
            entity.setStatusNum(0);
        } else if (saveType == 1) {
            //提交
            entity.setStatusNum(1);
        }
        entity = repository.save(entity);
        String id = entity.getId();

        //获取表单发布信息
        CustomFormReleaseModelExtend releaseModel = customFormReleaseService.getDetail(CopyUtils.copyBean(customFormRelease, CustomFormReleaseModelExtend.class));

        JSONObject data = model.getData();
        CustomFormModelExtend formData = releaseModel.getFormData();
        if (!StringUtils.isEmpty(formData)) {
            //插入主表数据
            createPhysicalData(formData.getName(), formData.getTableColumn(), new JSONArray() {
                {
                    add(data);
                }
            }, id, 0);
            //插入子表数据
            if (formData.getTableChild() != null) {
                formData.getTableChild().stream()
                        .forEach(item -> createPhysicalData(item.getTableName(), item.getTableColumn(), JSONArray.parseArray(JSON.toJSONString(data.get(item.getTableName()))), id, 0));
            }
        }

        if (saveType == 1 && releaseModel.getIsFlow() == BooleanEnum.YES.getStatus()) {
            startWorkFlow(entity, releaseModel.getFlowCustomDef(), model.getAssigneeData(), userId);
        }

        return CopyUtils.copyBean(entity, CustomFormFillModelExtend.class);
    }

    @Override
    public CustomFormFillModelExtend getDetail(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "填报id不能为空.");
        }
        CustomFormFillModelExtend model = get(id);

        //表单数据
        CustomFormModelExtend formModel = customFormService.get(model.getFormId());
        model.setFormData(formModel);

        return model;
    }

    @Override
    public PageOb<CustomFormFillAuditResponseModel> getAudit(CustomFormFillModelSearch search, Pageable pageable) {
        String username = LoginUtils.getUserInfo().getUsername();

        JPAQuery<Tuple> jpaQuery = queryFactory.select(qCustomFormFill
                , qCustomFormRelease.releaseName
                , qSysUsers.name)
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .leftJoin(qSysUsers).on(qCustomFormFill.userId.eq(qSysUsers.username))
                .leftJoin(qWorkFlowTask).on(qCustomFormFill.id.eq(qWorkFlowTask.fillId));

        Predicate pre = fillPredicate(search);
        jpaQuery.where(pre)
                //当前登录人为办理人
                .where(qWorkFlowTask.assigneeAccount.eq(username))
                .groupBy(qCustomFormFill);

        setPageParams(jpaQuery, pageable, qCustomFormFill);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        List<String> ids = fetchResults.getResults().stream().map(item -> item.get(qCustomFormFill).getId()).collect(Collectors.toList());
        //获取当前登录人流程环节信息
        Map<String, List<WorkFlowTaskModelExtend>> taskMap = queryFactory.selectFrom(qWorkFlowTask)
                .where(qWorkFlowTask.fillId.in(ids).and(qWorkFlowTask.assigneeAccount.eq(username)))
                .orderBy(qWorkFlowTask.assignDate.asc(), qWorkFlowTask.orderId.asc()).fetch()
                .stream().map(item -> CopyUtils.copyBean(item, WorkFlowTaskModelExtend.class)).collect(Collectors.groupingBy(WorkFlowTaskModelExtend::getFillId));

        return CopyUtils.copyPageObByQueryResults(fetchResults, CustomFormFillAuditResponseModel.class, tuple -> {
            CustomFormFillAuditResponseModel model = CopyUtils.copyBean(tuple.get(qCustomFormFill), CustomFormFillAuditResponseModel.class);
            model.setFillUserName(tuple.get(qSysUsers.name));
            model.setReleaseName(tuple.get(qCustomFormRelease.releaseName));

            model.setTaskList(taskMap.get(model.getId()));

            List<WorkFlowTaskModelExtend> currTaskList = model.getTaskList().stream().filter(item -> item.getIsCurrTask() == BooleanEnum.YES.getStatus()).collect(Collectors.toList());
            model.setCurrTaskList(currTaskList);

            return model;
        });
    }

    @Override
    public CustomFormFillModelExtend getByFlowId(Integer flowId) {
        if (StringUtils.isEmpty(flowId)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程id不能为空.");
        }
        CustomFormFill customFormFill = repository.findByFlowId(flowId);
        if (customFormFill == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到该表单填报信息.");
        }

        return get(customFormFill.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void todo(Integer taskId, CustomFormFillTodoRequestModel model) {
        Integer flowId = model.getFlowId();
        if (StringUtils.isEmpty(flowId)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程id不能为空.");
        }
        //根据流程id获取填报信息
        Tuple tuple = queryFactory.select(qCustomFormFill, qCustomFormRelease)
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease).on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .where(qCustomFormFill.flowId.eq(flowId))
                .fetchFirst();
        if (tuple == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到流程填报信息.");
        }
        CustomFormFill customFormFill = tuple.get(qCustomFormFill);
        CustomFormRelease customFormRelease = tuple.get(qCustomFormRelease);
        //判断表单是否在填报时间内
        long now = System.currentTimeMillis();
        if (now < customFormRelease.getStartDateTime() || now > customFormRelease.getEndDateTime()) {
            throw new ServiceException(ResultCode.FORBIDDEN, "不在填报时间范围内.");
        }
        if (customFormFill.getStatusNum() == 0) {
            //保存改为提交
            customFormFill.setStatusNum(1);
        }

        //驱动环节
        workFlowService.taskForward(new WorkFlowTaskForwardRequestModel() {
            {
                setTask_id(taskId);
                setAssignee(LoginUtils.getUserInfo().getUsername());
                setAppr_code(model.getApprCode());
                setAppr_desc(model.getApprDesc());
                setAssignee_data(model.getAssigneeData());
            }
        });
        //更新表单填报数据
        updateFormData(customFormFill.getId(), customFormRelease.getFormId(), model.getData());
        //更新流程及环节信息 todo model.getApprCode() 根据是点了同意（1）还是不同意（0）
        updateWorkFlow(CopyUtils.copyBean(customFormFill, CustomFormFill.class), customFormRelease.getFlowCustomDef(), flowId);
    }

    @Override
    public PageOb<WorkFlowTodoResponseModel> getTodo(WorkFlowTodoRequestModel search, Pageable pageable) {
        //获取所有流程id
        List<Integer> flowIds = queryFactory.select(qCustomFormFill.flowId).from(qCustomFormFill).fetch();

        //当前登录人
        search.setAssignUser(LoginUtils.getUserInfo().getUsername());
        List<WorkFlowTodoResponseModel> list = workFlowService.getTodoAll(search).stream()
                .filter(item -> flowIds.contains(item.getRequestId())).collect(Collectors.toList());


        // 逻辑分页
        PageInfoConvent page = new PageInfoConvent(pageable.getPageNumber() + 1, pageable.getPageSize(), list);

        return new PageOb(page.getPageSize(), page.getPageNum(), page.getTotalCount(), page.getList());
    }

    @Override
    public CustomFormFillModelExtend getReleaseFill(String releaseId) {
        String fillId = queryFactory.select(qCustomFormFill.id)
                .from(qCustomFormFill)
                .where(qCustomFormFill.releaseId.eq(releaseId)
                        .and(qCustomFormFill.userId.eq(LoginUtils.getUserInfo().getUsername()))
                        //保存
                        .and(qCustomFormFill.statusNum.eq(0))
                ).orderBy(qCustomFormFill.fillDateTime.desc())
                .fetchFirst();

        if (StringUtils.isEmpty(fillId)) {
            return null;
        }
        return get(fillId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void flowCancel(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "自定义表单填报id不能为空.");
        }
        //根据流程id获取填报信息
        Tuple tuple = queryFactory.select(qCustomFormFill, qCustomFormRelease.startDateTime, qCustomFormRelease.endDateTime)
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease)
                .on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .where(qCustomFormFill.id.eq(id))
                .fetchFirst();
        CustomFormFill customFormFill = tuple.get(qCustomFormFill);
        if (customFormFill == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到流程填报信息.");
        }
        Integer flowId = customFormFill.getFlowId();
        if (StringUtils.isEmpty(flowId)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程id不能为空!");
        }
        //判断表单是否在填报时间内
        long now = System.currentTimeMillis();
        if (now < tuple.get(qCustomFormRelease.startDateTime) || now > tuple.get(qCustomFormRelease.endDateTime)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "不在填报时间范围内.");
        }

        //取消流程
        workFlowService.cancel(new WorkFlowCancelRequestModel() {
            {
                setWf_id(flowId);
                setOp_user(LoginUtils.getUserInfo().getUsername());
            }
        });

        //更新填报表单的状态
        customFormFill.setFlowStatus("已取消");
        customFormFill.setEndDate(System.currentTimeMillis());

        //更新流程环节信息
        updateWorkFlowTask(customFormFill.getId(), flowId, new ArrayList<>());
    }

    @Override
    public JSONObject getFormFieldData(String id, String tableName) {
        //整理积木报表需要的数据格式
        List<Map<String, Object>> tableData = this.querySqlDataByBusinessId(tableName, id, null);

        JSONObject jsonData = new JSONObject();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(tableData));
        jsonData.put("data", jsonArray);

        return jsonData;
    }

    /**
     * 启动流程
     *
     * @param entity
     * @param flowCustomDef
     * @param assigneeData
     * @param username
     */
    private void startWorkFlow(CustomFormFill entity, String flowCustomDef, List<WorkFlowCreateRequestModel.WorkFlowCreateFormAssigneeDataModel> assigneeData, String username) {
        //启动流程
        JSONObject createData = workFlowService.create(new WorkFlowCreateRequestModel() {
            {
                setWf_def(flowCustomDef);
                setInit_user(username);
                setForward(true);
                setAssignee_data(assigneeData);
            }
        });
        //更新流程及环节信息
        final int wf_id = Integer.parseInt(createData.getString("wf_id"));
        //todo 往校务服务插入待办数据
        /**
         * 步骤说明：1.在校务办事大厅中创建事务实例
         * 2.将下一环节的办理人作为待办插入到校务办事大厅的待办中
         * 3.根据审核状态，更新校务办事大厅的待办的状态
         * 4.更新事务实例的状态
         */
        updateWorkFlow(entity, flowCustomDef, wf_id);


    }


    private void synToPortal(CustomFormFillModelExtend entity,int wf_id,String status) {
        Map<String, String> itemDomIdMap = customFormReleaseService.getItemDomIdMap();
        String itemDomId = itemDomIdMap.get(entity.getReleaseId());
        if(StringUtils.isEmpty(itemDomId)){
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到门户事项档案号");
        }
        PortalToDoSynModel synModel=new PortalToDoSynModel();
        synModel.setName(entity.getReleaseName());
        synModel.setItemDomId(itemDomId);
        synModel.setCode(entity.getId());
        synModel.setAssignerSubject(entity.getUserId());
        synModel.setStatus(status);
        synModel.setActualStartDateTime(entity.getCreateTime());
        if(PortalToDoStatusEnum.FINISHED.getStatus().equals(status)){
            synModel.setActualEndDateTime(System.currentTimeMillis());
        }
        portalTodoService.synToPoral(synModel);

    }
    /**
     * 步骤说明：
     * 2.将下一环节的办理人作为待办插入到校务办事大厅的待办中
     * 3.根据审核状态，更新校务办事大厅的待办的状态
     * 4.更新事务实例的状态
     */
    private void updateStatusToPortal(CustomFormFill entity,int wf_id,WorkFlowResponseModel flowResponseModel){
        //更新事务实例的状态
        Map<String, String> itemDomIdMap = customFormReleaseService.getItemDomIdMap();
        String itemDomId = itemDomIdMap.get(entity.getReleaseId());
        if(StringUtils.isEmpty(itemDomId)){
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到门户事项档案号");
        }
        CustomFormFillModelExtend extend = get(entity.getId());
        synToPortal(extend,wf_id,PortalToDoStatusEnum.TO_BE_RECEIVED.getStatus());
        PortalToDoModel toDoModel = convertToPortalModel(extend);
        List<WorkFlowTaskResponseModel> taskList = getTaskList(wf_id, WorkFlowTaskStatusEnum.TO_DO.getName());
        //设置环节待办人
        String[] assigneeList = getassigneeList(taskList);

        toDoModel.setItemDomId(itemDomId);
        if(assigneeList.length>0){
            String toDoId = String.valueOf(wf_id).concat("_")
                    .concat(String.valueOf(taskList.get(0).getId()))
                    .concat("_").concat(taskList.get(0).getName());
            toDoModel.setTodoId(toDoId);
            toDoModel.setAssigneeSubject(assigneeList);
            toDoModel.setStatus(PortalToDoStatusEnum.DOING.getStatus());
            //将下一环节的办理人作为待办插入到校务办事大厅的待办中
            portalTodoService.pushToDoPoral(toDoModel);
        }
        //todo 设置上一环节办理状态为finish
        List<WorkFlowTaskResponseModel> finishTaskList = getTaskList(wf_id, WorkFlowTaskStatusEnum.COMPLETED.getName());
        String[] assigneeFinshList = getassigneeList(finishTaskList);
        if(assigneeFinshList.length>0){
            String toDoId = String.valueOf(wf_id).concat("_")
                    .concat(String.valueOf(finishTaskList.get(0).getId()))
                    .concat("_").concat(finishTaskList.get(0).getName());
            toDoModel.setTodoId(toDoId);
            toDoModel.setAssigneeSubject(assigneeFinshList);
            toDoModel.setStatus(PortalToDoStatusEnum.FINISHED.getStatus());
            //如果状态为finish,则必须设置结束时间
            toDoModel.setEndDateTimeMills(System.currentTimeMillis());
            //根据审核状态，更新校务办事大厅的待办的状态
            portalTodoService.pushToDoPoral(toDoModel);
        }
        String instanceStatus=PortalToDoStatusEnum.DOING.getStatus();
        if(flowResponseModel.getStatus().equals("已完成")){
            instanceStatus=PortalToDoStatusEnum.FINISHED.getStatus();
        }else if(flowResponseModel.getStatus().equals("已取消")){
            instanceStatus=PortalToDoStatusEnum.CANCELLED.getStatus();
        }
        //更新事务实例的状态
        synToPortal(extend,wf_id,instanceStatus);

    }

    private PortalToDoModel convertToPortalModel(CustomFormFillModelExtend entity){
        PortalToDoModel toDoModel=new PortalToDoModel();
        toDoModel.setInstanceCode(entity.getId());
        toDoModel.setAssignerSubject(entity.getUserId());
        toDoModel.setStartDateTimeMills(entity.getCreateTime());
        toDoModel.setTitle(entity.getReleaseName());
        return toDoModel;
    }

    private List<WorkFlowTaskResponseModel> getTaskList(int wf_id,String status){
        if(StringUtils.isEmpty(status)){
            throw new ServiceException(ResultCode.FORBIDDEN, "流程状态为必须值");
        }
        List<WorkFlowTaskResponseModel> list = workFlowService.getTask(new WorkFlowTaskRequestModel() {
            {
                setWf_id(wf_id);
            }
        });
        List<WorkFlowTaskResponseModel> taskList = list.stream().filter(item->item.getStatus().equals(status))
                .map(item->{
                    //处理数据，当审批意见为拒绝时，流程回到开始点，此时待办的人员账号没有工号
                    WorkFlowTaskResponseModel model = CopyUtils.copyBean(item, WorkFlowTaskResponseModel.class);
                    CustomFormFillModelExtend fillModel = getByFlowId(wf_id);
                    if(!StringUtils.isEmpty(item.getAssigeeName())&&item.getName().equals("开始")){
                        model.setAssingeeAccount(fillModel.getUserId());
                    }
                    return model;
                })
                .collect(Collectors.toList()).stream().filter(ob->!StringUtils.isEmpty(ob.getAssingeeAccount())).collect(Collectors.toList());

        return taskList;
    }
    private String[] getassigneeList(List<WorkFlowTaskResponseModel> taskList){
        String[] assigneeCountList=new String[taskList.size()];
        for (int i=0;i<taskList.size();i++){
            if(!StringUtils.isEmpty(taskList.get(i).getAssingeeAccount())&&taskList.get(i).getAssingeeAccount()!="null"){
                assigneeCountList[i]=taskList.get(i).getAssingeeAccount();
            }
        }
        return assigneeCountList;
    }
    /**
     * 更新流程及环节信息
     *
     * @param entity
     * @param flowCustomDef 流程自定义标识
     * @param flowId        流程id
     */
    private void updateWorkFlow(CustomFormFill entity, String flowCustomDef, int flowId) {
        //更新填报信息
        Pageable pageable = PageRequest.of(0, max, Sort.by(Sort.Direction.ASC, "createTime"));
        PageOb<WorkFlowResponseModel> pageModel = workFlowService.get(new WorkFlowRequestModel() {
            {
                setWf_def(flowCustomDef);
                setInit_user(entity.getUserId());
            }
        }, pageable);
        Optional<WorkFlowResponseModel> first = pageModel.getResult().stream().filter(item -> item.getWfId() == flowId).findFirst();
        if (!first.isPresent()) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到流程实例信息.");
        }
        WorkFlowResponseModel workFlow = first.get();
        entity.setFlowStatus(workFlow.getStatus());
        entity.setFlowId(workFlow.getWfId());
        if (!StringUtils.isEmpty(workFlow.getStartDate())) {
            entity.setStartDate(DateUtils.StringToDate(workFlow.getStartDate()).getTime());
        }
        if (!StringUtils.isEmpty(workFlow.getEndDate())) {
            entity.setEndDate(DateUtils.StringToDate(workFlow.getEndDate()).getTime());
        }
        repository.save(entity);

        //更新流程环节信息
        updateWorkFlowTask(entity.getId(), flowId, workFlow.getCurrTask());
        //更新校务办事大厅的流程状态
        updateStatusToPortal(entity,flowId,workFlow);
        //todo 消息提醒
        if(entity.getFlowStatus().equals("进行中")){
            //获取待办人
            List<WorkFlowTaskResponseModel> taskList = getTaskList(flowId, WorkFlowTaskStatusEnum.TO_DO.getName());
            //设置环节待办人
            String[] assigneeList = getassigneeList(taskList);
            if(assigneeList.length>0){
                MessageSendingDataModelExtend messageSendingDataModelExtend = new MessageSendingDataModelExtend();
                messageSendingDataModelExtend.setMatchingKey("todo");
                messageSendingDataModelExtend.setReciverGhList(assigneeList);
                messageSendingDataModelExtend.setSendUser(entity.getUserId());
                messageSendingDataModelExtend.setBelongReleaseId(entity.getReleaseId());
                messageSendingDataModelExtend.setBelongFillId(entity.getId());
                redisService.lPush(RedisKeyConstant.SMS_MESSAGE_TASK,JSONObject.toJSONString(messageSendingDataModelExtend));
            }
        }

    }
    private void updateWorkFlowTask(String fillId, Integer flowId, List<WorkFlowResponseModel.WorkFlowCurrentTaskModel> currTask) {
        //删除所有环节
        queryFactory.delete(qWorkFlowTask).where(qWorkFlowTask.fillId.eq(fillId)).execute();
        //当前环节的环节id列表
        List<Integer> cTaskIds = currTask.stream().map(WorkFlowResponseModel.WorkFlowCurrentTaskModel::getId).collect(Collectors.toList());

        List<WorkFlowTask> list = createWorkFlowTask(fillId, flowId).stream().map(item -> {
            WorkFlowTask entity = CopyUtils.copyBean(item, WorkFlowTask.class);
            entity.setIsCurrTask(cTaskIds.contains(item.getTaskId()) ? BooleanEnum.YES.getStatus() : BooleanEnum.NO.getStatus());
            return entity;
        }).collect(Collectors.toList());

        workFlowTaskRepository.saveAll(list);
    }

    private List<WorkFlowTaskModelExtend> createWorkFlowTask(String fillId, Integer flowId) {
        return workFlowService.getTask(new WorkFlowTaskRequestModel() {
            {
                setWf_id(flowId);
            }
        }).stream().map(item -> {
            WorkFlowTaskModelParam taskModel = CopyUtils.copyBean(item, WorkFlowTaskModelParam.class, "id", "assignDate", "endDate");
            taskModel.setFillId(fillId);
            taskModel.setTaskId(item.getId());
            if (!StringUtils.isEmpty(item.getAssignDate())) {
                taskModel.setAssignDate(DateUtils.StringToDate(item.getAssignDate()).getTime());
            }
            if (!StringUtils.isEmpty(item.getEndDate())) {
                taskModel.setEndDate(DateUtils.StringToDate(item.getEndDate()).getTime());
            }
            taskModel.setIsCurrTask(BooleanEnum.NO.getStatus());
            taskModel.setAssigneeAccount(item.getAssingeeAccount());
            return workFlowTaskService.post(taskModel);
        }).collect(Collectors.toList());
    }

    /**
     * 创建物理数据
     *
     * @param tableName   表名
     * @param tableColumn 表字段
     * @param dataList    数据
     * @param businessId  业务id
     * @param sortNum     排序号
     */
    @Override
    public void createPhysicalData(String tableName, List<CustomFormFieldModelExtend> tableColumn, JSONArray dataList, String businessId, int sortNum) {
        if (dataList == null) {
            return;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO ")
                .append(tableName).append(" ")
                .append(createColumnSql(tableColumn))
                .append(" VALUES ");

        for (int i = 0; i < dataList.size(); i++) {
            sql.append(createValueSql(tableColumn, (JSONObject) dataList.get(i), businessId, sortNum + i, tableName)).append(",");
        }

        String insertSql = sql.substring(0, sql.lastIndexOf(",")) + ";";
        try {
            log.info("插入数据sql:{}", insertSql);
            _jdbcTemplate.execute(insertSql);
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void statusNum(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        List<CustomFormFill> list = ids.stream().map(id -> {
            CustomFormFill entity = findCustomFormFillById(id);

            //设置状态
            if (entity.getStatusNum().equals(1)) {
                entity.setStatusNum(0);
                //如果存在流程，则取消流程，并删除流程数据
                if(!StringUtils.isEmpty(entity.getFlowId())){
                    flowCancel(entity.getId());
                    workFlowTaskService.deleteByFillId(entity.getId());
                }
            } else if (entity.getStatusNum().equals(0)) {
                entity.setStatusNum(1);
            }
            return entity;
        }).collect(Collectors.toList());

        repository.saveAll(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void flowRefresh(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "自定义表单填报id不能为空.");
        }
        Tuple tuple = queryFactory.select(qCustomFormFill, qCustomFormRelease.flowCustomDef)
                .from(qCustomFormFill)
                .leftJoin(qCustomFormRelease)
                .on(qCustomFormFill.releaseId.eq(qCustomFormRelease.id))
                .where(qCustomFormFill.id.eq(id)).fetchFirst();
        CustomFormFill entity = tuple.get(qCustomFormFill);
        if (entity == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到表单填报信息!");
        }
        Integer flowId = entity.getFlowId();
        if (StringUtils.isEmpty(flowId)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程id不能为空!");
        }
        //更新流程
        updateWorkFlow(entity, tuple.get(qCustomFormRelease.flowCustomDef), flowId);
    }

    @Override
    public CustomFormFillModelExtend put(String id, CustomFormFillModelParam param) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "id不能为空.");
        }
        CustomFormFill entity = findCustomFormFillById(id);
        CustomFormFill bean = CopyUtils.copyBean(param, entity);
        repository.save(bean);
        CustomFormRelease customFormRelease = queryFactory.selectFrom(qCustomFormRelease).where(qCustomFormRelease.id.eq(entity.getReleaseId())).fetchFirst();
        //表单数据
        updateFormData(id, customFormRelease.getFormId(), param.getData());
        return CopyUtils.copyBean(bean,CustomFormFillModelExtend.class);
    }

    @Override
    public CustomFormFillModelExtend getOneById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "id不能为空.");
        }
        CustomFormFill entity = findCustomFormFillById(id);
        return CopyUtils.copyBean(entity,CustomFormFillModelExtend.class);
    }

    @Override
    @Transactional
    public void batchDownloadFiles(CustomFormFillModelSearch search) {
        if(StringUtils.isEmpty(search.getReleaseId())){
            throw new ServiceException(ResultCode.FORBIDDEN, "releaseId不能为空.");
        }
        String tableName = queryFactory.select(qCustomForm.name).from(qCustomForm)
                .leftJoin(qCustomFormRelease).on(qCustomFormRelease.formId.eq(qCustomForm.id))
                .where(qCustomFormRelease.id.eq(search.getReleaseId()))
                .groupBy(qCustomForm.name).fetchFirst();
        //查询是否存在附件上传的字段
        QCustomFormField qCustomFormField = QCustomFormField.customFormField;
        JPAQuery<String> jpaQuery = queryFactory.select(qCustomFormField.field).from(qCustomFormField)
                .leftJoin(qCustomForm).on(qCustomForm.id.eq(qCustomFormField.pid))
                .where(qCustomFormField.izFileUpload.eq(1).and(qCustomForm.name.eq(tableName)));

        if(!StringUtils.isEmpty(search.getFileUploadField())){
            jpaQuery.where(qCustomFormField.field.eq(search.getFileUploadField()));
        }
        List<String> uploadFields = jpaQuery.fetch();
        if(uploadFields.size()==0||uploadFields.size()>1){
            throw new ServiceException(ResultCode.FORBIDDEN, "附件不存在或存在多个附件字段需指定一个.");
        }
        String alias = StringUtils.underlineToCamelCase(tableName);
        StringBuffer sql=new StringBuffer();
        String columns="";
        if(search.getFieldList().size()>0){
             columns = search.getFieldList().stream().map(field->alias.concat(".").concat(field)).collect(Collectors.joining(",'_',"));
            sql.append("select c.*,CONCAT(").append(columns).append(") as keywords from ");
        }else {
            sql.append("select c.*").append(" from ");
        }
        sql.append(tableName).append(" ").append(alias)
        .append(" left join custom_form_fill b on b.id=").append(alias).append(".business_id")
        .append(" left join gs_teacher_base_info tea on tea.gh=b.user_id")
        .append(" left join sys_file c on c.bussiness_id=").append(alias).append(".").append(uploadFields.get(0))
        .append(" where c.bussiness_id is not null ");
        if(!StringUtils.isEmpty(search.getFillDateTimes())&&search.getFillDateTimes().size()>0){
            sql.append(" and b.fill_date_time>=").append(search.getFillDateTimes().get(0))
                .append(" and b.fill_date_time<=").append(search.getFillDateTimes().get(1));
        }
        if(!StringUtils.isEmpty(search.getFillUserName())){
            sql.append(" and tea.xm='").append(search.getFillUserName()).append("'");
        }
        log.info("下载的执行sql:{}",sql.toString());

        List<FileModel> list = _jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(FileModel.class));
        FileUtils.exportZip(list);
    }

    private StringBuffer createColumnSql(List<CustomFormFieldModelExtend> tableColumn) {
        StringBuffer sql = new StringBuffer();
        sql.append("(id,business_id,");
        tableColumn.stream().forEach(item -> sql.append(item.getField()).append(","));
        sql.append("sort_num,version_num,create_time,create_user)");

        return sql;
    }

    private StringBuffer createValueSql(List<CustomFormFieldModelExtend> tableColumn, JSONObject data, String businessId, int sortNum, String tableName) {
        Map<String, String> fileFieldMap = new HashMap<>();

        StringBuffer sql = new StringBuffer();
        sql.append("('").append(UUID.randomUUID().toString().replaceAll("-", "")).append("',")
                .append("'").append(businessId).append("',");
        //添加参数
        for (CustomFormFieldModelExtend item : tableColumn) {
            //过滤附件字段
            if (!StringUtils.isEmpty(item.getIzFileUpload()) && item.getIzFileUpload() == BooleanEnum.YES.getStatus()) {
                String fileFieldId = UUID.randomUUID().toString().replaceAll("-", "");
                fileFieldMap.put(item.getField(), fileFieldId);
                sql.append("'").append(fileFieldId).append("',");
                continue;
            }
            Object value = data.get(item.getField());
            if (StringUtils.isEmpty(value)) {
                sql.append("null,");
            } else {
                if (CharacterFieldTypeEnum.getByName(item.getFieldType()) != null) {
                    //字符类型
                    sql.append("'").append(value.toString()).append("'");
                } else {
                    //数字或时间类型
                    sql.append(value);
                }
                sql.append(",");
            }
        }
        sql.append(sortNum).append(",")
                .append("0,")
                .append(System.currentTimeMillis()).append(",'")
                .append(LoginUtils.getUserInfo().getId()).append("')");

        //保存附件
        for (String key : fileFieldMap.keySet()) {
            String dataItem = JSON.toJSONString(data.get(key));
            try {
                List<FileModel> tempFiles = JSON.parseArray(dataItem, FileModel.class);
                if (tempFiles != null && tempFiles.size() > 0) {
                    tempFiles.forEach(file -> file.setBussinessName(StringUtils.underlineToCamelCase(tableName)));
                    sysFileService.saveFileModel(fileFieldMap.get(key), tempFiles);
                }
            } catch (Exception e) {
                continue;
            }
        }

        return sql;
    }

    private List<Map<String, Object>> querySqlDataById(String tableName, List<String> ids, String query) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        if (StringUtils.isEmpty(query)) {
            query = "*";
        }
        sql.append(query).append(" FROM ")
                .append(tableName)
                .append(" WHERE id IN (");
        ids.stream().forEach(id -> sql.append("'").append(id).append("',"));

        String querySql = sql.substring(0, sql.lastIndexOf(",")) + ");";

        List<Map<String, Object>> list;
        try {
            log.info("查询数据sql:{}】", querySql);
            list = _jdbcTemplate.queryForList(querySql);
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }

        return list;
    }

    /**
     * 根据业务id删除物理数据
     *
     * @param tableName
     * @param businessId
     * @param formId
     */
    private void deletePhysicalDataByBusinessId(String tableName, String businessId, String formId) {
        if (StringUtils.isEmpty(tableName)) {
            return;
        }
        //带附件的字段map
        List<String> fileFieldList = customFormFieldService.get(new CustomFormFieldModelSearch() {
            {
                setPid(formId);
                setIzFileUpload(BooleanEnum.YES.getStatus());
            }
        }, Sort.by(Sort.Direction.ASC, "sortNum")).stream().map(CustomFormFieldModelExtend::getField).collect(Collectors.toList());
        if (fileFieldList.size() > 0) {
            List<String> bussinessIds = new ArrayList<>();
            List<Map<String, Object>> dataList = querySqlDataByBusinessId(tableName, businessId, StringUtils.join(fileFieldList, ","));
            for (Map<String, Object> data : dataList) {
                for (Map.Entry entry : data.entrySet()) {
                    bussinessIds.add(String.valueOf(entry.getValue()));
                }
            }
            //删除附件
            sysFileService.removeByBussinessId(bussinessIds);
        }

        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM ")
                .append(tableName).append(" ")
                .append("WHERE business_id = '")
                .append(businessId)
                .append("';");

        try {
            log.info("删除数据sql:{}", sql);
            _jdbcTemplate.execute(sql.toString());
        } catch (NoSuchElementException e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }
    }


    /**
     * 通过id查询自定义表单填报
     *
     * @param id
     * @return
     */
    private CustomFormFill findCustomFormFillById(String id) {
        CustomFormFill entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormFill found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormFillModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getUserId())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.userId.like("%" + search.getUserId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getReleaseId())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.releaseId.eq(search.getReleaseId()));
        }
        if (!StringUtils.isEmpty(search.getFlowStatus())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.flowStatus.like("%" + search.getFlowStatus() + "%"));
        }
        if (!StringUtils.isEmpty(search.getFlowId())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.flowId.eq(search.getFlowId()));
        }
        if (!StringUtils.isEmpty(search.getStartDate())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.startDate.eq(search.getStartDate()));
        }
        if (!StringUtils.isEmpty(search.getEndDate())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.endDate.eq(search.getEndDate()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.statusNum.eq(search.getStatusNum()));
        }
        if (!StringUtils.isEmpty(search.getIzFlow()) && search.getIzFlow()) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.flowId.isNotNull());
        }
        List<Long> fillDateTimes = search.getFillDateTimes();
        if (!StringUtils.isEmpty(fillDateTimes) && fillDateTimes.size() > 0) {
            pre = ExpressionUtils.and(pre, qCustomFormFill.fillDateTime.goe(fillDateTimes.get(0)).and(qCustomFormFill.fillDateTime.loe(fillDateTimes.get(1))));
        }
        if (!StringUtils.isEmpty(search.getDeptId())) {
            pre = ExpressionUtils.and(pre, qSysDepartment.id.eq(search.getDeptId()));
        }
        if (!StringUtils.isEmpty(search.getFillUserName())) {
            pre = ExpressionUtils.and(pre, qSysUsers.name.like("%" + search.getFillUserName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getReleaseName())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.releaseName.like("%" + search.getReleaseName() + "%"));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormFill.isDelete.eq(0), qCustomFormFill.isDelete.isNull()));

        return pre;
    }


}
