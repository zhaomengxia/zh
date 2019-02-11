package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.basic.Polder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 圩区 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
public interface PolderMapper extends BaseMapper<Polder> {

    IPage<Polder> getListPage(Page<Polder> page, @Param("keywords") String keywords);

    List<Polder> getListPage(@Param("keywords") String keywords);
}
