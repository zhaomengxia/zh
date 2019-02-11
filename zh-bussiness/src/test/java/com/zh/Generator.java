package com.zh;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 * 根据本机环境修改参数
 * <p>
 * 勿提交此文件
 *
 * @author  赵梦霞
 * @date 2018-08-06 14:34

 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Generator {

    @Resource
    private DynamicDataSourceProperties dynamicDataSourceProperties;


    private DataSourceProperty sqlserver;


    @Before
    public void init() {
        sqlserver = dynamicDataSourceProperties.getDatasource().get("sqlserver");
    }

    //项目根路径根据实际路径修改
    private static String PROJECT_PATH = "E:\\code\\zh";
    private static String AUTHOR = " 赵梦霞 ";

    //代码生成 根据表生成对应实体类、mapper接口、mapper xml、service以及controller
    @Test
    public void mybatisGenerator() {

        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)//是否支持AR模式
                .setOpen(false) //是否打开输出目录
                .setAuthor(AUTHOR)//设置作者信息
                .setOutputDir(".\\src\\main\\java")//代码生成路径
                .setFileOverride(true)//是否覆盖文件
                .setIdType(IdType.ID_WORKER)//主键策略
                .setDateType(DateType.TIME_PACK) //时间类型对应策略
                .setServiceName("%sService")//设置生成的service接口的名字首字母是否为I 默认带I
                .setSwagger2(true)//是否需要swagger注解配置
                .setBaseResultMap(true)//生成基本resultMap
                .setBaseColumnList(true);//生成基本列

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        //sqlserver 数据表生成
        dataSourceConfig.setDbType(DbType.SQL_SERVER)
                .setDriverName(sqlserver.getDriverClassName())
                .setUrl(sqlserver.getUrl())
                .setUsername(sqlserver.getUsername())
                .setPassword(sqlserver.getPassword())
        ;
        //策略配置  设置is_deleted create_time update_time 自动填充
        List<TableFill> fills = Lists.newArrayList();
        fills.add(new TableFill("create_time", FieldFill.INSERT));
        fills.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        fills.add(new TableFill("is_deleted", FieldFill.INSERT));
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//全局大写
                .setEntityLombokModel(true)//创建Lombok实体类
                .setRestControllerStyle(true)//是否创建RestController
                .setLogicDeleteFieldName("is_deleted")//逻辑删除字段
                .setEntityBooleanColumnRemoveIsPrefix(true)//Boolean类型字段是否移除is前缀（默认 false）
                .setVersionFieldName("version")//乐观锁字段 如果需要开启此配置
                .setTableFillList(fills)//表填充字段
                .setEntityTableFieldAnnotationEnable(true)//是否生成实体时，生成字段注解
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略 下划线转驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSkipView(true)//是否跳过视图
                .setTablePrefix("")//表前缀
                .setInclude("exception_job_attachment");//生成的表
        //包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        String packgeName = StrUtil.DOT + "task"; //自定义子包名称 分组管理
        packageConfig.setParent("com.zh") //父包名
                .setModuleName("")
                .setMapper("mapper" + packgeName) //mapper包名
                .setService("service" + packgeName) //Service包名
                .setServiceImpl("service" + packgeName + ".impl")
                .setController("controller" + packgeName) //controller 包名
                .setEntity("entity" + packgeName) //实体类包名
        ;

        // 注入自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        //entity 设置自定义实体类模板 解决idea不能识别@Accessors(chain = true)注解问题
        focList.add(new FileOutConfig("/templates/builderEntity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-entity\\src\\main\\java\\com\\zh\\entity\\" + packgeName.replace(".", "") + "\\" + tableInfo.getEntityName() + ".java";
            }
        });
        //mapper
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-mapper\\src\\main\\java\\com\\zh\\mapper\\" + packgeName.replace(".", "") + "\\" + tableInfo.getMapperName() + ".java";
            }
        });
        //service
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-service\\src\\main\\java\\com\\zh\\service\\" + packgeName.replace(".", "") + "\\" + tableInfo.getServiceName() + ".java";
            }
        });
        //serviceImpl
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-service\\src\\main\\java\\com\\zh\\service\\" + packgeName.replace(".", "") + "\\impl\\" + tableInfo.getServiceImplName() + ".java";
            }
        });
        //controller
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-bussiness\\src\\main\\java\\com\\zh\\controller\\" + packgeName.replace(".", "") + "\\" + tableInfo.getControllerName() + ".java";
            }
        });
        // 调整 xml 生成目录 至resource/mapper下
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "\\zh-mapper\\src\\main\\resources\\mapper\\" + packgeName.replace(".", "") + "\\" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 resources/mapper下
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setEntity(null);
        tc.setXml(null);
        tc.setMapper(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setController(null);
        mpg.setTemplate(tc);

        mpg.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        // 执行生成
        mpg.execute();

    }
}
