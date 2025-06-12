package com.jfbrother.customForm.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.dao.CustomFormChildRepository;
import com.jfbrother.customForm.dao.CustomFormFieldRepository;
import com.jfbrother.customForm.jpa.CustomFormChild;
import com.jfbrother.customForm.jpa.QCustomFormChild;
import com.jfbrother.customForm.jpa.QCustomFormField;
import com.jfbrother.customForm.model.extend.CustomFormChildModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.param.CustomFormChildModelParam;
import com.jfbrother.customForm.model.search.CustomFormChildModelSearch;
import com.jfbrother.customForm.service.CustomFormChildService;
import com.jfbrother.customForm.service.CustomFormFieldService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
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

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * 自定义表单子表service实现
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Service
@Slf4j
public class CustomFormChildServiceImpl extends BaseServiceImpl implements CustomFormChildService {
    @Autowired
    private CustomFormChildRepository repository;
    @Autowired
    private CustomFormFieldRepository fieldRepository;
    @Autowired
    private CustomFormFieldService fieldService;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    protected JdbcTemplate _jdbcTemplate;

    QCustomFormChild qCustomFormChild = QCustomFormChild.customFormChild;
    QCustomFormField qCustomFormField = QCustomFormField.customFormField;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormChildModelExtend post(CustomFormChildModelParam model) {
        CustomFormChild entity = CopyUtils.copyBean(model, CustomFormChild.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormChildModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormChildModelExtend put(String id, CustomFormChildModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormChild entity = findCustomFormChildById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormChildModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomFormChildModelExtend patch(String id, CustomFormChildModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormChild entity = findCustomFormChildById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormChildModelExtend.class);
    }

    @Override
    public CustomFormChildModelExtend get(String id) {
        CustomFormChild entity = findCustomFormChildById(id);
        return CopyUtils.copyBean(entity, CustomFormChildModelExtend.class);
    }

    @Override
    public PageOb<CustomFormChildModelExtend> get(CustomFormChildModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<CustomFormChild> pageCustomFormChild = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageCustomFormChild, CustomFormChildModelExtend.class);
    }

    @Override
    public List<CustomFormChildModelExtend> get(CustomFormChildModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomFormChild> pageCustomFormChild = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomFormChild.getContent(), CustomFormChildModelExtend.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        List<CustomFormChild> list = queryFactory.selectFrom(qCustomFormChild).where(qCustomFormChild.id.in(ids)).fetch();

        List<String> fields = queryFactory.select(qCustomFormField.id).from(qCustomFormField).where(qCustomFormField.pid.in(ids)).fetch();

        repository.deleteBatch(ids);

        //删除节点下的字段
        if (fields.size() > 0) {
            fieldRepository.deleteBatch(fields);
        }

        //删除物理表
        List<String> tableNameList = list.stream().filter(item -> !StringUtils.isEmpty(item.getTableName())).map(CustomFormChild::getTableName).collect(Collectors.toList());
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("DROP TABLE IF EXISTS ").append(String.join(",", tableNameList))
                .append(";");

        log.info("删除表sql:{}", deleteSql);
        _jdbcTemplate.execute(deleteSql.toString());
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
    public List<CustomFormChildModelExtend> getChildFormsByFormId(String formId) {
        if (StringUtils.isEmpty(formId)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "formId is required!");
        }

        List<CustomFormChild> list = queryFactory.selectFrom(qCustomFormChild).where(qCustomFormChild.formId.eq(formId)).fetch();
        if (list.size() == 0) {
            return null;
        }

        List<String> ids = list.stream().map(CustomFormChild::getId).collect(Collectors.toList());
        //表单字段
        Map<String, List<CustomFormFieldModelExtend>> fieldMap = queryFactory.selectFrom(qCustomFormField).where(qCustomFormField.pid.in(ids))
                .fetch().stream().map(item -> CopyUtils.copyBean(item, CustomFormFieldModelExtend.class))
                .collect(Collectors.groupingBy(CustomFormFieldModelExtend::getPid));

        return list.stream().map(item -> {
            CustomFormChildModelExtend dto = CopyUtils.copyBean(item, CustomFormChildModelExtend.class);
            dto.setTableColumn(fieldMap.get(dto.getId()));

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(String formId, List<CustomFormChildModelParam> list) {
        if (StringUtils.isEmpty(formId)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "formId is required!");
        }
        list.stream().forEach(item -> save(formId, item));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String formId, CustomFormChildModelParam item) {
        if (StringUtils.isEmpty(formId)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "formId is required!");
        }
        if (StringUtils.isEmpty(item.getTableName())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单名不能为空.");
        }

        item.setFormId(formId);
        CustomFormChildModelExtend model = post(item);

        //创建物理表
        fieldService.createPhysicalTable(item.getTableName(), item.getTableComment(), item.getTableColumn());
        if (!StringUtils.isEmpty(item.getTableColumn()) && item.getTableColumn().size() > 0) {
            //保存表字段
            fieldService.saveAll(model.getId(), item.getTableColumn());
        }
    }

    /**
     * 通过id查询自定义表单子表
     *
     * @param id
     * @return
     */
    private CustomFormChild findCustomFormChildById(String id) {
        CustomFormChild entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormChild found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormChildModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFormId())) {
            pre = ExpressionUtils.and(pre, qCustomFormChild.formId.like("%" + search.getFormId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTableName())) {
            pre = ExpressionUtils.and(pre, qCustomFormChild.tableName.like("%" + search.getTableName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTableComment())) {
            pre = ExpressionUtils.and(pre, qCustomFormChild.tableComment.like("%" + search.getTableComment() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormChild.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormChild.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormChild.isDelete.eq(0), qCustomFormChild.isDelete.isNull()));

        return pre;
    }
}
