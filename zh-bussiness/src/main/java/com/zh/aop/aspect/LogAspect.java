package com.zh.aop.aspect;

import cn.hutool.core.util.StrUtil;
import com.zh.aop.annotation.Log;
import com.zh.entity.log.OperateLog;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUser;
import com.zh.security.util.SecurityUtil;
import com.zh.service.log.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * 日志切面
 *
 * @author  赵梦霞
 * @since 2018-12-17 16:07

 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Resource
    private OperateLogService operateLogService;

    private LogAspect() {
    }

    /**
     * 切点Log注解方法
     */
    @Pointcut("@annotation(com.zh.aop.annotation.Log)")
    public void pointCut() {
    }

    /**
     * @author  赵梦霞
     * @Description 记录操作日志、方法执行时间
     * @since 2018/12/17 16:20
     **/
    @Around("pointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        //获取当前登陆信息
        SysUser sysUser = (SysUser) SecurityUtil.getCurrentUserInfo();

        //获取当前登陆用户ip
        String ip = SecurityUtil.getCurrentUserUrl();
        //获取方法描述
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String desc = signature.getMethod().getAnnotation(Log.class).desc();
        Object result;

        OperateLog operateLog = OperateLog.builder()
                .operator(sysUser.getName())
                //多角色用","隔开
                .operateRole(sysUser.getRoles().parallelStream().map(SysRoles::getRoleName).collect(Collectors.joining(StrUtil.COMMA)))
                .operateTime(Instant.now().toEpochMilli())
                .operatorIp(ip)
                .build();
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("异常操作");
            //异常信息存储
            desc = desc + "出现异常，" + throwable.getMessage();
            throw throwable;
        } finally {
            operateLog.setOperateActive(desc);
            operateLog.setExecuteTime(System.currentTimeMillis() - start);
            operateLogService.save(operateLog);
        }

        return result;
    }

}
