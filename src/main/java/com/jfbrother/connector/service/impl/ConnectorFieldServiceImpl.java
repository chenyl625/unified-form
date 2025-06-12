package com.jfbrother.connector.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.dao.ConnectorFieldRepository;
import com.jfbrother.connector.jpa.ConnectorField;
import com.jfbrother.connector.jpa.QConnectorField;
import com.jfbrother.connector.model.extend.ConnectorFieldModelExtend;
import com.jfbrother.connector.model.param.ConnectorFieldModelParam;
import com.jfbrother.connector.model.search.ConnectorFieldModelSearch;
import com.jfbrother.connector.service.ConnectorFieldService;
import com.jfbrother.standard.jpa.QStandardDataSource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * 接口字段service实现
 *
 * @author chenyl@jfbrother.com 2022-07-20
 */
@Service
public class ConnectorFieldServiceImpl extends BaseServiceImpl implements ConnectorFieldService {
    @Autowired
    private ConnectorFieldRepository repository;

    QConnectorField qConnectorField = QConnectorField.connectorField;
    QStandardDataSource qStandardDataSource = QStandardDataSource.standardDataSource;
    QSysUsers qSysUsers = QSysUsers.sysUsers;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConnectorFieldModelExtend post(ConnectorFieldModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        ConnectorField entity = CopyUtils.copyBean(model, ConnectorField.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorFieldModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConnectorFieldModelExtend put(String id, ConnectorFieldModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        ConnectorField entity = findConnectorFieldById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorFieldModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ConnectorFieldModelExtend patch(String id, ConnectorFieldModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        ConnectorField entity = findConnectorFieldById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorFieldModelExtend.class);
    }

    @Override
    public ConnectorFieldModelExtend get(String id) {
        ConnectorField entity = findConnectorFieldById(id);
        return CopyUtils.copyBean(entity, ConnectorFieldModelExtend.class);
    }

    @Override
    public PageOb<ConnectorFieldModelExtend> get(ConnectorFieldModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<ConnectorField> pageConnectorField = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageConnectorField, ConnectorFieldModelExtend.class);
    }

    @Override
    public List<ConnectorFieldModelExtend> get(ConnectorFieldModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qConnectorField
                , qStandardDataSource.name
                , qStandardDataSource.checkUser
                , qSysUsers.name
        )
                .from(qConnectorField)
                .leftJoin(qStandardDataSource).on(qConnectorField.fieldSource.eq(qStandardDataSource.id))
                .leftJoin(qSysUsers).on(qStandardDataSource.checkUser.eq(qSysUsers.username));

        Predicate pre = fillPredicate(search);

        return jpaQuery.where(pre)
                .orderBy(getOrderSpecifiers(sort, qConnectorField))
                .fetch().stream()
                .map(tuple -> {
                    ConnectorFieldModelExtend model = CopyUtils.copyBean(tuple.get(qConnectorField), ConnectorFieldModelExtend.class);
                    model.setFieldSourceName(tuple.get(qStandardDataSource.name));
                    model.setCheckUser(tuple.get(qStandardDataSource.checkUser));
                    model.setCheckUserName(tuple.get(qSysUsers.name));

                    return model;
                }).collect(Collectors.toList());
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
    public void saveAll(String connId, List<ConnectorFieldModelParam> list) {
        List<String> fieldIds = list.stream().map(ConnectorFieldModelParam::getId).collect(Collectors.toList());

        //删除
        List<String> delIds = queryFactory.select(qConnectorField.id)
                .from(qConnectorField)
                .where(qConnectorField.connId.eq(connId))
                .fetch().stream()
                .filter(id -> !fieldIds.contains(id)).collect(Collectors.toList());
        if (delIds.size() > 0) {
            repository.deleteBatch(delIds);
        }
        //保存
        list.stream().forEach(item -> {
            if (StringUtils.isEmpty(item.getId())) {
                item.setConnId(connId);
                post(item);
            } else {
                put(item.getId(), item);
            }
        });
    }

    @Override
    public Map<String, Map<String, Object>> getByConnId(String connId) {
        List<ConnectorField> list = queryFactory.selectFrom(qConnectorField)
                .where(qConnectorField.connId.eq(connId)
                        //字段不隐藏
                        .and(qConnectorField.isHide.eq(BooleanEnum.NO.getStatus()))
                )
                .orderBy(qConnectorField.sortNum.asc().nullsLast())
                .fetch();

        Map<String, Map<String, Object>> data = new LinkedHashMap<>();
        for (ConnectorField item : list) {
            data.put(item.getFieldName(), new JSONObject() {
                {
                    put("description", item.getDescription());
                    put("type", item.getType());
                }
            });
        }
        return data;
    }

    /**
     * 通过id查询接口字段
     *
     * @param id
     * @return
     */
    private ConnectorField findConnectorFieldById(String id) {
        ConnectorField entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No ConnectorField found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(ConnectorFieldModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getConnId())) {
            pre = ExpressionUtils.and(pre, qConnectorField.connId.like("%" + search.getConnId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getFieldName())) {
            pre = ExpressionUtils.and(pre, qConnectorField.fieldName.like("%" + search.getFieldName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getFieldSource())) {
            pre = ExpressionUtils.and(pre, qConnectorField.fieldSource.like("%" + search.getFieldSource() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsHide())) {
            pre = ExpressionUtils.and(pre, qConnectorField.isHide.eq(search.getIsHide()));
        }
        if (!StringUtils.isEmpty(search.getDictId())) {
            pre = ExpressionUtils.and(pre, qConnectorField.dictId.eq(search.getDictId()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qConnectorField.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qConnectorField.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qConnectorField.isDelete.eq(0), qConnectorField.isDelete.isNull()));

        return pre;
    }
}
