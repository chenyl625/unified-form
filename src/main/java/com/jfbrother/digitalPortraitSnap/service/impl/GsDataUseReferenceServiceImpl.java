package com.jfbrother.digitalPortraitSnap.service.impl;

import java.util.*;

import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.digitalPortraitSnap.dao.GsDataUseReferenceRepository;
import com.jfbrother.digitalPortraitSnap.jpa.GsDataUseReference;
import com.jfbrother.digitalPortraitSnap.jpa.QGsDataUseReference;
import com.jfbrother.digitalPortraitSnap.model.param.GsDataUseReferenceModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsDataUseReferenceModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsDataUseReferenceModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GsDataUseReferenceService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
 * 数据使用参照,快照系统取数依据service实现
 * @author xinz 2023-03-30
 */
@Service
public class GsDataUseReferenceServiceImpl extends BaseServiceImpl implements GsDataUseReferenceService {
    @Autowired
    private GsDataUseReferenceRepository gsDataUseReferenceRepository;

    QGsDataUseReference qGsDataUseReference = QGsDataUseReference.gsDataUseReference;

    @Override
    @Transactional
    public GsDataUseReferenceModelExtend post(GsDataUseReferenceModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (StringUtils.isEmpty(model.getDeleteFlag())) {
            model.setDeleteFlag("0");
        }
        if(!StringUtils.isEmpty(model.getStatus())){
            if(model.getStatus().equals("1")){
                Predicate pre = qGsDataUseReference.deleteFlag.eq("0");
                pre = ExpressionUtils.and(pre, qGsDataUseReference.status.eq("1"));
                Iterable<GsDataUseReference> iterable = gsDataUseReferenceRepository.findAll(pre);
                for(GsDataUseReference gsDataUseReference : iterable){
                    gsDataUseReference.setStatus("0");
                    gsDataUseReferenceRepository.save(gsDataUseReference);
                }
            }
        }
        GsDataUseReference entity = CopyUtils.copyBean(model, GsDataUseReference.class);
        entity = gsDataUseReferenceRepository.save(entity);
        return CopyUtils.copyBean(entity, GsDataUseReferenceModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsDataUseReferenceModelExtend put(String id, GsDataUseReferenceModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        GsDataUseReference entity = findGsDataUseReferenceById(id);
        //自定义忽略字段
        String[] strings = {"id","deleteFlag","createId","createTime"};
        CopyUtils.copyBean(model, entity, strings);
        entity = gsDataUseReferenceRepository.save(entity);
        return CopyUtils.copyBean(entity, GsDataUseReferenceModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsDataUseReferenceModelExtend patch(String id, GsDataUseReferenceModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        if(!StringUtils.isEmpty(model.getStatus())){
            Predicate pre = qGsDataUseReference.deleteFlag.eq("0");
            pre = ExpressionUtils.and(pre, qGsDataUseReference.status.eq("1"));
            Iterable<GsDataUseReference> iterable = gsDataUseReferenceRepository.findAll(pre);
            if(model.getStatus().equals("1")){
                for(GsDataUseReference gsDataUseReference : iterable){
                    if(!gsDataUseReference.getId().equals(id)){
                        gsDataUseReference.setStatus("0");
                        gsDataUseReferenceRepository.save(gsDataUseReference);
                    }
                }
            }else{
                List<GsDataUseReference> list = queryFactory.select(qGsDataUseReference)
                        .from(qGsDataUseReference)
                        .where(qGsDataUseReference.deleteFlag.eq("0"))
                        .orderBy(qGsDataUseReference.orderBy.asc()).fetch();
                if(list.size() > 0){
                    boolean f = false;
                    for(GsDataUseReference gsDataUseReference : list){
                        if(gsDataUseReference.getStatus().equals("1") && !gsDataUseReference.getId().equals(id)){
                            f = true;
                            break;
                        }
                    }
                    if(!f){
                        for(GsDataUseReference gsDataUseReference : list){
                            if(!gsDataUseReference.getId().equals(id)){
                                gsDataUseReference.setStatus("1");
                                gsDataUseReferenceRepository.save(gsDataUseReference);
                                break;
                            }
                        }
                    }
                }
            }
        }
        GsDataUseReference entity = findGsDataUseReferenceById(id);
        //自定义忽略字段,防止前端未传入updateTime,导致 updateTime不更新
        if(model.getUpdateId() == null){
            model.setUpdateId("");
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime("");
        }
        CopyUtils.copyBean(model, entity, CopyUtils.getNullPropertyNames(model));
        entity = gsDataUseReferenceRepository.save(entity);
        return CopyUtils.copyBean(entity, GsDataUseReferenceModelExtend.class);
    }

    @Override
    public GsDataUseReferenceModelExtend get(String id) {
        GsDataUseReference entity = findGsDataUseReferenceById(id);
        return CopyUtils.copyBean(entity, GsDataUseReferenceModelExtend.class);
    }

    @Override
    public PageOb<GsDataUseReferenceModelExtend> get(GsDataUseReferenceModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);
        Page<GsDataUseReference> pageGsDataUseReference = gsDataUseReferenceRepository.findAll(pre, pageable);
        return CopyUtils.copyPageOb(pageGsDataUseReference, GsDataUseReferenceModelExtend.class);
    }

    @Override
    public List<GsDataUseReferenceModelExtend> get(GsDataUseReferenceModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);
        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsDataUseReference> pageGsDataUseReference = gsDataUseReferenceRepository.findAll(pre, pageable);
        return CopyUtils.copyList(pageGsDataUseReference.getContent(), GsDataUseReferenceModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsDataUseReferenceRepository.deleteBatch(ids);
    }
    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsDataUseReferenceRepository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询数据使用参照,快照系统取数依据
     * @param id
     * @return
     */
    private GsDataUseReference findGsDataUseReferenceById(String id) {
        GsDataUseReference entity = null;
        try {
            entity = gsDataUseReferenceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsDataUseReference found.");
        }
        return entity;
    }

    /**
     * 将查询条件转化为领域模型的查询对象
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsDataUseReferenceModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getTemplateName())) {
            pre = ExpressionUtils.and(pre, qGsDataUseReference.templateName.like("%" + search.getTemplateName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getStatus())) {
            pre = ExpressionUtils.and(pre, qGsDataUseReference.status.like("%" + search.getStatus() + "%"));
        }
        if (StringUtils.isEmpty(search.getDeleteFlag())) {
            pre = ExpressionUtils.and(pre, qGsDataUseReference.deleteFlag.eq("0"));
        }
        return pre;
    }

    @Override
    public Map<String,Object> getDataUseTemplate(String stuCode) {
        List<Map<String,Object>> list = gsDataUseReferenceRepository.getDataUseTemplate(stuCode);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}