package com.zh.service.test2.impl;

import com.zh.entity.test2.Orders;
import com.zh.mapper.test2.OrdersMapper;
import com.zh.service.test2.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-20
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
