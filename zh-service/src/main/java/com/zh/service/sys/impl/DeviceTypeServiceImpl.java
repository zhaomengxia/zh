package com.zh.service.sys.impl;

import com.zh.dto.sys.site.DeviceTypeAndFactorIdDTO;
import com.zh.entity.sys.DeviceType;
import com.zh.mapper.sys.DeviceTypeMapper;
import com.zh.mapper.sys.FactorMapper;
import com.zh.service.sys.DeviceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 设备类型表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@Service
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements DeviceTypeService {
    @Resource
    private FactorMapper factorMapper;
    @Override
    public List<DeviceTypeAndFactorIdDTO> selectByDeviceTypeId(Long deviceTypeId) {

        return factorMapper.selectByDeviceTypeId(deviceTypeId);
    }
}
