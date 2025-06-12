package com.jfbrother.baseserver.enums;

/**
 * 接口类型枚举类
 *
 * @author: chenyl@jfbrother.com 2022-09-07
 **/
public enum ConnTypeEnum {
    AUTO_REQUEST(1, "自动获取"),
    MANUAL_REQUEST(2, "手动获取");

    private Integer status;
    private String describe;

    ConnTypeEnum(Integer status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public static ConnTypeEnum getByStatus(Integer status) {
        for (ConnTypeEnum enums : ConnTypeEnum.values()) {
            if (enums.getStatus().equals(status)) {
                return enums;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return this.status;
    }
}
