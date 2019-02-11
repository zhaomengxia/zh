package com.zh.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 相关配置类
 *
 * @author  赵梦霞
 * @date 2018-08-05 11:07

 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * @author  赵梦霞
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
     * @author  赵梦霞
     * @date 2018/8/5 17:09
     * @Description 逻辑删除插件
     **/
    @Bean("logicInjector")
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
