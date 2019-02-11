package com.zh.mapper.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 数据库表create_time update_time字段自动填充
 *
 * @author  赵梦霞
 * @date 2018-08-05 18:04

 **/
@Component
public class CreateUpdateMetaObjectHandler implements MetaObjectHandler {
    /**
     * <p>
     * 插入元对象字段填充
     * 包括逻辑删除字段 deleted createTime updateTime
     * </p>
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Boolean deleted = (Boolean) getFieldValByName("deleted", metaObject);
        LocalDateTime createTime = (LocalDateTime) getFieldValByName("createTime", metaObject);
        LocalDateTime updateTime = (LocalDateTime) getFieldValByName("updateTime", metaObject);
        if (deleted == null) {
            setFieldValByName("deleted", false, metaObject);
        }
        if (createTime == null) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        if (updateTime == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime updateTime = (LocalDateTime) getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
