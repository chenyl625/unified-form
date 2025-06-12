package com.jfbrother.baseserver.enums;

/**
 * 字段数据来源枚举类
 *
 * @author: chenyl@jfbrother.com 2022-07-20
 **/
public enum FieldSourceEnum {
    PERSONNEL(1, "人事系统"),
    STUDENT_WORKER(2, "学工系统");

    private int status;
    private String describe;

    FieldSourceEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public static FieldSourceEnum getByStatus(int status) {
        for (FieldSourceEnum enums : FieldSourceEnum.values()) {
            if (enums.getStatus() == status) {
                return enums;
            }
        }
        return null;
    }

    public int getStatus() {
        return this.status;
    }
}
