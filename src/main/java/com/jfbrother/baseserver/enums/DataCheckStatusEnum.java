package com.jfbrother.baseserver.enums;

/**
 * 数据纠错审核状态枚举类
 *
 * @author: chenyl@jfbrother.com 2022-07-20
 **/
public enum DataCheckStatusEnum {
    STAGING(0, "暂存"),
    AUDIT(1, "审核中"),
    PASS(2, "通过"),
    REFUSE(3, "拒绝");

    private int status;
    private String describe;

    DataCheckStatusEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public static DataCheckStatusEnum getByStatus(int status) {
        for (DataCheckStatusEnum enums : DataCheckStatusEnum.values()) {
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
