package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.dataCenter.dao.DataDictionaryChildRepository;
import com.jfbrother.dataCenter.jpa.DataDictionaryChild;
import com.jfbrother.dataCenter.jpa.QDataDictionary;
import com.jfbrother.dataCenter.jpa.QDataDictionaryChild;
import com.jfbrother.dataCenter.model.extend.DataDictionaryChildModelExtend;
import com.jfbrother.dataCenter.model.param.DataDictionaryChildModelParam;
import com.jfbrother.dataCenter.model.search.DataDictionaryChildModelSearch;
import com.jfbrother.dataCenter.service.DataDictionaryChildService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
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
 * 数据字典子表service实现
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@Service
public class DataDictionaryChildServiceImpl extends BaseServiceImpl implements DataDictionaryChildService {
    @Autowired
    private DataDictionaryChildRepository repository;

    QDataDictionaryChild qDataDictionaryChild = QDataDictionaryChild.dataDictionaryChild;
    QDataDictionary qDataDictionary = QDataDictionary.dataDictionary;

    @Override
    @Transactional
    public DataDictionaryChildModelExtend post(DataDictionaryChildModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        DataDictionaryChild entity = CopyUtils.copyBean(model, DataDictionaryChild.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataDictionaryChildModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataDictionaryChildModelExtend put(String id, DataDictionaryChildModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataDictionaryChild entity = findDataDictionaryChildById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataDictionaryChildModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataDictionaryChildModelExtend patch(String id, DataDictionaryChildModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataDictionaryChild entity = findDataDictionaryChildById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataDictionaryChildModelExtend.class);
    }

    @Override
    public DataDictionaryChildModelExtend get(String id) {
        DataDictionaryChild entity = findDataDictionaryChildById(id);
        return CopyUtils.copyBean(entity, DataDictionaryChildModelExtend.class);
    }

    @Override
    public PageOb<DataDictionaryChildModelExtend> get(DataDictionaryChildModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        JPAQuery<Tuple> jpaQuery = queryFactory.select(qDataDictionaryChild, qDataDictionary.name)
                .from(qDataDictionaryChild)
                .leftJoin(qDataDictionary)
                .on(qDataDictionaryChild.pid.eq(qDataDictionary.id));

        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qDataDictionaryChild);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, DataDictionaryChildModelExtend.class, tuple -> {
            DataDictionaryChildModelExtend dto = CopyUtils.copyBean(tuple.get(qDataDictionaryChild), DataDictionaryChildModelExtend.class);
            dto.setDictName(tuple.get(qDataDictionary.name));

            return dto;
        });
    }

    @Override
    public List<DataDictionaryChildModelExtend> get(DataDictionaryChildModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<DataDictionaryChild> pageDataDictionaryChild = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageDataDictionaryChild.getContent(), DataDictionaryChildModelExtend.class);
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
     * 通过id查询数据字典子表
     *
     * @param id
     * @return
     */
    private DataDictionaryChild findDataDictionaryChildById(String id) {
        DataDictionaryChild entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No DataDictionaryChild found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(DataDictionaryChildModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getPid())) {
            pre = ExpressionUtils.and(pre, qDataDictionaryChild.pid.eq(search.getPid()));
        }
        if (!StringUtils.isEmpty(search.getCode())) {
            pre = ExpressionUtils.and(pre, qDataDictionaryChild.code.like("%" + search.getCode() + "%"));
        }
        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, qDataDictionaryChild.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qDataDictionaryChild.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qDataDictionaryChild.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qDataDictionaryChild.isDelete.eq(0), qDataDictionaryChild.isDelete.isNull()));

        return pre;
    }
}
