package com.zh.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author  hahaha
 * @since 2018-12-05 14:54

 **/
@SuppressWarnings("unused")
public class DateUtil {

    private DateUtil() {
    }

    /**
     * @param timestamp 时间戳
     * @author  hahaha
     * @Description 时间戳转为LocalDateTime对象
     * @since 2018/12/5 14:57
     **/
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.of("+8"));
    }

    /**
     * @param date 日期对象
     * @author  hahaha
     * @Description Date 转为 LocalDateTime
     * @since 2018/12/5 15:31
     **/
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    /**
     * @param patten        格式
     * @param localDateTime 需格式化日期对象
     * @author  hahaha
     * @since 2018/12/24 11:08
     **/
    public static String parse(String patten, LocalDateTime localDateTime) {
        if(localDateTime == null){
            return "-";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
        return localDateTime.format(formatter);
    }

    /**
     * @param localDateTime java8 日期对象
     * @author  hahaha
     * @Description LocalDateTime 转为 Date
     * @since 2018/12/5 15:32
     **/
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * @author  hahaha
     * @date 2018/6/10 13:20
     * @Description 获取今日零点
     **/
    public static Instant getTodayZero() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8"));
    }

}
