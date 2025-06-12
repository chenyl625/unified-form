package com.jfbrother.baseserver.enums;

/**
 * 流程环节状态枚举类
 */
public enum WorkFlowTaskStatusEnum {
    TO_DO("待办"),
    ONGOING("进行中"),
    COMPLETED("已完成"),
    CANCELLED("已取消"),
    PENDING("待审");

    private String name;

    WorkFlowTaskStatusEnum(String name) {
        this.name = name;
    }

    public static WorkFlowTaskStatusEnum getByName(String name) {
        for (WorkFlowTaskStatusEnum enums : WorkFlowTaskStatusEnum.values()) {
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
