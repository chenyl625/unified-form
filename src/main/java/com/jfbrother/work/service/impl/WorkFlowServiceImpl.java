package com.jfbrother.work.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.work.model.request.*;
import com.jfbrother.work.model.response.WorkFlowResponseModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import com.jfbrother.work.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;


/**
 * 自定义表单service实现
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Service
@Slf4j
public class WorkFlowServiceImpl extends BaseServiceImpl implements WorkFlowService {

    @Autowired
    private RedisService redisService;

    public final String CLIENT_ID = "gsxy";
    public final String SECRET_KEY = "$2a$10$W4g8xgdxzAwXo6krH8MTZO";
    public final String BMP_URL = "http://xinoa.zjbti.net.cn/wh/client/bpm/dispatcher";

    public String createToken() {
        String token = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(BMP_URL + "?cmd=token&client_id={1}&secret={2}", String.class, CLIENT_ID, SECRET_KEY);
        JSONObject body = JSON.parseObject(res.getBody());
        if (body.get("queryObj") != null) {
            JSONObject queryObj = JSONObject.parseObject(body.get("queryObj").toString());
            token = queryObj.getString("token");
        }

        return token;
    }

    public String getToken() {
        String token;

        Object obj = redisService.get(RedisKeyConstant.iBMP_TOKEN);
        if (StringUtils.isEmpty(obj)) {
            token = createToken();

            //access_token的有效期为7200秒（2小时）
            redisService.set(RedisKeyConstant.iBMP_TOKEN, token, 2 * 60 * 60L);
        } else {
            token = String.valueOf(obj);
        }

        return token;
    }

    @Override
    public PageOb<WorkFlowResponseModel> get(WorkFlowRequestModel search, Pageable pageable) {
        //添加参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);
        param.put("page", pageable.getPageNumber() + 1);
        param.put("page_size", pageable.getPageSize());

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=workflow.list", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        JSONObject jsonObject = JSONObject.parseObject(body.get("queryObj").toString());
        List<WorkFlowResponseModel> list = JSONArray.parseArray(jsonObject.get("list").toString(), WorkFlowResponseModel.class);
        long total = Long.valueOf(jsonObject.get("total").toString());

        return new PageOb(pageable.getPageNumber() + 1, pageable.getPageSize(), total, list);
    }

    @Override
    public List<WorkFlowTaskResponseModel> getTask(WorkFlowTaskRequestModel search) {
        if (StringUtils.isEmpty(search.getWf_id())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程实例id不能为空");
        }
        //添加参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.get", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        return JSONArray.parseArray(body.get("queryObj").toString(), WorkFlowTaskResponseModel.class);
    }

    @Override
    public JSONObject create(WorkFlowCreateRequestModel model) {
        if (StringUtils.isEmpty(model.getWf_def())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程自定义标识不能为空");
        }
        if (StringUtils.isEmpty(model.getInit_user())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "发起人不能为空");
        }
        //设置参数
        JSONObject param = (JSONObject) JSONObject.toJSON(model);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=workflow.create", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        return JSONObject.parseObject(body.get("queryObj").toString());
    }

    @Override
    public JSONObject taskForward(WorkFlowTaskForwardRequestModel model) {
        if (StringUtils.isEmpty(model.getTask_id())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "办理环节id不能为空");
        }
        if (StringUtils.isEmpty(model.getAssignee())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "办理人不能为空");
        }
        //设置参数
        JSONObject param = (JSONObject) JSONObject.toJSON(model);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.forward", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        return JSONObject.parseObject(body.get("queryObj").toString());
    }

    @Override
    public JSONObject getFormData(WorkFlowFormDataRequestModel search) {
        if (StringUtils.isEmpty(search.getWf_id())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "流程id不能为空");
        }
        //设置参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=form.data", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        return JSONObject.parseObject(body.get("queryObj").toString());
    }

    @Override
    public void cancel(WorkFlowCancelRequestModel search) {
        //设置参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=workflow.cancel", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }
    }

    @Override
    public void download(int wf_id) {
        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(new Gson().toJson(""), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> res = restTemplate.exchange(BMP_URL + "?cmd=printfile.download&wf_id={1}", HttpMethod.GET, requestEntity, byte[].class, wf_id);

        //todo
    }

    @Override
    public JSONObject taskApproval(WorkFlowTaskApprovalRequestModel search) {
        //添加参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.approval", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());
        if ("false".equals(body.get("success").toString())) {
            throw new ServiceException(ResultCode.BAD_REQUEST, body.getString("msgDesc"));
        }

        return JSONObject.parseObject(body.get("queryObj").toString());
    }

    @Override
    public JSONObject formUpdate(WorkFlowFormUpdateRequestModel model) {
        //todo

        return null;
    }

    @Override
    public PageOb<WorkFlowTodoResponseModel> getTodo(WorkFlowTodoRequestModel search, Pageable pageable) {
        //添加参数
        JSONObject param = (JSONObject) JSONObject.toJSON(search);
        param.put("page", pageable.getPageNumber() + 1);
        param.put("pageSize", pageable.getPageSize());

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.assigns", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());

        List<WorkFlowTodoResponseModel> list = JSONArray.parseArray(body.get("info").toString(), WorkFlowTodoResponseModel.class);
        long total = Long.valueOf(body.get("total").toString());

        return new PageOb(pageable.getPageNumber() + 1, pageable.getPageSize(), total, list);
    }

    @Override
    public List<WorkFlowTodoResponseModel> getTodoAll(WorkFlowTodoRequestModel search) {
        JSONObject param = (JSONObject) JSONObject.toJSON(search);
        param.put("page", 1);
        param.put("pageSize", max);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.assigns", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());

        List<WorkFlowTodoResponseModel> totalList = JSONArray.parseArray(body.get("info").toString(), WorkFlowTodoResponseModel.class);
        Integer total = Integer.valueOf(body.get("total").toString());

        //总页数
        int totalPages;
        //计算多少页
        if (total % max == 0) {
            totalPages = total / max;
        } else {
            totalPages = total / max + 1;
        }

        for (int page = 2; page <= totalPages; page++) {
            totalList.addAll(recordWorkFlowTodo(param, page));
        }

        return totalList;
    }

    private List<WorkFlowTodoResponseModel> recordWorkFlowTodo(JSONObject param, int page) {
        param.put("page", page);

        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("api_token", getToken());

        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(BMP_URL + "?cmd=task.assigns", requestEntity, String.class);

        JSONObject body = JSON.parseObject(res.getBody());

        return JSONArray.parseArray(body.get("info").toString(), WorkFlowTodoResponseModel.class);
    }

    private JSONObject convertParam(Object object) {
        JSONObject param = new JSONObject();
        try {
            Class clazz = object.getClass();
            //获取object的所有属性
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                //遍历获取属性名
                String name = pd.getName();
                //获取属性的get方法
                Method readMethod = pd.getReadMethod();
                // 在search上调用get方法等同于获得search的属性值
                // 一致string类型
                Object value = readMethod.invoke(object);

                if (!StringUtils.isEmpty(value)) {
                    param.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}
