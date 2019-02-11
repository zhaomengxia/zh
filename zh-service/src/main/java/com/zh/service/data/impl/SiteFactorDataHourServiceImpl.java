package com.zh.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.dto.data.WaterResultListDTO;
import com.zh.entity.data.SiteFactorDataHour;
import com.zh.mapper.data.SiteFactorDataHourMapper;
import com.zh.service.data.DataService;
import com.zh.service.data.SiteFactorDataHourService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 站点因子小时数据 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
@Service
public class SiteFactorDataHourServiceImpl extends ServiceImpl<SiteFactorDataHourMapper, SiteFactorDataHour> implements SiteFactorDataHourService, DataService {

    @Resource
    private SiteFactorDataHourMapper siteFactorDataHourMapper;

    @Override
    public List<RainListDTO> rainSection(QueryDTO queryDTO) {
        return siteFactorDataHourMapper.rainSection(queryDTO);
    }

    @Override
    public List<WaterListDTO> waterSection(QueryDTO queryDTO) {
        List<WaterResultListDTO> waterResultListDTOS = siteFactorDataHourMapper.waterSection(queryDTO);
        return this.assembleWaterList(waterResultListDTOS);
    }
}
