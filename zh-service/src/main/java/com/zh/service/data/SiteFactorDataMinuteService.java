package com.zh.service.data;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.entity.data.SiteFactorDataMinute;

import java.util.List;

/**
 * <p>
 * 站点因子分钟数据 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
public interface SiteFactorDataMinuteService extends IService<SiteFactorDataMinute> {

    /**
     * @param siteId 站点主键
     * @param start  开始时间
     * @param end    结束时间
     * @author  赵梦霞
     * @Description 统计一个时间段内累计降雨量
     * @since 2019/1/4 14:40
     **/
    double accumulative(Long siteId, Long start, Long end);

    List<RainListDTO> rainList(QueryDTO queryDTO);

    List<RainListDTO> rainSection(QueryDTO queryDTO);

    List<WaterListDTO> waterList(QueryDTO queryDTO);

    List<WaterListDTO> waterSection(QueryDTO queryDTO);
}
