package com.jfbrother.baseserver.enums;

/**
 * 是否枚举通用类
 *
 * @author: chenh@jfbrother.com 2020-09-24
 **/
public enum BooleanEnum {
    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");


    private int status;
    private String describe;

    BooleanEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public static BooleanEnum getByStatus(int status) {
        for (BooleanEnum enums : BooleanEnum.values()) {
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
