package com.zh.mapper.sys;

import com.zh.dto.sys.site.DeviceTypeAndFactorIdDTO;
import com.zh.dto.warning.SiteFactorDTO;
import com.zh.entity.sys.Factor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

/**
 * <p>
 * 设备类型因子关联表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
public interface FactorMapper extends BaseMapper<Factor> {
    List<DeviceTypeAndFactorIdDTO> selectByDeviceTypeId(@Param("deviceTypeId") Long deviceTypeId);

    List<SiteFactorDTO> getSiteFactor(Long siteId);

}
