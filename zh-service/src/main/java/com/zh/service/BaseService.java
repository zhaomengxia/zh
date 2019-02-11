package com.zh.service;

import com.zh.exceptions.UnifiedException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * 通用方法 service
 *
 * @author  赵梦霞
 * @since 2019-01-07 14:54

 **/
public class BaseService {

    /**
     * @author  赵梦霞
     * @Description 判断时间之间间距
     * @since 2019/1/7 9:51
     **/
    protected boolean judgeTimeSection(Long start, Long end) {
        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault());
        LocalDateTime localDate2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault());
        long between = ChronoUnit.HOURS.between(localDate, localDate2);
        if (between <= 24) {
            return true;
        } else if (between <= 72) {
            return false;
        }
        throw new UnifiedException("时间区间错误，最多查询72小时内数据");
    }

}
