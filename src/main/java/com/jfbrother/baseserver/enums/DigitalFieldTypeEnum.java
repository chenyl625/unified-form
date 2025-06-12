package com.jfbrother.baseserver.enums;

/**
 * 数字字段类型枚举类
 */
public enum DigitalFieldTypeEnum {
    TINYINT("tinyint"),
    SMALLINT("smallint"),
    MEDIUMINT("smallint"),
    INT("int"),
    INTEGER("integer"),
    BIGINT("bigint"),
    FLOAT("float"),
    DOUBLE("double"),
    DECIMAL("decimal");

    private String name;

    DigitalFieldTypeEnum(String name) {
        this.name = name;
    }

    public static DigitalFieldTypeEnum getByName(String name) {
        for (DigitalFieldTypeEnum enums : DigitalFieldTypeEnum.values()) {
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
