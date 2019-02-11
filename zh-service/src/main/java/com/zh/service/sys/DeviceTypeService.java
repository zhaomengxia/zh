package com.zh.service.sys;

import com.zh.dto.sys.site.DeviceTypeAndFactorIdDTO;
import com.zh.entity.sys.DeviceType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备类型表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
public interface DeviceTypeService extends IService<DeviceType> {

    List<DeviceTypeAndFactorIdDTO> selectByDeviceTypeId(Long deviceTypeId);
}
