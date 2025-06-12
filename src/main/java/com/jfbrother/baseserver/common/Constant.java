package com.jfbrother.baseserver.common;

/**
 * 系统公共常量定义
 *
 * @author chenyl
 */
public class Constant {
    /**
     * 更新操作时，需要忽略的字段
     */
    public final static String[] UPDATE_IGNORE_FIELDS = {"id", "createTime", "createUser"};

    /**
     * 用户更新操作时，需要忽略的字段
     */
    public final static String[] UPDATE_USER_IGNORE_FIELDS = {"id", "createTime", "createUser", "password"};

    /**
     * 删除操作时，用户传过来的ids的分隔符，当前使用英文逗号
     */
    public final static String REMOVE_IDS_SPLIT = ",";

    /**
     * 自定义表单字段
     */
    public final static String[] CUSTOM_FORM_FIELDS = {"id", "business_id", "sort_num", "status_num", "version_num", "is_delete", "createTime", "createUser", "update_time", "update_user"};

}
