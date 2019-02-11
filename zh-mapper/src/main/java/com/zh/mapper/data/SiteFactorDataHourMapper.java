package com.zh.mapper.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterResultListDTO;
import com.zh.entity.data.SiteFactorDataHour;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 站点因子小时数据 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
public interface SiteFactorDataHourMapper extends BaseMapper<SiteFactorDataHour> {

    List<RainListDTO> rainSection(@Param("queryDTO") QueryDTO queryDTO);

    List<WaterResultListDTO> waterSection(@Param("queryDTO") QueryDTO queryDTO);
}
