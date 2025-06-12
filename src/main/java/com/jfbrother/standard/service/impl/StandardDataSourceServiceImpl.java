package com.jfbrother.standard.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.standard.dao.StandardDataSourceRepository;
import com.jfbrother.standard.jpa.QStandardDataSource;
import com.jfbrother.standard.jpa.StandardDataSource;
import com.jfbrother.standard.model.extend.StandardDataSourceModelExtend;
import com.jfbrother.standard.model.param.StandardDataSourceModelParam;
import com.jfbrother.standard.model.search.StandardDataSourceModelSearch;
import com.jfbrother.standard.service.StandardDataSourceService;
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
 * 数据来源标准库service实现
 *
 * @author chenyl@jfbrother.com 2022-08-04
 */
@Service
public class StandardDataSourceServiceImpl extends BaseServiceImpl implements StandardDataSourceService {
    @Autowired
    private StandardDataSourceRepository repository;

    QStandardDataSource qStandardDataSource = QStandardDataSource.standardDataSource;
    QSysUsers qSysUsers = QSysUsers.sysUsers;

    @Override
    @Transactional
    public StandardDataSourceModelExtend post(StandardDataSourceModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        StandardDataSource entity = CopyUtils.copyBean(model, StandardDataSource.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, StandardDataSourceModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public StandardDataSourceModelExtend put(String id, StandardDataSourceModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        StandardDataSource entity = findStandardDataSourceById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, StandardDataSourceModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public StandardDataSourceModelExtend patch(String id, StandardDataSourceModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        StandardDataSource entity = findStandardDataSourceById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, StandardDataSourceModelExtend.class);
    }

    @Override
    public StandardDataSourceModelExtend get(String id) {
        StandardDataSource entity = findStandardDataSourceById(id);
        return CopyUtils.copyBean(entity, StandardDataSourceModelExtend.class);
    }

    @Override
    public PageOb<StandardDataSourceModelExtend> get(StandardDataSourceModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qStandardDataSource, qSysUsers.name)
                .from(qStandardDataSource)
                .leftJoin(qSysUsers)
                .on(qStandardDataSource.checkUser.eq(qSysUsers.username));

        Predicate pre = fillPredicate(search);

        setPageParams(jpaQuery.where(pre), pageable, qStandardDataSource);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, StandardDataSourceModelExtend.class, tuple -> {
            StandardDataSourceModelExtend model = CopyUtils.copyBean(tuple.get(qStandardDataSource), StandardDataSourceModelExtend.class);
            model.setCheckUserName(tuple.get(qSysUsers.name));

            return model;
        });
    }

    @Override
    public List<StandardDataSourceModelExtend> get(StandardDataSourceModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<StandardDataSource> pageStandardDataSource = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageStandardDataSource.getContent(), StandardDataSourceModelExtend.class);
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
     * 通过id查询数据来源标准库
     *
     * @param id
     * @return
     */
    private StandardDataSource findStandardDataSourceById(String id) {
        StandardDataSource entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No StandardDataSource found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(StandardDataSourceModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, qStandardDataSource.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getCheckUser())) {
            pre = ExpressionUtils.and(pre, qStandardDataSource.checkUser.like("%" + search.getCheckUser() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qStandardDataSource.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qStandardDataSource.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qStandardDataSource.isDelete.eq(0), qStandardDataSource.isDelete.isNull()));

        return pre;
    }
}
