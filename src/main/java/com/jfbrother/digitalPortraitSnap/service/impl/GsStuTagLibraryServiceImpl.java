package com.jfbrother.digitalPortraitSnap.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.digitalPortraitSnap.jpa.QGsStuTagRelation;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagRelationModelParam;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagRelationService;
import com.jfbrother.student.service.StudentSearchService;
import com.querydsl.core.Tuple;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
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
import com.jfbrother.digitalPortraitSnap.dao.GsStuTagLibraryRepository;
import com.jfbrother.digitalPortraitSnap.jpa.GsStuTagLibrary;
import com.jfbrother.digitalPortraitSnap.jpa.QGsStuTagLibrary;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagLibraryModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagLibraryModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagLibraryModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagLibraryService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;


/**
 *  学生标签库service实现
 * @author xinz 2023-03-27
 */
@Service
public class GsStuTagLibraryServiceImpl extends BaseServiceImpl implements GsStuTagLibraryService, StudentSearchService {
    @Autowired
    private GsStuTagLibraryRepository gsStuTagLibraryRepository;
    @Autowired
    private GsStuTagRelationService gsStuTagRelationService;

    QGsStuTagLibrary qGsStuTagLibrary = QGsStuTagLibrary.gsStuTagLibrary;
    QGsStuTagRelation qGsStuTagRelation = QGsStuTagRelation.gsStuTagRelation;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    @Transactional
    public GsStuTagLibraryModelExtend post(GsStuTagLibraryModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (!StringUtils.isEmpty(model.getTagEvaluationSql())) {
            if(!isSelect(model.getTagEvaluationSql())){
                throw new ServiceException(ResultCode.FORBIDDEN,"sql含DML语句，被禁止执行.");
            }
        }
        if (StringUtils.isEmpty(model.getDeleteFlag())) {
            model.setDeleteFlag("0");
        }
        GsStuTagLibrary entity = CopyUtils.copyBean(model, GsStuTagLibrary.class);
        entity = gsStuTagLibraryRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagLibraryModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsStuTagLibraryModelExtend put(String id, GsStuTagLibraryModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        if (!StringUtils.isEmpty(model.getTagEvaluationSql())) {
            if(!isSelect(model.getTagEvaluationSql())){
                throw new ServiceException(ResultCode.FORBIDDEN,"sql含DML语句，被禁止执行.");
            }
        }
        GsStuTagLibrary entity = findGsStuTagLibraryById(id);
        //自定义忽略字段
        String[] strings = {"id","deleteFlag","createId","createTime"};
        CopyUtils.copyBean(model, entity, strings);
        entity = gsStuTagLibraryRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagLibraryModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsStuTagLibraryModelExtend patch(String id, GsStuTagLibraryModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        if (!StringUtils.isEmpty(model.getTagEvaluationSql())) {
            if(!isSelect(model.getTagEvaluationSql())){
                throw new ServiceException(ResultCode.FORBIDDEN,"sql含DML语句，被禁止执行.");
            }
        }
        GsStuTagLibrary entity = findGsStuTagLibraryById(id);
        //自定义忽略字段,防止前端未传入updateTime,导致 updateTime不更新
        if(model.getUpdateId() == null){
            model.setUpdateId("");
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime("");
        }
        CopyUtils.copyBean(model, entity, CopyUtils.getNullPropertyNames(model));
        entity = gsStuTagLibraryRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagLibraryModelExtend.class);
    }

    @Override
    public GsStuTagLibraryModelExtend get(String id) {
        GsStuTagLibrary entity = findGsStuTagLibraryById(id);
        return CopyUtils.copyBean(entity, GsStuTagLibraryModelExtend.class);
    }

    @Override
    public PageOb<GsStuTagLibraryModelExtend> get(GsStuTagLibraryModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);
        Page<GsStuTagLibrary> pageGsStuTagLibrary = gsStuTagLibraryRepository.findAll(pre, pageable);
        return CopyUtils.copyPageOb(pageGsStuTagLibrary, GsStuTagLibraryModelExtend.class);
    }

    @Override
    public List<GsStuTagLibraryModelExtend> get(GsStuTagLibraryModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);
        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsStuTagLibrary> pageGsStuTagLibrary = gsStuTagLibraryRepository.findAll(pre, pageable);
        return CopyUtils.copyList(pageGsStuTagLibrary.getContent(), GsStuTagLibraryModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsStuTagLibraryRepository.deleteBatch(ids);
    }
    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsStuTagLibraryRepository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询学生标签库
     * @param id
     * @return
     */
    private GsStuTagLibrary findGsStuTagLibraryById(String id) {
        GsStuTagLibrary entity = null;
        try {
            entity = gsStuTagLibraryRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsStuTagLibrary found.");
        }
        return entity;
    }

    /**
     * 将查询条件转化为领域模型的查询对象
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsStuTagLibraryModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (StringUtils.isEmpty(search.getDeleteFlag())) {
            pre = ExpressionUtils.and(pre, qGsStuTagLibrary.deleteFlag.eq("0"));
        }
        if (!StringUtils.isEmpty(search.getId())) {
            pre = ExpressionUtils.and(pre, qGsStuTagLibrary.id.eq(search.getId()));
        }
        if (!StringUtils.isEmpty(search.getTagMajorItemCode())) {
            pre = ExpressionUtils.and(pre, qGsStuTagLibrary.tagMajorItemCode.eq(search.getTagMajorItemCode()));
        }
        if (!StringUtils.isEmpty(search.getIds())) {
            pre = ExpressionUtils.and(pre, qGsStuTagLibrary.id.in(spilt(search.getIds())));
        }
        return pre;
    }

    //字符串切割加单引号
    private static String spilt(String str) {
        StringBuffer sb = new StringBuffer();
        String[] temp = str.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (!"".equals(temp[i]) && temp[i] != null)
                sb.append("'" + temp[i] + "',");
        }
        String result = sb.toString();
        String tp = result.substring(result.length() - 1);
        if (",".equals(tp))
            return result.substring(0, result.length() - 1);
        else
            return result;
    }

    //验证sql只能是 select
    private static boolean isSelect(String sql) {
        return sql.matches("^(?i)(\\s*)(select)(\\s+)(((?!(insert|delete|update)).)+)$");
    }

    @Override
    public Map<String, Object> getConformToTagStuListPage(Map<String, Object> body) {
        if(StringUtils.isEmpty(body.get("sql"))){
            throw new ServiceException(ResultCode.FORBIDDEN,"sql查询语句缺失.");
        }
        if(!isSelect(body.get("sql").toString())){
            throw new ServiceException(ResultCode.FORBIDDEN,"sql含DML语句，被禁止执行.");
        }

        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery(body.get("sql").toString());
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map<String,Object>> list = query.getResultList();

        int page = body.get("page") == null ? 0 : (Integer) body.get("page");
        int size = body.get("size") == null ? 10 : (Integer) body.get("size");

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("totalCount",list.size());

        List<Map<String,Object>> subList = list.stream().skip(page * size).limit(size).
                collect(Collectors.toList());
        //表头
        List<String> tabHeadList = new ArrayList();
        if(subList != null || subList.size() > 0){
            a:for(Map<String,Object> m : subList){
                if (m.keySet() != null) {
                    Set<String> key = m.keySet();
                    for (String str : key) {
                        tabHeadList.add(str);
                    }
                    break a;
                }
            }
        }
        map.put("tabHead",tabHeadList);
        map.put("tabData",subList);
        return map;
    }

    @Override
    public void markTagToStu(Map<String, Object> body) {
        if(StringUtils.isEmpty(body.get("sql"))){
            throw new ServiceException(ResultCode.FORBIDDEN,"sql查询语句缺失.");
        }
        if(!isSelect(body.get("sql").toString())){
            throw new ServiceException(ResultCode.FORBIDDEN,"sql含DML语句，被禁止执行.");
        }
        if(StringUtils.isEmpty(body.get("tagId"))){
            throw new ServiceException(ResultCode.FORBIDDEN,"标签id缺失.");
        }

        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery(body.get("sql").toString());
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        //list查询出来的字段必须含有学号
        List<Map<String,Object>> list = query.getResultList();

        List<Tuple> tupleList = queryFactory.select(qGsStuTagRelation.stuCode,qGsStuTagRelation.tagId)
                .from(qGsStuTagRelation)
                .where(qGsStuTagRelation.deleteFlag.eq("0")).fetch();

        if(list.size() > 0){
            a:for(Map<String,Object> map : list){
                if(tupleList.size() > 0){
                     for(Tuple tuple : tupleList){
                         if(tuple.get(qGsStuTagRelation.stuCode).equals(map.get("xh"))
                                 && tuple.get(qGsStuTagRelation.tagId).equals(body.get("tagId"))){
                             break a;
                         }
                     }
                }
                GsStuTagRelationModelParam gsStuTagRelationModelParam = new GsStuTagRelationModelParam();
                gsStuTagRelationModelParam.setStuCode(map.get("xh").toString());
                gsStuTagRelationModelParam.setTagId(body.get("tagId").toString());
                gsStuTagRelationService.post(gsStuTagRelationModelParam);
            }
        }
    }

    @Override
    public Map<String,Object> getStuTagListPage(Map<String, Object> params) {
        int page = params.get("page") == null ? 0 : (Integer) params.get("page");
        int size = params.get("size") == null ? 10 : (Integer) params.get("size");

        List<Map<String,Object>> list = gsStuTagLibraryRepository.getStuTagListPage(
                params.get("keyWords") == null ? "null" : "%" + params.get("keyWords").toString() + "%",
                params.get("tagId") == null ? "null" : params.get("tagId").toString());

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("totalCount",list.size());
        List<Map<String,Object>> subList = list.stream().skip(page * size).limit(size).
                collect(Collectors.toList());
        map.put("result",subList);
        return map;
    }

    @Override
    public Map<String, Object> getLabelStatistics() {
        Map<String,Object> map = new HashMap<>();
        //大类统计
        List dlList = gsStuTagLibraryRepository.getTagMajorItemStatistics();
        map.put("dl_list",dlList);

        //标签按男女统计
        List<Map<String,Object>> nvList = gsStuTagLibraryRepository.getTagStatisticsBySex();
        List<Map<String,Object>> resList = new ArrayList<>();
        if(nvList.size() > 0){
            String ids = "";
            for(Map<String,Object> map1 : nvList){
                if(ids.indexOf(map1.get("id").toString()) > -1){
                    continue;
                }
                Map<String,Object> map2 = new HashMap<>();
                map2.put("id",map1.get("id"));
                map2.put("name",map1.get("name"));
                map2.put("dlcode",map1.get("dlcode"));
                int count = 0;
                for(Map<String,Object> map3 : nvList){
                    if(map1.get("id").equals(map3.get("id"))){
                        if(map3.get("sex").equals("男")){
                            map2.put("boy",map3.get("value"));
                        }else{
                            map2.put("girl",map3.get("value"));
                        }
                        count++;
                    }
                }
                if(count == 1){
                    boolean f = true;
                    if(map2.containsKey("boy")){
                        map2.put("girl",0);
                        f = false;
                    }
                    if(f){
                        if(map2.containsKey("girl")){
                            map2.put("boy",0);
                        }
                    }
                }
                resList.add(map2);
                ids += map2.get("id") + ",";
            }
        }
        map.put("nv_list",resList);
        return map;
    }

}
