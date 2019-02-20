package com.zh.service.test.impl;

import com.zh.entity.test.Product;
import com.zh.mapper.test.ProductMapper;
import com.zh.service.test.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
