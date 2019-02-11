package com.zh.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.entity.sys.DeviceFactor;
import com.zh.mapper.sys.DeviceFactorMapper;
import com.zh.service.sys.DeviceFactorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备因子表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@Service
public class DeviceFactorServiceImpl extends ServiceImpl<DeviceFactorMapper, DeviceFactor> implements DeviceFactorService {
}
