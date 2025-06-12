//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jfbrother.baseserver.enums;

public enum LoginTypeEnum {
    LOGIN_WEB(1),
    LOGIN_H5(2),
    LOGIN_WX_MINI(3),
    LOGIN_WX_H5(4),
    LOGIN_ANDROID(5),
    LOGIN_IPHONE(6),
    LOGIN_CAS(7),
    LOGIN_DINGTALK(8),
    LOGOUT(-1);

    private int type;

    private LoginTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static LoginTypeEnum find(int type) {
        LoginTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            LoginTypeEnum value = var1[var3];
            if (value.type == type) {
                return value;
            }
        }

        return null;
    }
}
