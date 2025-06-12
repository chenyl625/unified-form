package com.jfbrother.baseserver.utils;

import com.jfbrother.baseserver.enums.PinyinEnum;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenyl@jfbrother.com
 */
public class StringUtils extends org.springframework.util.StringUtils {
    /**
     * 默认分隔符(英文逗号)
     */
    private static final String DEFAULT_SEPARATOR = ",";
    /**
     * 去掉字符串两边的字符方法的最小字符串长度
     */
    private static final int REMOVE_BOTH_SIDES_MIN_LENGTH = 2;

    /**
     * 去掉字符串两边的字符
     *
     * @param str
     * @return
     */
    public static String removeBothSides(String str) {
        if (str == null || str.length() <= REMOVE_BOTH_SIDES_MIN_LENGTH) {
            return str;
        }
        return str.substring(1, str.length() - 1);
    }

    /**
     * 将list用指定字符分割拼接成一个完整的字符串
     *
     * @param list
     * @param separator
     * @return
     */
    @Deprecated
    public static String join(List<String> list, String separator) {
        return list.stream().filter(item -> !StringUtils.isEmpty(item)).collect(Collectors.joining(separator));
    }

    /**
     * 拼接字符串
     *
     * @param separator
     * @param list
     * @return
     */
    public static String join(String separator, List<String> list) {
        return join(list, separator);
    }

    /**
     * 拼接字符串
     *
     * @param separator
     * @param strs
     * @return
     */
    public static String join(String separator, String... strs) {
        return join(separator, Arrays.asList(strs));
    }

    /**
     * 将list用英文逗号分割拼接成一个完整的字符串
     *
     * @param list
     * @return
     */
    public static String join(List list) {
        return join(list, DEFAULT_SEPARATOR);
    }

    /**
     * 判断是否是绝对路径
     *
     * @param path
     * @return
     */
    public static boolean isAbsolutePath(String path) {
        //linux系统的绝对路径使用"/开头"
        //或者windows的路径中包含冒号
        if (Paths.get(path).toAbsolutePath().equals(path)) {
            return true;
        }
        return false;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        return (str != null & str.length() > 1) ? str.substring(0, 1).toLowerCase() + str.substring(1) : "";
    }

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }


    /**
     * 将序号转化为指定位数的单号
     *
     * @param value  序号
     * @param length 位数
     * @return 单号
     */
    public static String convertNo(Long value, int length) {
        StringBuffer sb = new StringBuffer();
        String val = value + "";

        if (length < val.length()) {
            return null;
        }
        for (int i = 0; i < length - val.length(); i++) {
            sb.append(0);
        }
        sb.append(value);

        return sb.toString();
    }

    /**
     * 汉字转拼音
     *
     * @param text 准备转换的汉字
     * @param type 类型
     * @return
     */
    public static String convertPinyin(String text, PinyinEnum type) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        String result = "";
        //姓名全拼
        String pyName = "";
        //姓名首字母拼音
        String shouzimuName = "";
        char[] namec = text.toCharArray();
        for (int i = 0; i < namec.length; i++) {
            //判断是否存在非中文字符
            if (Character.toString(namec[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] py = PinyinHelper.toHanyuPinyinStringArray(namec[i], defaultFormat);
                    pyName += py[0];
                    shouzimuName += py[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            } else {
                //若存在则直接拼上去
                pyName += Character.toString(namec[i]);
            }

        }
        if (type == PinyinEnum.FULL) {
            result = pyName;
        } else if (type == PinyinEnum.ACRONYM) {
            result = shouzimuName;
        }
        return result;
    }

    /**
     * 驼峰式，转下划线
     *
     * @param camelCaseStr
     * @return
     */
    public static String camelCaseToUnderline(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        // 将驼峰字符串转换成数组
        char[] charArray = camelCaseStr.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
