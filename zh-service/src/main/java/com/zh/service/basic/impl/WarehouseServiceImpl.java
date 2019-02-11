package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.MessageDTO;
import com.zh.entity.basic.Warehouse;
import com.zh.mapper.basic.WarehouseMapper;
import com.zh.service.basic.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;
    @Override
    public IPage<Warehouse> selectAllByMessage(Page<Warehouse> warehousePage,String keywords) {

        return warehouseMapper.selectAllByMessage(warehousePage,keywords);
    }
}
