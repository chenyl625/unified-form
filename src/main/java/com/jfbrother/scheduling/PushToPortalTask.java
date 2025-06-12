package com.jfbrother.scheduling;

import com.jfbrother.baseserver.enums.PortalToDoStatusEnum;
import com.jfbrother.baseserver.enums.WorkFlowTaskStatusEnum;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.search.CustomFormFillModelSearch;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.work.model.PortalToDoModel;
import com.jfbrother.work.model.PortalToDoSynModel;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.request.WorkFlowRequestModel;
import com.jfbrother.work.model.request.WorkFlowTaskRequestModel;
import com.jfbrother.work.model.request.WorkFlowTodoRequestModel;
import com.jfbrother.work.model.response.WorkFlowResponseModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.service.PortalTodoService;
import com.jfbrother.work.service.WorkFlowService;
import com.jfbrother.work.service.WorkFlowTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@Slf4j
public class PushToPortalTask {
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

//每2分钟执行一次 0 0/2 * * * ?
    //@Scheduled(cron = "0 36 17 * * ?")
    public void batchSyn(){
        /**
         * 步骤：1.根据流程id查询，流程的状态
         * 2.如果流程已完成，则更新校务服务的事项的状态，更新待办事项的状态为完成，查询本地库中是否流程也已完成，如未完成则更更新本地库流程状态和环节，
         * 3.如果流程未完成，则插入校务服务的待办事项的待办,并更新待办事项的状态为完成，查询本地库中是否流程也已完成，如未完成则更更新本地库流程状态和环节
         *
         * 1.根据流程id查询，流程的状态；2.更新待办事项的状态为完成,查询本地库中是否流程也已完成
         */
//        Object entity = redisService.get("portal_entity");
//        if(entity!=null){
//            PortalToDoModel toDoModel = (PortalToDoModel) entity;
//
//        }




    }
}
