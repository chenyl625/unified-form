package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.DataCheckStatusEnum;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.jpa.ConnectorField;
import com.jfbrother.connector.jpa.QConnectorField;
import com.jfbrother.connector.model.extend.ConnectorFieldModelExtend;
import com.jfbrother.dataCenter.dao.DataErrorCorrectionRepository;
import com.jfbrother.dataCenter.jpa.DataErrorCorrection;
import com.jfbrother.dataCenter.jpa.QDataErrorCorrection;
import com.jfbrother.dataCenter.model.extend.DataErrorCorrectionModelExtend;
import com.jfbrother.dataCenter.model.param.DataErrorCorrectionModelParam;
import com.jfbrother.dataCenter.model.search.DataErrorCorrectionModelSearch;
import com.jfbrother.dataCenter.service.DataErrorCorrectionService;
import com.jfbrother.standard.jpa.QStandardDataSource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * 数据纠错补录表service实现
 *
 * @author chenyl@jfbrother.com 2022-08-05
 */
@Service
public class DataErrorCorrectionServiceImpl extends BaseServiceImpl implements DataErrorCorrectionService {
    @Autowired
    private DataErrorCorrectionRepository repository;

    QDataErrorCorrection qDataErrorCorrection = QDataErrorCorrection.dataErrorCorrection;
    QConnectorField qConnectorField = QConnectorField.connectorField;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QStandardDataSource qStandardDataSource = QStandardDataSource.standardDataSource;

    @Override
    @Transactional
    public DataErrorCorrectionModelExtend post(DataErrorCorrectionModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (StringUtils.isEmpty(model.getConnFieldId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "字段id不能为空.");
        }

        DataErrorCorrection entity = CopyUtils.copyBean(model, DataErrorCorrection.class);

        //提交
        if (entity.getCheckStatus() == DataCheckStatusEnum.AUDIT.getStatus()) {
            entity.setApplyDateTime(System.currentTimeMillis());
            //审核人
            entity.setCheckUser(getCheckUser(model.getConnFieldId()));
        }

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataErrorCorrectionModelExtend.class);
    }

    private String getCheckUser(String connFieldId) {
        return queryFactory.select(qStandardDataSource.checkUser)
                .from(qConnectorField)
                .leftJoin(qStandardDataSource).on(qConnectorField.fieldSource.eq(qStandardDataSource.id))
                .where(qConnectorField.id.eq(connFieldId))
                .fetchFirst();
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataErrorCorrectionModelExtend put(String id, DataErrorCorrectionModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataErrorCorrection entity = findDataErrorCorrectionById(id);
        //通过或拒绝
        if (entity.getCheckStatus() == DataCheckStatusEnum.PASS.getStatus()
                || entity.getCheckStatus() == DataCheckStatusEnum.REFUSE.getStatus()) {
            throw new ServiceException(ResultCode.FORBIDDEN, "该流程已结束");
        }
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);

        //提交
        if (entity.getCheckStatus() == DataCheckStatusEnum.AUDIT.getStatus()) {
            entity.setApplyDateTime(System.currentTimeMillis());
            //审核人
            entity.setCheckUser(getCheckUser(model.getConnFieldId()));
        } else if (entity.getCheckStatus() == DataCheckStatusEnum.PASS.getStatus()
                || entity.getCheckStatus() == DataCheckStatusEnum.REFUSE.getStatus()) {
            //审核时间
            entity.setCheckDateTime(System.currentTimeMillis());
        }

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataErrorCorrectionModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataErrorCorrectionModelExtend patch(String id, DataErrorCorrectionModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataErrorCorrection entity = findDataErrorCorrectionById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataErrorCorrectionModelExtend.class);
    }

    @Override
    public DataErrorCorrectionModelExtend get(String id) {
        JPAQuery<Tuple> jpaQuery = getTupleJPAQuery();

        Tuple tuple = jpaQuery.where(qDataErrorCorrection.id.eq(id))
                .fetchFirst();
        if (tuple == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到纠错数据!");
        }

        return getDataErrorCorrectionModelExtend(tuple);
    }

    @Override
    public PageOb<DataErrorCorrectionModelExtend> get(DataErrorCorrectionModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        JPAQuery<Tuple> jpaQuery = getTupleJPAQuery();

        setPageParams(jpaQuery.where(pre), pageable, qDataErrorCorrection);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, DataErrorCorrectionModelExtend.class, tuple -> getDataErrorCorrectionModelExtend(tuple));
    }

    private JPAQuery<Tuple> getTupleJPAQuery() {
        return queryFactory.select(qDataErrorCorrection
                , qConnectorField
                , qStandardDataSource.name
                , qSysUsers.name
        )
                .from(qDataErrorCorrection)
                .leftJoin(qConnectorField).on(qDataErrorCorrection.connFieldId.eq(qConnectorField.id))
                .leftJoin(qStandardDataSource).on(qConnectorField.fieldSource.eq(qStandardDataSource.id))
                .leftJoin(qSysUsers).on(qDataErrorCorrection.checkUser.eq(qSysUsers.username));
    }

    @NotNull
    private DataErrorCorrectionModelExtend getDataErrorCorrectionModelExtend(Tuple tuple) {
        DataErrorCorrectionModelExtend model = CopyUtils.copyBean(tuple.get(qDataErrorCorrection), DataErrorCorrectionModelExtend.class);
        //处理响应模型
        ConnectorField connectorField = tuple.get(qConnectorField);
        if (connectorField != null) {
            model.setConnField(CopyUtils.copyBean(connectorField, ConnectorFieldModelExtend.class));
        }
        model.setFieldSourceName(tuple.get(qStandardDataSource.name));
        model.setCheckUserName(tuple.get(qSysUsers.name));
        //补录或纠错
        model.setCorrType(StringUtils.isEmpty(model.getOldValue()) ? "COLLECTION" : "CORRECTION");

        return model;
    }

    @Override
    public List<DataErrorCorrectionModelExtend> get(DataErrorCorrectionModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<DataErrorCorrection> pageDataErrorCorrection = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageDataErrorCorrection.getContent(), DataErrorCorrectionModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        repository.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }

        repository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询数据纠错补录表
     *
     * @param id
     * @return
     */
    private DataErrorCorrection findDataErrorCorrectionById(String id) {
        DataErrorCorrection entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No DataErrorCorrection found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(DataErrorCorrectionModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getConnFieldId())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.connFieldId.like("%" + search.getConnFieldId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getOldValue())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.oldValue.like("%" + search.getOldValue() + "%"));
        }
        if (!StringUtils.isEmpty(search.getNewValue())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.newValue.like("%" + search.getNewValue() + "%"));
        }
        if (!StringUtils.isEmpty(search.getRowJson())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.rowJson.like("%" + search.getRowJson() + "%"));
        }
        if (!StringUtils.isEmpty(search.getCheckUser())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.checkUser.eq(search.getCheckUser()));
        }
        if (!StringUtils.isEmpty(search.getCheckDateTime())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.checkDateTime.eq(search.getCheckDateTime()));
        }
        if (!StringUtils.isEmpty(search.getCheckStatus())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.checkStatus.eq(search.getCheckStatus()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.statusNum.eq(search.getStatusNum()));
        }
        if (!StringUtils.isEmpty(search.getCreateUser())) {
            pre = ExpressionUtils.and(pre, qDataErrorCorrection.createUser.eq(search.getCreateUser()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qDataErrorCorrection.isDelete.eq(0), qDataErrorCorrection.isDelete.isNull()));

        return pre;
    }
}
