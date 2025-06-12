package com.jfbrother.baseserver.enums;

public enum PortalToDoStatusEnum {
    TO_BE_RECEIVED("TO_BE_RECEIVED", "待接收"),
    DOING("DOING", "办理中、已接收"),
    CANCELLED("CANCELLED", "已取消"),
    FINISHED_WITH_EXCEPTION("FINISHED_WITH_EXCEPTION", "异常结束"),
    DELETED("DELETED","已删除"),
    FINISHED("FINISHED", "已完成");

    private String status;
    private String describe;

    PortalToDoStatusEnum(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public String getStatus() {
        return this.status;
    }
}
