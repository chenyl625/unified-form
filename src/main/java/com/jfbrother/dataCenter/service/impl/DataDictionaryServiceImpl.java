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
import com.jfbrother.dataCenter.dao.DataDictionaryRepository;
import com.jfbrother.dataCenter.jpa.DataDictionary;
import com.jfbrother.dataCenter.jpa.DataDictionaryChild;
import com.jfbrother.dataCenter.jpa.QDataDictionary;
import com.jfbrother.dataCenter.jpa.QDataDictionaryChild;
import com.jfbrother.dataCenter.model.DataDictionaryChildModel;
import com.jfbrother.dataCenter.model.extend.DataDictionaryChildModelExtend;
import com.jfbrother.dataCenter.model.extend.DataDictionaryModelExtend;
import com.jfbrother.dataCenter.model.param.DataDictionaryModelParam;
import com.jfbrother.dataCenter.model.response.StandardDataResponseModel;
import com.jfbrother.dataCenter.model.search.DataDictionaryModelSearch;
import com.jfbrother.dataCenter.service.DataDictionaryService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * 数据字典表service实现
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@Service
public class DataDictionaryServiceImpl extends BaseServiceImpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryRepository repository;
    @Autowired
    private DataDictionaryChildRepository childRepository;

    @Autowired
    @Qualifier("pgsqlSjglJdbcTemplate")
    protected JdbcTemplate jdbcTemplateSjgl;

    QDataDictionary qDataDictionary = QDataDictionary.dataDictionary;
    QDataDictionaryChild qDataDictionaryChild = QDataDictionaryChild.dataDictionaryChild;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictionaryModelExtend post(DataDictionaryModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        DataDictionary entity = CopyUtils.copyBean(model, DataDictionary.class);

        entity = repository.save(entity);

//        saveDictionaryChild(model.getDictChildList(), entity);

        return CopyUtils.copyBean(entity, DataDictionaryModelExtend.class);
    }

    private void saveDictionaryChild(List<DataDictionaryChildModel> childList, DataDictionary entity) {
        if (childList == null || childList.size() == 0) {
            return;
        }

        List<DataDictionaryChildModel> listChild = new ArrayList<>();
        for (DataDictionaryChildModel item : childList) {
            DataDictionaryChildModel childModel = new DataDictionaryChildModel();
            childModel.setCode(item.getCode());
            childModel.setName(item.getName());
            childModel.setSortNum(item.getSortNum());
            listChild.add(childModel);
        }

        try {
            saveChildren(childList,
                    QDataDictionaryChild.dataDictionaryChild,
                    entity.getId(),
                    "pid",
                    childRepository,
                    DataDictionaryChild.class);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "数据字典子表保存失败.");
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictionaryModelExtend put(String id, DataDictionaryModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataDictionary entity = findDataDictionaryById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);

//        saveDictionaryChild(model.getDictChildList(), entity);

        return CopyUtils.copyBean(entity, DataDictionaryModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictionaryModelExtend patch(String id, DataDictionaryModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        DataDictionary entity = findDataDictionaryById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, DataDictionaryModelExtend.class);
    }

    @Override
    public DataDictionaryModelExtend get(String id) {
        DataDictionary entity = findDataDictionaryById(id);
        DataDictionaryModelExtend model = CopyUtils.copyBean(entity, DataDictionaryModelExtend.class);

        //查询子表
        List<DataDictionaryChildModelExtend> childList = queryFactory.selectFrom(qDataDictionaryChild).where(qDataDictionaryChild.pid.eq(id)).fetch().stream().map(item -> CopyUtils.copyBean(item, DataDictionaryChildModelExtend.class)).collect(Collectors.toList());
        model.setDictChildList(childList);

        return model;
    }

    @Override
    public PageOb<DataDictionaryModelExtend> get(DataDictionaryModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<DataDictionary> pageDataDictionary = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageDataDictionary, DataDictionaryModelExtend.class);
    }

    @Override
    public List<DataDictionaryModelExtend> get(DataDictionaryModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<DataDictionary> pageDataDictionary = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageDataDictionary.getContent(), DataDictionaryModelExtend.class);
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
    public void syncStandardData() {
        List<StandardDataResponseModel> list = jdbcTemplateSjgl.query("SELECT info_id,info_name,code,name FROM standard.standard_data WHERE is_use = 1 GROUP BY info_id,info_name,code,name ORDER BY code Asc ", new BeanPropertyRowMapper(StandardDataResponseModel.class));
        if (list.size() == 0) {
            throw new ServiceException(ResultCode.NOT_FOUND, "标准数据项为空!");
        }
        //标准数据项
        Map<String, List<StandardDataResponseModel>> standardMap = list.stream().collect(Collectors.groupingBy(StandardDataResponseModel::getInfoId));
        //已存的字典
        Map<String, DataDictionary> dictMap = queryFactory.selectFrom(qDataDictionary).where(qDataDictionary.infoId.isNotNull()).fetch().stream().collect(Collectors.toMap(DataDictionary::getInfoId, item -> item));
        //
        Map<String, List<String>> dictChildMap = queryFactory.select(qDataDictionaryChild.id, qDataDictionaryChild.pid)
                .from(qDataDictionaryChild)
                .leftJoin(qDataDictionary).on(qDataDictionaryChild.pid.eq(qDataDictionary.id))
                .where(qDataDictionary.infoId.isNotNull())
                .fetch().stream()
                .collect(Collectors.groupingBy(tuple -> tuple.get(qDataDictionaryChild.pid)
                        , Collectors.collectingAndThen(Collectors.toList(), items -> items.stream().map(tuple -> tuple.get(qDataDictionaryChild.id)).collect(Collectors.toList()))));

        for (Map.Entry<String, List<StandardDataResponseModel>> entry : standardMap.entrySet()) {
            DataDictionary dictionary = dictMap.get(entry.getKey());
            List<StandardDataResponseModel> dataList = entry.getValue();

            if (StringUtils.isEmpty(dictionary)) {
                dictionary = new DataDictionary();
                dictionary.setName(dataList.get(0).getInfoName());
                dictionary.setInfoId(dataList.get(0).getInfoId());

                List<DataDictionaryChildModel> dictList = dataList.stream().map(item -> {
                    DataDictionaryChildModel model = new DataDictionaryChildModel();
                    model.setCode(item.getCode());
                    model.setName(item.getName());

                    return model;
                }).collect(Collectors.toList());

                //保存数据标准项
                saveDictionaryChild(dictList, dictionary);
            } else {
                //更新
                String id = dictionary.getId();

                DataDictionary entity = findDataDictionaryById(id);
                CopyUtils.copyBean(dictionary, entity, Constant.UPDATE_IGNORE_FIELDS);
                repository.save(entity);

                //删除子表数据
                List<String> delIds = dictChildMap.get(id);
                if (delIds != null && delIds.size() > 0) {
                    queryFactory.delete(qDataDictionaryChild).where(qDataDictionaryChild.id.in(delIds)).execute();
                }

                //插入新数据项
                AtomicInteger i = new AtomicInteger();
                if (dataList.size() > 0) {
                    childRepository.saveAll(dataList.stream().map(item -> {
                        DataDictionaryChild child = CopyUtils.copyBean(item, DataDictionaryChild.class);
                        child.setPid(id);
                        child.setSortNum(i.getAndIncrement());
                        return child;
                    }).collect(Collectors.toList()));
                }
            }
        }
    }

    /**
     * 通过id查询数据字典表
     *
     * @param id
     * @return
     */
    private DataDictionary findDataDictionaryById(String id) {
        DataDictionary entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No DataDictionary found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(DataDictionaryModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, qDataDictionary.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qDataDictionary.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qDataDictionary.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qDataDictionary.isDelete.eq(0), qDataDictionary.isDelete.isNull()));

        return pre;
    }
}
