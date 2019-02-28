package com.zh;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/2/28 19:38
 */
@Component
public class TestObject {
    @Around("com.zh.service.test2.impl.ZUserServiceImpl(exportUser(HttpServletResponse httpServletResponse))")
    public Object testObject(ProceedingJoinPoint jp) throws Throwable {
        System.out.print(jp.getSignature()+"开始执行");
        Object v=jp.proceed();
        System.out.print(jp.getSignature()+"执行成功");
        return v;
    }
}
