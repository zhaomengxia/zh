package com.zh.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * mybatis-plus 相关配置类
 *
 * @author  hahaha
 * @date 2018-08-05 11:07

 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * @author  hahaha
     * @date 2018/8/5 17:08
     * @Description 配置mapper接口扫描
     **/
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.zh.mapper");
        return scannerConfigurer;
    }

    /**
     * @author  hahaha
     * @date 2018/8/5 17:09
     * @Description 逻辑删除插件
     **/
    @Bean("logicInjector")
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * @author  hahaha
     * @date 2018/8/5 17:09
     * @Description 分页插件
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * @author  hahaha
     * @date 2018/8/16 10:50
     * @Description 乐观锁插件 实体类需要增加@Version注解
     **/
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * @author  hahaha
     * @date 2018/8/5 17:10
     * @Description 性能分析拦截器，不建议生产环境使用
     **/
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor().setFormat(true).setMaxTime(1500000);
    }


}
