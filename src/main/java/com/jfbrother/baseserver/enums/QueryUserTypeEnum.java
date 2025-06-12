package com.jfbrother.baseserver.enums;

public enum QueryUserTypeEnum {
    STUDENT("student"),
    TEACHER("teacher");

    private String defaultType;

    QueryUserTypeEnum(String type) {
        this.defaultType = type;
    }

    @Override
    public String toString() {
        return defaultType;
    }
}
