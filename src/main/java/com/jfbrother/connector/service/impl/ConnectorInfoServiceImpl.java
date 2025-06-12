package com.jfbrother.connector.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.LoginUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.dao.ConnectorInfoRepository;
import com.jfbrother.connector.jpa.ConnectorInfo;
import com.jfbrother.connector.jpa.QConnectorField;
import com.jfbrother.connector.jpa.QConnectorInfo;
import com.jfbrother.connector.model.extend.ConnectorInfoModelExtend;
import com.jfbrother.connector.model.param.ConnectorInfoModelParam;
import com.jfbrother.connector.model.search.ConnectorInfoModelSearch;
import com.jfbrother.connector.service.ConnectorInfoService;
import com.jfbrother.dataCenter.jpa.QDataDictionary;
import com.jfbrother.dataCenter.jpa.QDataDictionaryChild;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 接口管理service实现
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Service
@Slf4j
public class ConnectorInfoServiceImpl extends BaseServiceImpl implements ConnectorInfoService {
    @Autowired
    private ConnectorInfoRepository repository;

    @Autowired
    private RedisService redisService;

    QConnectorInfo qConnectorInfo = QConnectorInfo.connectorInfo;
    QConnectorField qConnectorField = QConnectorField.connectorField;
    QDataDictionary qDataDictionary = QDataDictionary.dataDictionary;
    QDataDictionaryChild qDataDictionaryChild = QDataDictionaryChild.dataDictionaryChild;

    @Value("${baseUrl}")
    private String baseUrl;

    final public static String FIELD_ORIGINAL_REF = "originalRef";
    final public static String FIELD_PROPERTIES = "properties";
    final public static String FIELD_CONVERT_TYPE = "array";
    final public static String FIELD_ITEMS = "items";
    final public static String FIELD_TYPE = "type";

    @Override
    @Transactional
    public ConnectorInfoModelExtend post(ConnectorInfoModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        ConnectorInfo entity = CopyUtils.copyBean(model, ConnectorInfo.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorInfoModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public ConnectorInfoModelExtend put(String id, ConnectorInfoModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        ConnectorInfo entity = findConnectorInfoById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorInfoModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public ConnectorInfoModelExtend patch(String id, ConnectorInfoModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        ConnectorInfo entity = findConnectorInfoById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, ConnectorInfoModelExtend.class);
    }

    @Override
    public ConnectorInfoModelExtend get(String id) {
        ConnectorInfo entity = findConnectorInfoById(id);
        return CopyUtils.copyBean(entity, ConnectorInfoModelExtend.class);
    }

    @Override
    public PageOb<ConnectorInfoModelExtend> get(ConnectorInfoModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<ConnectorInfo> pageConnectorInfo = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageConnectorInfo, ConnectorInfoModelExtend.class);
    }

    @Override
    public List<ConnectorInfoModelExtend> get(ConnectorInfoModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<ConnectorInfo> pageConnectorInfo = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageConnectorInfo.getContent(), ConnectorInfoModelExtend.class);
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
    public JSONObject getField(String id) {
        ConnectorInfo connectorInfo = findConnectorInfoById(id);

        Object o = redisService.get(RedisKeyConstant.SWAGGER_API);
        if (o == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "无接口信息.");
        }
        JSONObject jsonObject = JSONObject.parseObject(o.toString());

        Object definitionsObj = jsonObject.get("definitions");
        Object pathsObj = jsonObject.get("paths");
        if (StringUtils.isEmpty(definitionsObj) || StringUtils.isEmpty(pathsObj)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "无接口信息.");
        }
        // 定义
        JSONObject definitions = JSONObject.parseObject(definitionsObj.toString());
        // 路径
        JSONObject paths = JSONObject.parseObject(pathsObj.toString());

        Object pathTypesObj = paths.get(connectorInfo.getConnUrl());
        if (StringUtils.isEmpty(pathTypesObj)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "接口不存在.");
        }
        JSONObject pathTypes = JSONObject.parseObject(pathTypesObj.toString());
        Object connTypeObj = pathTypes.get(connectorInfo.getConnType());
        if (StringUtils.isEmpty(connTypeObj)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "接口类型不存在.");
        }
        JSONObject connType = JSONObject.parseObject(connTypeObj.toString());
        String responses = JSONObject.parseObject(connType.getString("responses")).getString("200");

        //响应结果
        String schema = JSONObject.parseObject(responses).getString("schema");
        Object responsesResultObj = definitions.get(JSONObject.parseObject(schema).getString(FIELD_ORIGINAL_REF));
        if (StringUtils.isEmpty(responsesResultObj)) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到该接口信息.");
        }
        String properties = JSONObject.parseObject(responsesResultObj.toString()).getString(FIELD_PROPERTIES);
        JSONObject data = JSONObject.parseObject(JSONObject.parseObject(properties).getString("data"));
        Object _originalRef = null;
        if (StringUtils.isEmpty(data.get(FIELD_TYPE))) {
            _originalRef = data.get(FIELD_ORIGINAL_REF);
        } else {
            String type = data.getString(FIELD_TYPE);
            if (FIELD_CONVERT_TYPE.equals(type)) {
                _originalRef = JSONObject.parseObject(data.get(FIELD_ITEMS).toString()).get(FIELD_ORIGINAL_REF);
            }
        }

        return convertItemsField(_originalRef, definitions, new ArrayList<>());
    }

    @Override
    public JSONArray getData(String id, Map<String, Object> param) {
        ConnectorInfo connectorInfo = findConnectorInfoById(id);

        //获取参数
        if (StringUtils.isEmpty(param) || param.size() == 0) {
            param = getCommonParam();
        }
        StringSubstitutor str = new StringSubstitutor(param);
        String url = baseUrl + connectorInfo.getConnUrl() + str.replace(connectorInfo.getWithParams());
        log.info("url【{}】", url);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("authorization");

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> res = restTemplate.exchange(url, HttpMethod.GET, httpEntity, JSONObject.class);

        JSONObject body = res.getBody();
        if (StringUtils.isEmpty(body) || StringUtils.isEmpty(body.get("data"))) {
            throw new ServiceException(ResultCode.NOT_FOUND, "未获取到该接口信息.");
        }
        return JSONArray.parseArray(JSON.toJSONString(body.get("data")));
    }

    @Override
    public Map<String, Map<String, String>> getCommonDictMap(String apiUrl) {
        return queryFactory.select(qConnectorField.fieldName
                , qDataDictionaryChild.code
                , qDataDictionaryChild.name
        )
                .from(qConnectorInfo)
                .leftJoin(qConnectorField).on(qConnectorField.connId.eq(qConnectorInfo.id))
                .leftJoin(qDataDictionary).on(qDataDictionary.id.eq(qConnectorField.dictId))
                .leftJoin(qDataDictionaryChild).on(qDataDictionaryChild.pid.eq(qDataDictionary.id))
                .where((qConnectorInfo.connUrl.eq(apiUrl).or(qConnectorInfo.connUrlPage.eq(apiUrl)))
                        .and(qConnectorField.dictId.isNotNull()).and(qConnectorField.dictId.ne(""))
                )
                .fetch().stream()
                .collect(Collectors.groupingBy(tuple -> tuple.get(qConnectorField.fieldName)
                        , Collectors.toMap(tuple -> tuple.get(qDataDictionaryChild.code), tuple -> tuple.get(qDataDictionaryChild.name))));
    }

    private Map<String, Object> getCommonParam() {
        return new HashMap<String, Object>() {
            {
                put("username", LoginUtils.getUserInfo().getUsername());
                put("deptId", LoginUtils.getUserInfo().getDeptId());
            }
        };
    }

    private JSONObject convertItemsField(Object originalRef, JSONObject
            definitions, List<String> originalRefList) {
        JSONObject data = new JSONObject();
        if (StringUtils.isEmpty(originalRef)
                || originalRefList.contains(originalRef.toString())
                || StringUtils.isEmpty(definitions.get(originalRef))) {
            return data;
        }
        originalRefList.add(originalRef.toString());
        JSONObject properties = JSONObject.parseObject(JSONObject.parseObject(definitions.get(originalRef).toString()).getString(FIELD_PROPERTIES));

        for (String key : properties.keySet()) {
            JSONObject item = JSONObject.parseObject(properties.getString(key));
            Object type = item.get(FIELD_TYPE);

            if (StringUtils.isEmpty(type)) {
                JSONObject _item = new JSONObject();
                _item.put(FIELD_TYPE, item.get(FIELD_ORIGINAL_REF));
                _item.put("description", item.get("description"));
                _item.put(FIELD_ITEMS, convertItemsField(item.get(FIELD_ORIGINAL_REF), definitions, originalRefList));
                data.put(key, _item);
            } else if (FIELD_CONVERT_TYPE.equals(type.toString())) {
                Object _originalRef = JSONObject.parseObject(item.getString(FIELD_ITEMS)).get(FIELD_ORIGINAL_REF);
                if (StringUtils.isEmpty(_originalRef)) {
                    data.put(key, item);
                    continue;
                }
                item.put(FIELD_ITEMS, convertItemsField(_originalRef, definitions, originalRefList));
                data.put(key, item);
            } else {
                data.put(key, item);
            }
        }

        return data;
    }

    /**
     * 通过id查询接口管理
     *
     * @param id
     * @return
     */
    private ConnectorInfo findConnectorInfoById(String id) {
        ConnectorInfo entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No ConnectorInfo found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(ConnectorInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getConnName())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.connName.like("%" + search.getConnName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getConnUrl())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.connUrl.like("%" + search.getConnUrl() + "%"));
        }
        if (!StringUtils.isEmpty(search.getConnUrlPage())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.connUrlPage.like("%" + search.getConnUrlPage() + "%"));
        }
        if (!StringUtils.isEmpty(search.getWithParams())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.withParams.like("%" + search.getWithParams() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qConnectorInfo.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qConnectorInfo.isDelete.eq(0), qConnectorInfo.isDelete.isNull()));

        return pre;
    }
}
