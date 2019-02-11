package com.zh.mapper.sys;

import com.zh.entity.sys.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 站点设备表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
public interface DeviceMapper extends BaseMapper<Device> {

    List<Device> selectAllBySiteId(@Param("siteId") Long siteId);
}
