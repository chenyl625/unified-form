package com.jfbrother.baseserver.utils;

import com.mysema.commons.lang.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * LocalDate工具类
 *
 * @author liuyz
 */
public class LocalDateUtils {
    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String timeToString(Long time, String format) {
        Assert.notNull(time, "time is null");
        // "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatString = DateTimeFormatter.ofPattern(format);
        return formatString.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 时间戳转LocalDate
     *
     * @param epochMilli 时间戳(毫秒)
     * @return
     */
    public static LocalDate LongToLocalDate(Long epochMilli) {
        return Instant.ofEpochMilli(epochMilli).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * LocalDate转时间戳(毫秒)
     *
     * @param localDate
     * @return
     */
    public static Long LocalDateToLong(LocalDate localDate) {
        return localDate.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 通过当前周期日获取下个周期日
     *
     * @param date       当前周期日
     * @param period     周期月份
     * @param dateNumber 日期号
     * @return
     */
    public static LocalDate getNextPeriodDate(LocalDate date, Integer period, Integer dateNumber) {
        LocalDate tempDate = date.plusMonths(period);
        return LocalDate.of(tempDate.getYear(), tempDate.getMonth(), dateNumber);
    }

    /**
     * 通过当前周期日的时间戳获取下个周期日
     *
     * @param epochMilli 当前周期日的时间戳(毫秒)
     * @param period
     * @param dateNumber
     * @return
     */
    public static LocalDate getNextPeriodDate(Long epochMilli, Integer period, Integer dateNumber) {
        return getNextPeriodDate(LongToLocalDate(epochMilli), period, dateNumber);
    }

    /**
     * 通过当前周期日获取下个周期日的时间戳(毫秒)
     *
     * @param date       当前周期日
     * @param period
     * @param dateNumber
     * @return
     */
    public static Long getNextPeriodDateToLong(LocalDate date, Integer period, Integer dateNumber) {
        LocalDate nextPeriodDate = getNextPeriodDate(date, period, dateNumber);
        return LocalDateToLong(nextPeriodDate);
    }

    /**
     * 通过当前周期日的时间戳获取下个周期日的时间戳(毫秒)
     *
     * @param epochMilli 当前周期日的时间戳(毫秒)
     * @param period
     * @param dateNumber
     * @return
     */
    public static Long getNextPeriodDateToLong(Long epochMilli, Integer period, Integer dateNumber) {
        LocalDate nextPeriodDate = getNextPeriodDate(LongToLocalDate(epochMilli), period, dateNumber);
        return LocalDateToLong(nextPeriodDate);
    }

    /**
     * 通过当前周期日获取上个周期日
     *
     * @param date       当前周期日
     * @param period
     * @param dateNumber
     * @return
     */
    public static LocalDate getLastPeriodDate(LocalDate date, Integer period, Integer dateNumber) {
        LocalDate tempDate = date.minusMonths(period);
        return LocalDate.of(tempDate.getYear(), tempDate.getMonth(), dateNumber);
    }

    /**
     * 通过当前周期日的时间戳获取上个周期日的时间戳(毫秒)
     *
     * @param epochMilli 当前周期日的时间戳(毫秒)
     * @param period
     * @param dateNumber
     * @return
     */
    public static Long getLastPeriodDateToLong(Long epochMilli, Integer period, Integer dateNumber) {
        LocalDate nextPeriodDate = getLastPeriodDate(LongToLocalDate(epochMilli), period, dateNumber);
        return LocalDateToLong(nextPeriodDate);
    }

    /**
     * LocalDateTime转时间戳(毫秒)
     *
     * @param localDateTime
     * @return
     */
    public static Long LocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 两个日期之间的天数
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getBetweenDays(LocalDate begin, LocalDate end) {
        return begin.until(end, ChronoUnit.DAYS);
    }

    /**
     * 根据指定的两个日期计算两个日期之间差几周
     *
     * @param begin
     * @param end
     * @return
     */
    public static Integer getBetweenWeeks(LocalDate begin, LocalDate end) {
        int beginWeekDay = begin.getDayOfWeek().getValue();
        long daysBetween = getBetweenDays(begin, end);
        long weeksBetween = daysBetween / 7;

        int offset = (daysBetween % 7 + beginWeekDay) > 7 ? 1 : 0;
        long weeks = offset + weeksBetween;

        return Integer.parseInt(String.valueOf(weeks));
    }

    /**
     * 时间戳转年月
     *
     * @param epochMilli
     * @return
     */
    public static YearMonth LongToYearMonth(Long epochMilli) {
        LocalDate localDate = LongToLocalDate(epochMilli);
        return YearMonth.of(localDate.getYear(), localDate.getMonthValue());
    }
}
