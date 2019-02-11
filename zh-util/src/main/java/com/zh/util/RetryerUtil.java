package com.zh.util;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * guava retryer重试规则设置帮助类
 *
 * @author  赵梦霞
 * @since  2018-08-22 14:17

 **/
public class RetryerUtil {


    private RetryerUtil(){}

    /**
     * @author  赵梦霞
     * @date 2018/7/30 14:22
     * @param needRetry 需要重试的返回结果
     * @param sleepTime 间隔时间
     * @param timeUnit 时间单位
     * @param attemptNumber 重试次数
     *
     * @Description
     **/
    public static <T>Retryer<T> getRetryer(Object needRetry, int sleepTime, TimeUnit timeUnit, int attemptNumber){

        return RetryerBuilder.<T>newBuilder()
                .retryIfException()
                .retryIfResult(s -> Objects.equals(s, needRetry))
                //重调策略
                .withWaitStrategy(WaitStrategies.fixedWait(sleepTime, timeUnit))
                //尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(attemptNumber))
                .build();
    }

}
