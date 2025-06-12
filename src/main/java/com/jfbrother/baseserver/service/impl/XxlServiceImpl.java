package com.jfbrother.baseserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.service.XxlService;
import com.jfbrother.baseserver.xxl.XxlApi;
import com.jfbrother.baseserver.xxl.model.xxl.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenyl
 */
@Service
@Slf4j
public class XxlServiceImpl implements XxlService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private XxlApi xxlApi;

    @Value("${xxl.job.login.username:}")
    private String xxlUsername;
    @Value("${xxl.job.login.password:}")
    private String xxlPassword;

    @Override
    public boolean login(String username, String password) {
        XxlLoginModel model = new XxlLoginModel();
        model.setUserName(username);
        model.setPassword(password);
        try {
            XxlResultModel resultModel = xxlApi.login(username, password);
            log.debug(resultModel.toString());
            if (resultModel.getCode() == 500) {
                log.error("xxl login is error: {}", resultModel.getMsg());
                return false;
            } else if (resultModel.getCode() == 200) {
                log.trace("xxl login is success.");
                return true;
            } else {
                log.error("xxl login is error.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int create(int jobGroup, String jobDesc
            , ExecutorRouteStrategyEnum executorRouteStrategy
            , String cron
            , String jobHandler
            , ExecutorBlockStrategyEnum executorBlockStrategy
            , int executorTimeout
            , int executorFailRetryCount
            , String author
            , String alarmEmail
            , String executorParam
    ) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        XxlAddTaskModel model = new XxlAddTaskModel();

        model.setJobGroup(jobGroup);
        model.setJobDesc(jobDesc);
        model.setExecutorRouteStrategy(executorRouteStrategy.name());
        model.setCronGenDisplay(cron);
        model.setJobCron(cron);
        model.setGlueType("BEAN");
        model.setExecutorHandler(jobHandler);
        model.setExecutorBlockStrategy(executorBlockStrategy.name());
        model.setExecutorTimeout(executorTimeout);
        model.setExecutorFailRetryCount(executorFailRetryCount);
        model.setAuthor(author);
        model.setAlarmEmail(alarmEmail);
        model.setExecutorParam(executorParam);
        model.setGlueRemark("我是备注");
        Map<String, Object> data = JSONObject.parseObject(JSONObject.toJSONString(model)).getInnerMap();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        data.forEach((k, v) -> {
            if (v != null) {
                builder.addFormDataPart(k, v + "");
            }
        });
        RequestBody requestBody = builder.build();

        try {
            XxlResultModel resultModel = xxlApi.create(requestBody);

            //返回id
            return Integer.parseInt(resultModel.getContent());
        } catch (Exception e) {
            log.debug("未登录");
            return -1;
        }
    }

    @Override
    public boolean createAndStart(int jobGroup, String jobDesc, ExecutorRouteStrategyEnum executorRouteStrategy, String cron, String jobHandler, ExecutorBlockStrategyEnum executorBlockStrategy, int executorTimeout, int executorFailRetryCount, String author, String alarmEmail, String executorParam) {
        int id = this.create(jobGroup, jobDesc, executorRouteStrategy, cron, jobHandler, executorBlockStrategy, executorTimeout, executorFailRetryCount, author, alarmEmail, executorParam);
        if (id != -1) {
            return this.start(id);
        }
        return false;
    }

    @Override
    public boolean update(int id, int jobGroup, String jobDesc, ExecutorRouteStrategyEnum executorRouteStrategy, String cron, String jobHandler, ExecutorBlockStrategyEnum executorBlockStrategy, int executorTimeout, int executorFailRetryCount, String author, String alarmEmail, String executorParam) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        XxlAddTaskModel model = new XxlAddTaskModel();

        model.setJobGroup(jobGroup);
        model.setJobDesc(jobDesc);
        model.setExecutorRouteStrategy(executorRouteStrategy.name());
        model.setCronGenDisplay(cron);
        model.setJobCron(cron);
        model.setGlueType("BEAN");
        model.setExecutorHandler(jobHandler);
        model.setExecutorBlockStrategy(executorBlockStrategy.name());
        model.setExecutorTimeout(executorTimeout);
        model.setExecutorFailRetryCount(executorFailRetryCount);
        model.setAuthor(author);
        model.setAlarmEmail(alarmEmail);
        model.setExecutorParam(executorParam);
        model.setGlueRemark("我是备注");
        Map<String, Object> data = JSONObject.parseObject(JSONObject.toJSONString(model)).getInnerMap();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        data.forEach((k, v) -> {
            if (v != null) {
                builder.addFormDataPart(k, v + "");
            }
        });

        //更新操作需要id
        builder.addFormDataPart("id", id + "");

        RequestBody requestBody = builder.build();

        try {
            XxlResultModel resultModel = xxlApi.update(requestBody);

            return resultModel.getCode() == 200;
        } catch (Exception e) {
            log.debug("未登录");
            return false;
        }
    }

    @Override
    public boolean start(int id) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        XxlResultModel result = xxlApi.start(id);
        return result.getCode() == 200;
    }

    @Override
    public boolean stop(int id) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        XxlResultModel result = xxlApi.stop(id);
        return result.getCode() == 200;
    }

    @Override
    public boolean remove(int id) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        XxlResultModel result = xxlApi.remove(id);
        return result.getCode() == 200;
    }

    @Override
    public boolean remove(int jobGroup, String jobDesc) {
        return remove(jobGroup, jobDesc, false);
    }

    @Override
    public boolean removeAll(int jobGroup, String jobDesc) {
        return remove(jobGroup, jobDesc, true);
    }

    @Override
    public XxlFindListModel find(XxlFindSearchModel searchModel) {
        //先进行登录操作
        login(xxlUsername, xxlPassword);

        try {
            Map<String, Object> data = JSONObject.parseObject(JSONObject.toJSONString(searchModel)).getInnerMap();

            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            data.forEach((k, v) -> {
                if (v != null) {
                    if ("triggerStatus".equals(k)) {
                        builder.addFormDataPart(k, searchModel.getTriggerStatus().getValue() + "");
                    } else {
                        builder.addFormDataPart(k, v + "");
                    }
                }
            });

            return xxlApi.find(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new XxlFindListModel();
    }

    @Override
    public String createCronByTime(Long time) {
        return createCronByTime(new Date(time));
    }

    @Override
    public String createCronByTime(Date time) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(time, dateFormat);
    }

    public static String formatDateByPattern(Date time, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (time != null) {
            formatTimeStr = sdf.format(time);
        }
        return formatTimeStr;
    }

    /**
     * 删除任务
     *
     * @param jobGroup 执行器编号
     * @param jobDesc  任务描述
     * @param focus    如果匹配结果有2条及以上，是否强制删除
     * @return
     */
    private boolean remove(int jobGroup, String jobDesc, boolean focus) {
        List<XxlTaskModel> all = new ArrayList<>();
        //初始化查询条件
        XxlFindSearchModel searchModel = XxlFindSearchModel.XxlFindSearchModelBuilder.builder()
                //按任务描述进行删除
                .jobDesc(jobDesc)
                .jobGroup(jobGroup)
                .build();

        //循环遍历所有的页
        while (true) {
            XxlFindListModel xxlFindListModel = find(searchModel);
            List<XxlTaskModel> data = xxlFindListModel.getData();
            //加入全部数据
            all.addAll(data);

            //当没有数据后，跳出循环
            if (data.size() == 0) {
                break;
            }

            //翻页（开始索引为之前的索引+页面偏移）
            searchModel.setStart(searchModel.getStart() + searchModel.getLength());
        }

        if (all != null && !all.isEmpty()) {
            //判断是否需要强制删除
            if (all.size() > 1 && !focus) {
                log.warn("xxl任务描述【{}】大于1条", jobDesc);
                return false;
            }
            //遍历删除
            all.forEach(xxl -> {
                remove(xxl.getId());
            });
            return true;
        } else {
            log.warn("xxl任务描述【{}】不存在", jobDesc);
        }

        return false;
    }
}
