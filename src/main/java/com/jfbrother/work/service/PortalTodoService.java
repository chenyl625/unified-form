package com.jfbrother.work.service;

import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.PortalToDoModel;
import com.jfbrother.work.model.PortalToDoSynModel;

public interface PortalTodoService extends BaseService {
    /**
     * 更新事务实例的状态
     * @param model
     */
    void synToPoral(PortalToDoSynModel model);

    /**
     * 插入待办
     * @param model
     */
    void pushToDoPoral(PortalToDoModel model);

    void batchSyn();

}
