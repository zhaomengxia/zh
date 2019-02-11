package com.zh.service.data;

import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.entity.data.SiteFactorDataHour;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 站点因子小时数据 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
public interface SiteFactorDataHourService extends IService<SiteFactorDataHour> {

    List<RainListDTO> rainSection(QueryDTO queryDTO);

    List<WaterListDTO> waterSection(QueryDTO queryDTO);
}
