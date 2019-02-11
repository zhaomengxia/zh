package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.entity.basic.Warehouse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
public interface WarehouseService extends IService<Warehouse> {

    IPage<Warehouse> selectAllByMessage(Page<Warehouse> warehousePage, String keywords);
}
