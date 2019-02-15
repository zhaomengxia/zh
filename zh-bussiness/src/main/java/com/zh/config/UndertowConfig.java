package com.zh.config;

import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author hahaha
 * @since 2018-09-21 14:21
 * undertow 容器配置类
 **/
@Configuration
public class UndertowConfig {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers(undertowBuilderCustomizer());
        //配置错误页面
        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/authentication/forbidden"), new ErrorPage(HttpStatus.NOT_FOUND, "/unknown"));
        factory.addBuilderCustomizers();
        return factory;
    }

    @Bean
    public UndertowBuilderCustomizer undertowBuilderCustomizer() {
        return customizer -> {
            //设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程
            customizer.setIoThreads(4);
            // 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载
            customizer.setWorkerThreads(256);
            //每个区分配的buffer数量 , 所以pool的大小是buffer-size * buffers-per-region
            customizer.setBufferSize(1024);
            //是否分配的直接内存
            customizer.setDirectBuffers(true);
        };
    }

}