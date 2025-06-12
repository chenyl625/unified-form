package com.jfbrother.baseserver.utils;

import com.jfbrother.baseserver.enums.DigitalFieldTypeEnum;

import java.util.Arrays;

/**
 * 表处理通用方法类
 */
public class TableFmtUtils {

    /**
     * 判断字段类型能否设置长度
     *
     * @param columnType
     * @return
     */
    public static boolean canSetLength(String columnType) {
        String[] arr = new String[]{"date", "datetime", "timestamp"
                , "text", "mediumtext", "longtext", "tinytext"
                , "blob", "mediumblob", "longblob", "tinyblob"};

        return Arrays.asList(arr).indexOf(columnType) == -1;
    }


    /**
     * 判断字段类型能否设置小数点
     *
     * @param columnType
     * @return
     */
    public static boolean canSetPoint(String columnType) {
        String[] arr = new String[]{DigitalFieldTypeEnum.DECIMAL.getName()
                , DigitalFieldTypeEnum.DOUBLE.getName()
                , DigitalFieldTypeEnum.FLOAT.getName()
        };

        return Arrays.asList(arr).indexOf(columnType) != -1;
    }

}
