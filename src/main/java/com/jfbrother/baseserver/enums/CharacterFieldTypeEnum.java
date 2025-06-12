package com.jfbrother.baseserver.enums;

/**
 * 字符字段类型枚举类
 */
public enum CharacterFieldTypeEnum {
    CHAR("char"),
    VARCHAR("varchar"),
    TINYBLOB("tinyblob"),
    TINYTEXT("tinytext"),
    BLOB("blob"),
    TEXT("text"),
    MEDIUMBLOB("mediumblob"),
    MEDIUMTEXT("mediumtext"),
    LONGBLOB("longblob"),
    LONGTEXT("longtext");

    private String name;

    CharacterFieldTypeEnum(String name) {
        this.name = name;
    }

    public static CharacterFieldTypeEnum getByName(String name) {
        for (CharacterFieldTypeEnum enums : CharacterFieldTypeEnum.values()) {
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
