package com.jfbrother.work.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.enums.PortalToDoStatusEnum;
import com.jfbrother.baseserver.enums.WorkFlowTaskStatusEnum;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.search.CustomFormFillModelSearch;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.work.model.PortalToDoModel;
import com.jfbrother.work.model.PortalToDoSynModel;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.request.WorkFlowTaskRequestModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.service.PortalTodoService;
import com.jfbrother.work.service.WorkFlowService;
import com.jfbrother.work.service.WorkFlowTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 门户待办推送service实现
 *
 * @author chenyl@jfbrother.com 2022-10-09
 */
@Service
@Slf4j
public class PortalTodoServiceImpl implements PortalTodoService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CustomFormFillService customFormFillService;
    @Autowired
    private PortalTodoService portalTodoService;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private WorkFlowTaskService workFlowTaskService;
    public static final String baseuUrl="https://xwfw.zjbti.net.cn";
    public static final String appId="1574959809409581058";
    public static final String secrete="fzslwatfz8bzwssaysxk";
    @Override
    public void synToPoral(PortalToDoSynModel model) {
        String token = getToken();
        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("eco-oauth2-token", getToken());
        try {
            if(!com.jfbrother.baseserver.utils.StringUtils.isEmpty(model.getGotoUrl())){
                String encode = URLEncoder.encode(model.getGotoUrl(), "UTF-8");
                model.setGotoUrl(encode);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(model, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(baseuUrl + "/api/web-service/portal1/item/instance/sync", requestEntity, String.class);
        //JSONObject body = JSON.parseObject(res.getBody());
        String body = res.getBody();
        System.out.println("开始打印输出体:ok");
        System.out.println(body);
    }

    @Override
    public void pushToDoPoral(PortalToDoModel model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("eco-oauth2-token", getToken());
        HttpEntity<Object> requestEntity = new HttpEntity<>(model, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.postForEntity(baseuUrl + "/api/web-service/portal1/personal-todo/sync", requestEntity, String.class);
        //JSONObject body = JSON.parseObject(res.getBody());
        String body = res.getBody();
        System.out.println("开始打印输出体:ok");
        System.out.println(body);
    }


    public String getToken() {
        String token;

        Object obj = redisService.get(RedisKeyConstant.ECO_TOKEN);
        if (StringUtils.isEmpty(obj)) {
            token = createToken();

            //access_token的有效期20分钟
            redisService.set(RedisKeyConstant.ECO_TOKEN, token,  20 * 60L);
        } else {
            token = String.valueOf(obj);
        }

        return token;
    }
    public String createToken() {
        String token = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(baseuUrl + "/api/oauth2/access-token?appid={1}&secret={2}", String.class, appId, secrete);
        String body = res.getBody();
        if (!com.jfbrother.baseserver.utils.StringUtils.isEmpty(body)) {
            token = body;
        }

        return token;
    }

    @Override
    public void batchSyn() {
        List<CustomFormFillModelExtend> collect = customFormFillService.get(new CustomFormFillModelSearch() {{
            setFlowStatus("进行中");
        }}, Sort.by("createTime").descending()).stream().filter(item -> item.getFlowId() != null).collect(Collectors.toList());
        for (CustomFormFillModelExtend extend : collect) {
            Integer wf_id = extend.getFlowId();
            //调用1.获取流程实例列表接口（workflow.list）
            List<WorkFlowTaskResponseModel> task = workFlowService.getTask(new WorkFlowTaskRequestModel() {
                {
                    setWf_id(wf_id);
                }
            });
            log.info("OA中获取到的流程环节列表:{}",task);
            List<WorkFlowTaskResponseModel> oaOnGoingFlowTasks =task.stream().filter(item -> item.getStatus().equals(WorkFlowTaskStatusEnum.TO_DO.getName())).collect(Collectors.toList());
            CustomFormFillModelExtend param = customFormFillService.getByFlowId(wf_id);
            List<WorkFlowTaskModelExtend> localOnGoingTaskList = workFlowTaskService.getByFillId(param.getId()).stream().filter(item -> item.getStatus().equals(WorkFlowTaskStatusEnum.TO_DO.getName())).collect(Collectors.toList());
            if(oaOnGoingFlowTasks.size()==0){
                if(localOnGoingTaskList.size()>0){
                    //先更新本地库环节
                    for (WorkFlowTaskResponseModel oaTask : task) {
                        for (WorkFlowTaskModelExtend taskModelExtend : localOnGoingTaskList) {
                            log.info("taskModelExtend:{}", taskModelExtend);
                            if (taskModelExtend.getTaskId() == oaTask.getId() && !oaTask.getStatus().equals(taskModelExtend.getStatus())) {
                                WorkFlowTaskModelParam taskParam = CopyUtils.copyBean(taskModelExtend, WorkFlowTaskModelParam.class);
                                taskParam.setStatus(oaTask.getStatus());
                                log.info("更新本地库中的环节状态：{}", taskModelExtend);
                                log.info("更新本地库中的环节状态：{}", taskParam);
                                workFlowTaskService.put(taskModelExtend.getId(),taskParam);
                            }
                        }
                    }
                }
                //当本地库未完成，OA的流程引擎已完成，则更新本地库的流程状态，不更新校务服务大厅
                //当本地库中环节也是全部完成时,则直接更新表单审核状态为“已完成”
                param.setFlowStatus("已完成");
                log.info("更新表单填报状态：{}", param.getId());
                customFormFillService.put(param.getId(), CopyUtils.copyBean(param, CustomFormFillModelParam.class));
            }

            if(oaOnGoingFlowTasks.size()==0&&localOnGoingTaskList.size()==0) {


            }else{
                //当本地库未完成，OA的流程引擎未完成，则查询本地库的环节和OA的流程引擎的环节，如一致则不更新；更新校务服务大厅
                //OA中的进行中环节和本地库中进行中的环节进行比较
                List<String> assigeeName = localOnGoingTaskList.stream().map(a -> a.getAssigeeName()).collect(Collectors.toList());
                List<WorkFlowTaskResponseModel> diffTask = oaOnGoingFlowTasks.stream().filter(o -> assigeeName.contains(o.getAssigeeName())).collect(Collectors.toList());
                boolean needUpdatePortal = oaOnGoingFlowTasks.size() == localOnGoingTaskList.size()&& oaOnGoingFlowTasks.size()== diffTask.size();
                if(!needUpdatePortal){
                    //OA中的进行中环节和本地库中进行中的环节不一致，则以OA中的进行中环节为准，进行数据更新
                    log.info("出问题啦");
                    //return;
                }
                log.info("开始往校务办事大厅同步数据：【{}】",oaOnGoingFlowTasks.size());
                String itemDomId = param.getRelease().getItemDomId();
                if(!com.jfbrother.baseserver.utils.StringUtils.isEmpty(itemDomId)){
                    //插入实例
                    PortalToDoSynModel synModel=new PortalToDoSynModel();
                    synModel.setCode(param.getId());
                    synModel.setItemDomId(itemDomId);
                    synModel.setAssignerSubject(param.getUserId());
                    synModel.setStatus(PortalToDoStatusEnum.DOING.getStatus());
                    synModel.setActualStartDateTime(param.getCreateTime());
                    log.info("开始往校务办事大厅插入实例数据：【{}】",synModel);
                    //portalTodoService.synToPoral(synModel);
                    //插入环节待办
                    PortalToDoModel toDoModel=new PortalToDoModel();
                    toDoModel.setInstanceCode(param.getId());
                    toDoModel.setItemDomId(itemDomId);
                    toDoModel.setAssignerSubject(param.getUserId());
                    List<String> list = oaOnGoingFlowTasks.stream().filter(item -> !com.jfbrother.baseserver.utils.StringUtils.isEmpty(item.getAssingeeAccount())).map(ob -> ob.getAssingeeAccount()).collect(Collectors.toList());
                    String[] assigner = list.toArray(new String[list.size()]);
                    toDoModel.setAssignerSubject(param.getUserId());
                    toDoModel.setAssigneeSubject(assigner);
                    toDoModel.setStartDateTimeMills(System.currentTimeMillis());
                    toDoModel.setStatus(PortalToDoStatusEnum.DOING.getStatus());
                    log.info("开始往校务办事大厅插入待办数据：【{}】",toDoModel);
                    //portalTodoService.pushToDoPoral(toDoModel);
                }
            }

        }
    }

    public static void main(String[] args) {
        try {
            String url = URLEncoder.encode("部门负责人审核", "UTF-8");
            System.out.println(url);
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String token = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(baseuUrl + "/api/oauth2/access-token?appid={1}&secret={2}", String.class, appId, secrete);
        String body = res.getBody();
        if (!com.jfbrother.baseserver.utils.StringUtils.isEmpty(body)) {
            token = body;
        }
        PortalToDoModel model=new PortalToDoModel();
        //PortalToDoSynModel model=new PortalToDoSynModel();
        model.setItemDomId("jf001");
        model.setInstanceCode("ff80808183cb81900183cb95c626000d");
       // model.setCode("ff80808183cb53730183cb54daf40000");
        model.setTodoId("169339_1308927_部门负责人审核");
        model.setStatus(PortalToDoStatusEnum.DOING.getStatus());
        model.setAssigneeSubject(new String[]{"31343115"});
        model.setStartDateTimeMills(1665563679473L);
        model.setEndDateTimeMills(1665567868358L);

//        model.setAssignerSubject("31343115");
//        model.setActualEndDateTime(1665567868358L);
//        model.setActualStartDateTime(1665563679473L);
        HttpHeaders headers = new HttpHeaders();
        headers.add("eco-oauth2-token", token);
        HttpEntity<Object> requestEntity = new HttpEntity<>(model, headers);
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> res2 = restTemplate2.postForEntity(baseuUrl + "/api/web-service/portal1/personal-todo/sync", requestEntity, String.class);
       // ResponseEntity<String> res2 = restTemplate.postForEntity(baseuUrl + "/api/web-service/portal1/item/instance/sync", requestEntity, String.class);
        //JSONObject body = JSON.parseObject(res.getBody());
        String body2 = res2.getBody();
        System.out.println("开始打印输出体:ok");
        System.out.println(body2);

    }
}
