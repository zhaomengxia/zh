package com.zh.mapper.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.constant.CacheKeyConstant;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterResultListDTO;
import com.zh.entity.data.SiteFactorDataMinute;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 站点因子分钟数据 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
public interface SiteFactorDataMinuteMapper extends BaseMapper<SiteFactorDataMinute> {

    double accumulative(@Param("siteId") Long siteId, @Param("start") Long start, @Param("end") Long end);

    List<RainListDTO> selectRainList(@Param("queryDTO") QueryDTO queryDTO);

    List<RainListDTO> rainSection(@Param("queryDTO") QueryDTO queryDTO);

    @Cacheable(cacheNames = CacheKeyConstant.WATER_QUERY_LIST_CACHE, key = "#p0")
    List<WaterResultListDTO> selectWaterList(@Param("queryDTO") QueryDTO queryDTO);

    List<WaterResultListDTO> waterSection(@Param("queryDTO") QueryDTO queryDTO);
}
