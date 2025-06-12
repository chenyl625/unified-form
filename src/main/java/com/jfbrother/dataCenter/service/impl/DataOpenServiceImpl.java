package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.jpa.QConnectorInfo;
import com.jfbrother.dataCenter.dao.DataOpenRepository;
import com.jfbrother.dataCenter.jpa.DataOpen;
import com.jfbrother.dataCenter.jpa.QDataOpen;
import com.jfbrother.dataCenter.model.extend.DataOpenModelExtend;
import com.jfbrother.dataCenter.model.param.DataOpenModelParam;
import com.jfbrother.dataCenter.model.search.DataOpenModelSearch;
import com.jfbrother.dataCenter.service.DataOpenService;
import com.jfbrother.standard.jpa.QStandardDataTheme;
import com.jfbrother.standard.jpa.StandardDataTheme;
import com.jfbrother.standard.model.extend.StandardDataThemeModelExtend;
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

import java.util.*;
import java.util.stream.Collectors;


/**
 * 数据开放管理service实现
 *
 * @author chenyl@jfbrother.com 2022-09-16
 */
@Service
public class DataOpenServiceImpl extends BaseServiceImpl implements DataOpenService {
    @Autowired
    private DataOpenRepository repository;

    QDataOpen qDataOpen = QDataOpen.dataOpen;
    QStandardDataTheme qStandardDataTheme = QStandardDataTheme.standardDataTheme;
    QConnectorInfo qConnectorInfo = QConnectorInfo.connectorInfo;

    @Override
    @Transactional
    public DataOpenModelExtend post(DataOpenModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        DataOpen entity = CopyUtils.copyBean(model, DataOpen.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataOpenModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataOpenModelExtend put(String id, DataOpenModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataOpen entity = findDataOpenById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataOpenModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public DataOpenModelExtend patch(String id, DataOpenModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataOpen entity = findDataOpenById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataOpenModelExtend.class);
    }

    @Override
    public DataOpenModelExtend get(String id) {
        DataOpen entity = findDataOpenById(id);
        JPAQuery<Tuple> jpaQuery = getTupleJPAQuery();
        List<DataOpenModelExtend> collect = jpaQuery.where(qDataOpen.id.eq(id)).fetch().stream().map(item -> {
            DataOpenModelExtend model = CopyUtils.copyBean(item.get(qDataOpen), DataOpenModelExtend.class);
            model.setThemeName(item.get(qStandardDataTheme.themeName));
            model.setConnName(item.get(qConnectorInfo.connName));
            model.setConnUrl(item.get(qConnectorInfo.connUrl));
            model.setConnUrlPage(item.get(qConnectorInfo.connUrlPage));
            return model;
        }).collect(Collectors.toList());
        return collect.get(0);
    }

    @Override
    public PageOb<DataOpenModelExtend> get(DataOpenModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTupleJPAQuery();

        Predicate pre = fillPredicate(search);
        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qDataOpen);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, DataOpenModelExtend.class, tuple -> {
            DataOpenModelExtend model = CopyUtils.copyBean(tuple.get(qDataOpen), DataOpenModelExtend.class);
            model.setThemeName(tuple.get(qStandardDataTheme.themeName));
            model.setConnName(tuple.get(qConnectorInfo.connName));

            return model;
        });
    }

    private JPAQuery<Tuple> getTupleJPAQuery() {
        return queryFactory.select(qDataOpen
                    , qStandardDataTheme.themeName
                    , qConnectorInfo.connName
                    ,qConnectorInfo.connUrl
                    ,qConnectorInfo.connUrlPage
            )
                    .from(qDataOpen)
                    .leftJoin(qStandardDataTheme).on(qDataOpen.themeId.eq(qStandardDataTheme.id))
                    .leftJoin(qConnectorInfo).on(qDataOpen.connId.eq(qConnectorInfo.id));
    }

    @Override
    public List<DataOpenModelExtend> get(DataOpenModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<DataOpen> pageDataOpen = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageDataOpen.getContent(), DataOpenModelExtend.class);
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

    @Override
    public List<StandardDataThemeModelExtend> getByRole(String roleId) {
        //Map<String, String> themeMap = queryFactory.selectFrom(qStandardDataTheme).fetch().stream().collect(Collectors.toMap(StandardDataTheme::getId, StandardDataTheme::getThemeName));

        Map<String, List<DataOpenModelExtend>> listMap = queryFactory.select(qDataOpen
                , qStandardDataTheme.id
                , qStandardDataTheme.sortNum
                , qConnectorInfo.connUrl
                , qConnectorInfo.connUrlPage
        ).from(qDataOpen)
                .leftJoin(qStandardDataTheme).on(qDataOpen.themeId.eq(qStandardDataTheme.id))
                .leftJoin(qConnectorInfo).on(qDataOpen.connId.eq(qConnectorInfo.id))
                .where(qDataOpen.openRoles.like("%" + roleId + "%"))
                .fetch().stream()
                //按主题排序
                .sorted(Comparator.comparing((Tuple tuple) -> tuple.get(qStandardDataTheme.sortNum)))
                //按主题分组
                .collect(Collectors.groupingBy(tuple -> tuple.get(qStandardDataTheme.id)
                        , LinkedHashMap::new
                        , Collectors.collectingAndThen(Collectors.toList(), items -> items.stream().map(tuple -> {
                            DataOpenModelExtend dto = CopyUtils.copyBean(tuple.get(qDataOpen), DataOpenModelExtend.class);
                            dto.setConnUrl(tuple.get(qConnectorInfo.connUrl));
                            dto.setConnUrlPage(tuple.get(qConnectorInfo.connUrlPage));

                            return dto;
                        }).sorted(Comparator.comparing(DataOpenModelExtend::getSortNum)).collect(Collectors.toList()))
                ));

//        Map<String, List<DataOpenModelExtend>> items = new HashMap<>(listMap.size());
//        for (Map.Entry<String, List<DataOpenModelExtend>> entry : listMap.entrySet()) {
//            items.put(themeMap.get(entry.getKey()), entry.getValue());
//        }

        List<StandardDataThemeModelExtend> collect = queryFactory.selectFrom(qStandardDataTheme).fetch().stream().filter(obj->listMap.containsKey(obj.getId())).map(item -> {
            StandardDataThemeModelExtend extend = CopyUtils.copyBean(item, StandardDataThemeModelExtend.class);
            extend.setDataOpenList(listMap.get(item.getId()));
            return extend;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 通过id查询数据开放管理
     *
     * @param id
     * @return
     */
    private DataOpen findDataOpenById(String id) {
        DataOpen entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No DataOpen found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(DataOpenModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getThemeId())) {
            pre = ExpressionUtils.and(pre, qDataOpen.themeId.like("%" + search.getThemeId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getDataItemName())) {
            pre = ExpressionUtils.and(pre, qDataOpen.dataItemName.like("%" + search.getDataItemName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getConnId())) {
            pre = ExpressionUtils.and(pre, qDataOpen.connId.like("%" + search.getConnId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIcons())) {
            pre = ExpressionUtils.and(pre, qDataOpen.icons.like("%" + search.getIcons() + "%"));
        }
        if (!StringUtils.isEmpty(search.getOpenRoles())) {
            pre = ExpressionUtils.and(pre, qDataOpen.openRoles.like("%" + search.getOpenRoles() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qDataOpen.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qDataOpen.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qDataOpen.isDelete.eq(0), qDataOpen.isDelete.isNull()));

        return pre;
    }
}
