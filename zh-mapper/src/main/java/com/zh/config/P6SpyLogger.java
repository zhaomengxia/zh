package com.zh.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.zh.util.sql.SQLFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * p6spy格式化
 *
 * @author  hahaha
 * @since 2018-11-19 9:35

 **/
public class P6SpyLogger implements MessageFormattingStrategy {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? this.formatter.format(LocalDateTime.now()) + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "\n " + new SQLFormatter().format(sql) + ";" : "";
    }
}