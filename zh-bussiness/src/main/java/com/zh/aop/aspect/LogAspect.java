package com.zh.aop.aspect;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.zh.entity.log.ActEvtLog;
import com.zh.entity.test2.ZRoles;
import com.zh.entity.test2.ZUser;
import com.zh.security.util.SecurityUtil;
import com.zh.service.log.ActEvtLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.zh.aop.annotation.Log;
import javax.annotation.Resource;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/26 14:37
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Resource
    private ActEvtLogService actEvtLogService;
    private LogAspect(){}

    /**
     * 设置切点log为注解方法
     */
    @Pointcut("@annotation(com.zh.aop.annotation.Log)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        //本方法返回值
        Object result;
        //获得方法描述
        MethodSignature methodSignature= (MethodSignature) proceedingJoinPoint.getSignature();
        StringBuffer desc=new StringBuffer(methodSignature.getMethod().getAnnotation(Log.class).desc());
        //获取当前登录用户
        ZUser zUser= (ZUser) SecurityUtil.getCurrentUserInfo();
        //获取当前登录用户ip
        String ip=SecurityUtil.getCurrentUserUrl();
        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            desc.append("出现异常，").append(throwable.getMessage());
            log.error(desc.toString());
            throw throwable;
            } finally {
               long end=System.currentTimeMillis();
               //启动一个进程进行日志保存
            ThreadUtil.execute(()->{
                actEvtLogService.save(ActEvtLog.builder()
                .operator(zUser.getName())
                .operatorRole(zUser.getRoles().stream().map(ZRoles::getRoleName).collect(Collectors.joining(StrUtil.COMMA)))
                .operateTime(Instant.now().toEpochMilli())
                .operateActive(desc.toString())
                 .executeTime(end-start)
                    .operatorIp(ip)
                .build());
            });
            }
        return result;
        }

}
