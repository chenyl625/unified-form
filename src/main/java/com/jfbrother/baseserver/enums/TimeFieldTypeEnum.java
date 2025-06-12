package com.jfbrother.baseserver.enums;

/**
 * 时间字段类型枚举类
 */
public enum TimeFieldTypeEnum {
    DATE("date"),
    TIME("time"),
    YEAR("year"),
    DATETIME("datetime"),
    TIMESTAMP("timestamp");

    private String name;

    TimeFieldTypeEnum(String name) {
        this.name = name;
    }

    public static TimeFieldTypeEnum getByName(String name) {
        for (TimeFieldTypeEnum enums : TimeFieldTypeEnum.values()) {
            if (name.equals(enums.getName())) {
                return enums;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

}
