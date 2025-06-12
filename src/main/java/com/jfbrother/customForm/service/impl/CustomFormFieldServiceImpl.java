package com.jfbrother.customForm.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.CharacterFieldTypeEnum;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.baseserver.utils.TableFmtUtils;
import com.jfbrother.customForm.dao.CustomFormFieldRepository;
import com.jfbrother.customForm.jpa.CustomFormField;
import com.jfbrother.customForm.jpa.QCustomForm;
import com.jfbrother.customForm.jpa.QCustomFormField;
import com.jfbrother.customForm.jpa.QCustomFormRelease;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFieldModelParam;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import com.jfbrother.customForm.service.CustomFormFieldService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * 自定义表单字段service实现
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Service
@Slf4j
public class CustomFormFieldServiceImpl extends BaseServiceImpl implements CustomFormFieldService {
    @Autowired
    private CustomFormFieldRepository repository;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    protected JdbcTemplate _jdbcTemplate;

    QCustomFormField qCustomFormField = QCustomFormField.customFormField;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFieldModelExtend post(CustomFormFieldModelParam model) {
        CustomFormField entity = CopyUtils.copyBean(model, CustomFormField.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormFieldModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFieldModelExtend put(String id, CustomFormFieldModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormField entity = findCustomFormFieldById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormFieldModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormFieldModelExtend patch(String id, CustomFormFieldModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormField entity = findCustomFormFieldById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormFieldModelExtend.class);
    }

    @Override
    public CustomFormFieldModelExtend get(String id) {
        CustomFormField entity = findCustomFormFieldById(id);
        return CopyUtils.copyBean(entity, CustomFormFieldModelExtend.class);
    }

    @Override
    public PageOb<CustomFormFieldModelExtend> get(CustomFormFieldModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<CustomFormField> pageCustomFormField = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageCustomFormField, CustomFormFieldModelExtend.class);
    }

    @Override
    public List<CustomFormFieldModelExtend> get(CustomFormFieldModelSearch search, Sort sort) {
        QCustomForm qCustomForm = QCustomForm.customForm;
        QCustomFormRelease qCustomFormRelease = QCustomFormRelease.customFormRelease;
        Predicate pre = fillPredicate(search);
        JPAQuery<CustomFormField> jpaQuery = queryFactory.select(qCustomFormField).from(qCustomFormField)
                .leftJoin(qCustomForm).on(qCustomForm.id.eq(qCustomFormField.pid))
                .leftJoin(qCustomFormRelease).on(qCustomFormRelease.formId.eq(qCustomForm.id));

        if (!StringUtils.isEmpty(search.getReleaseId())) {
            pre = ExpressionUtils.and(pre, qCustomFormRelease.id.eq(search.getReleaseId()));
        }
        List<CustomFormFieldModelExtend> fetch = jpaQuery.where(pre).fetch()
                .stream()
                .map(obj->CopyUtils.copyBean(obj,CustomFormFieldModelExtend.class))
                .collect(Collectors.toList());

        return fetch;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
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
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(String pid, List<CustomFormFieldModel> list) {
        if (StringUtils.isEmpty(pid)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "pid is required!");
        }
        if (list.size() == 0) {
            return;
        }

        List<String> ids = queryFactory.select(qCustomFormField.id).from(qCustomFormField).where(qCustomFormField.pid.eq(pid)).fetch();

        AtomicInteger i = new AtomicInteger();
        list.stream().forEach(item -> {
            item.setPid(pid);
            item.setSortNum(i.getAndIncrement());

            CustomFormFieldModelParam model = CopyUtils.copyBean(item, CustomFormFieldModelParam.class);
            if (ids.contains(item.getId())) {
                put(item.getId(), model);
            } else {
                post(model);
            }
        });
    }

    @Override
    public void createPhysicalTable(String tableName, String tableComment, List<CustomFormFieldModel> tableColumn) {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (")
                .append("`id` varchar(32) NOT NULL COMMENT '主键',")
                .append("`business_id` varchar(32) DEFAULT NULL COMMENT '业务id',");
        for (CustomFormFieldModel column : tableColumn) {
            if (StringUtils.isEmpty(column.getField())) {
                continue;
            }
            sql.append(column.getField()).append(" ")
                    .append(createUseSql(column)).append(",");
        }
        sql.append("`sort_num` decimal(5,0) DEFAULT 0 COMMENT '排序号',")
                .append("`status_num` decimal(2,0) DEFAULT NULL COMMENT '状态码',")
                .append("`version_num` decimal(13,0) DEFAULT NULL COMMENT '数据版本，乐观锁',")
                .append("`is_delete` decimal(1,0) DEFAULT NULL COMMENT '逻辑删除标识符',")
                .append("`create_time` decimal(13,0) DEFAULT NULL COMMENT '创建时间',")
                .append("`create_user` varchar(32) DEFAULT NULL COMMENT '创建人',")
                .append("`update_time` decimal(13,0) DEFAULT NULL COMMENT '更新时间',")
                .append("`update_user` varchar(32) DEFAULT NULL COMMENT '更新人',")
                //默认主键
                .append(" PRIMARY KEY (`id`) USING BTREE")
                .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        //表注释
        if (StringUtils.isEmpty(tableComment)) {
            sql.append(";");
        } else {
            sql.append(" COMMENT='").append(tableComment).append("';");
        }

        try {
            log.info("新建表【{}】，sql:{}", tableName, sql);
            //建表
            _jdbcTemplate.execute(sql.toString());
        } catch (Exception e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public void updatePhysicalTable(String tableName, String tableComment, List<CustomFormFieldModel> tableColumn, List<CustomFormFieldModel> oldTableColumn) {
        StringBuffer insertSql = new StringBuffer();
        StringBuffer updateSql = new StringBuffer();
        StringBuffer deleteSql = new StringBuffer();
        List<String> existIds = new ArrayList<>();

        if (tableColumn == null) {
            tableColumn = new ArrayList<>();
        }
        if (oldTableColumn == null) {
            oldTableColumn = new ArrayList<>();
        }
        Map<String, String> tableColumnMap = oldTableColumn.stream().collect(Collectors.toMap(CustomFormFieldModel::getId, CustomFormFieldModel::getField));

        List<CustomFormFieldModelExtend> afterList = new ArrayList<>();
        for (int i = 0; i < tableColumn.size(); i++) {
            CustomFormFieldModel column = tableColumn.get(i);
            String columnName = column.getField();

            String fieldName = tableColumnMap.get(column.getId());
            if (StringUtils.isEmpty(fieldName)) {
                //新增sql
                insertSql.append("ADD COLUMN ").append(columnName).append(" ")
                        .append(createUseSql(column)).append(" AFTER ");
                if (i == 0) {
                    insertSql.append("business_id,");
                } else {
                    insertSql.append(tableColumn.get(i - 1).getField()).append(",");
                }
            } else {
                existIds.add(column.getId());
                //编辑sql
                updateSql.append("CHANGE COLUMN ")
                        .append(fieldName).append(" ")
                        .append(columnName).append(" ")
                        .append(createUseSql(column)).append(" AFTER ");
                if (afterList.size() == 0) {
                    updateSql.append("business_id,");
                } else {
                    updateSql.append(afterList.get(afterList.size() - 1).getField()).append(",");
                }

                afterList.add(CopyUtils.copyBean(column, CustomFormFieldModelExtend.class));
            }
        }

        //删除sql
        oldTableColumn.stream().filter(item -> !existIds.contains(item.getId()))
                .forEach(item -> deleteSql.append("DROP COLUMN ").append(item.getField()).append(","));

        //
        StringBuffer sql = new StringBuffer();
        sql.append("ALTER TABLE ").append(tableName).append(" ");
        if (deleteSql.length() > 0) {
            sql.append(deleteSql);
        }
        if (updateSql.length() > 0) {
            sql.append(updateSql);
        }
        if (insertSql.length() > 0) {
            sql.append(insertSql);
        }
        sql.append("COMMENT = '").append(tableComment).append("';");

        try {
            log.info("更新表【{}】，sql:{}", tableName, sql);
            //更新表
            _jdbcTemplate.execute(sql.toString());
        } catch (Exception e) {
            throw new ServiceException(ResultCode.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getField(String pid) {
        List<String> list = queryFactory.select(qCustomFormField.field)
                .from(qCustomFormField)
                .where(qCustomFormField.pid.eq(pid))
                .fetch();
        list.addAll(Arrays.asList(Constant.CUSTOM_FORM_FIELDS));
        //
        AtomicInteger i = new AtomicInteger();
        List<Map<String, Object>> fieldList = list.stream().map(str -> {
            Map<String, Object> item = new HashMap<>();
            item.put("fieldName", str);
            item.put("fieldText", str);
            item.put("isShow", true);
            item.put("orderNum", i.getAndIncrement());
            item.put("widgetType", "String");
            item.put("extJson", "");

            return item;
        }).collect(Collectors.toList());

        return fieldList;
    }

    private String createUseSql(CustomFormFieldModel column) {
        StringBuffer sql = new StringBuffer();

        //字段类型为空，默认为varchar
        if (StringUtils.isEmpty(column.getFieldType())) {
            column.setFieldType("varchar");
        } else {
            column.setFieldType(column.getFieldType().toLowerCase());
        }
        sql.append(column.getFieldType()).append(" ");

        //字段长度
        if (StringUtils.isEmpty(column.getMaxlength())) {
            //设置默认长度为255
            column.setMaxlength(255L);
        }
        if (TableFmtUtils.canSetLength(column.getFieldType())) {
            sql.append("(").append(column.getMaxlength());
            if (!StringUtils.isEmpty(column.getFieldPrecision())
                    && TableFmtUtils.canSetPoint(column.getFieldType())) {
                //精度
                sql.append(",").append(column.getFieldPrecision());
            }
            sql.append(")").append(" ");
        }
        //默认值
        if ("NULL".equals(column.getDefaultValue())) {
            column.setDefaultValue(null);
        }
        if (StringUtils.isEmpty(column.getDefaultValue())) {
            sql.append("DEFAULT NULL ");
        } else {
            sql.append("DEFAULT ");
            CharacterFieldTypeEnum fieldTypeEnum = CharacterFieldTypeEnum.getByName(column.getFieldType());
            if (fieldTypeEnum == null) {
                sql.append(column.getDefaultValue());
            } else {
                sql.append("'").append(column.getDefaultValue()).append("'");
            }
            sql.append(" ");
        }
        //注释
        if (!StringUtils.isEmpty(column.getComment())) {
            sql.append("COMMENT '").append(column.getComment()).append("'");
        }

        return sql.toString();
    }

    /**
     * 通过id查询自定义表单字段
     *
     * @param id
     * @return
     */
    private CustomFormField findCustomFormFieldById(String id) {
        CustomFormField entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormField found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormFieldModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getPid())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.pid.like("%" + search.getPid() + "%"));
        }
        if (!StringUtils.isEmpty(search.getComment())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.comment.like("%" + search.getComment() + "%"));
        }
        if (!StringUtils.isEmpty(search.getDefaultValue())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.defaultValue.like("%" + search.getDefaultValue() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMaxlength())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.maxlength.eq(search.getMaxlength()));
        }
        if (!StringUtils.isEmpty(search.getFieldType())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.fieldType.like("%" + search.getFieldType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIzFileUpload())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.izFileUpload.eq(search.getIzFileUpload()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormField.statusNum.eq(search.getStatusNum()));
        }

        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormField.isDelete.eq(0), qCustomFormField.isDelete.isNull()));

        return pre;
    }
}
