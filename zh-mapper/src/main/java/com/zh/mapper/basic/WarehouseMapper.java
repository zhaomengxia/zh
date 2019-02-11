package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.basic.Warehouse;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {
    IPage<Warehouse> selectAllByMessage(Page<Warehouse> warehousePage, @Param("keywords") String keywords);
}
