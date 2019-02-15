package com.zh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hahaha
 * @date 2018-08-06 17:02
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean enable;
    @Value("${spring.profiles.active}")
    private String profiles;

    @Bean
    public Docket api() {
        //swagger 增加头部信息token用于验证登陆
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //name表示名称，description表示描述
        ticketPar.name("token")
                .description("用于校验token")
                .required(true)
                .modelRef(new ModelRef("string")).parameterType("header")
                //required表示是否必填，defaultvalue表示默认值
                //.defaultValue()
                .build();
        //添加完此处一定要把下边的带***的也加上否则不生效
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zh.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(profiles.toUpperCase() + "环境" + "\tAPI接口文档")
                .description("restful接口")
                .version("1.0")
                .build();
    }

}
