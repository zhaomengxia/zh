package com.zh.service.data.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.contants.RedisContant;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.dto.data.WaterResultListDTO;
import com.zh.entity.data.SiteFactorDataMinute;
import com.zh.enums.DataStatusEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.data.SiteFactorDataMinuteMapper;
import com.zh.service.data.DataService;
import com.zh.service.data.SiteFactorDataMinuteService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * <p>
 * 站点因子分钟数据 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
@Service
public class SiteFactorDataMinuteServiceImpl extends ServiceImpl<SiteFactorDataMinuteMapper, SiteFactorDataMinute> implements SiteFactorDataMinuteService, DataService {

    @Resource
    private SiteFactorDataMinuteMapper siteFactorDataMinuteMapper;


    @Override
    public double accumulative(Long siteId, Long start, Long end) {
        return siteFactorDataMinuteMapper.accumulative(siteId, start, end);
    }

    @Override
    @Cacheable(cacheNames = RedisContant.RAIN_DATA_LIST, key = "#queryDTO")
    public List<RainListDTO> rainList(QueryDTO queryDTO) {
        List<RainListDTO> result = Lists.newArrayList();
        List<RainListDTO> rainListDTOS = siteFactorDataMinuteMapper.selectRainList(queryDTO);
        if (CollUtil.isNotEmpty(rainListDTOS)) {
            rainListDTOS.parallelStream().collect(Collectors.groupingBy(RainListDTO::getId)).forEach((id, list) ->
                    result.add(RainListDTO.builder()
                            .id(id)
                            .name(list.get(0).getName())
                            .longitude(list.get(0).getLongitude())
                            .latitude(list.get(0).getLatitude())
                            .value(NumberUtil.round(list.parallelStream().map(RainListDTO::getValue).reduce(0.0, Double::sum), 2).doubleValue())
                            .build())
            );

            //根据降水量排序 由大到小
            result.sort(Comparator.comparing(RainListDTO::getValue).reversed());
        }

        return result;
    }

    @Override
    public List<RainListDTO> rainSection(QueryDTO queryDTO) {
        return siteFactorDataMinuteMapper.rainSection(queryDTO);
    }

    @Override
    @Cacheable(cacheNames = RedisContant.WATER_DATA_LIST, key = "#queryDTO")
    public List<WaterListDTO> waterList(QueryDTO queryDTO) {

        List<WaterListDTO> result;
        List<WaterListDTO> last;

        Future<List<WaterListDTO>> resultFuture = ThreadUtil.execAsync(() -> this.convert(siteFactorDataMinuteMapper.selectWaterList(queryDTO)));
        Future<List<WaterListDTO>> lastFuture = ThreadUtil.execAsync(() -> {
            //查询上一时刻
            QueryDTO lastQueryDTO = new QueryDTO();
            lastQueryDTO.setTime(Instant.ofEpochMilli(queryDTO.getTime()).minus(1, ChronoUnit.MINUTES).toEpochMilli());
            return this.convert(siteFactorDataMinuteMapper.selectWaterList(lastQueryDTO));
        });

        try {
            result = resultFuture.get();
            last = lastFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new UnifiedException("查询数据失败");
        }
        //上下游状态
        if (CollUtil.isNotEmpty(result) && CollUtil.isNotEmpty(last)) {
            for (WaterListDTO nowValue : result) {
                for (WaterListDTO lastValue : last) {
                    if (nowValue.getId().equals(lastValue.getId())) {
                        nowValue.setUpperStreamStatus(this.waterStatus(lastValue.getUpperStream(), nowValue.getUpperStream()));
                        nowValue.setDownStreamStatus(this.waterStatus(lastValue.getDownStream(), nowValue.getDownStream()));
                        break;
                    }
                }
            }

            //排序
            result.sort(Comparator.comparing(WaterListDTO::getId));

            return result;
        }
        throw new UnifiedException("暂无数据");
    }

    @Override
    public List<WaterListDTO> waterSection(QueryDTO queryDTO) {
        List<WaterResultListDTO> waterResultListDTOS = siteFactorDataMinuteMapper.waterSection(queryDTO);
        return this.assembleWaterList(waterResultListDTOS);
    }

    private List<WaterListDTO> convert(List<WaterResultListDTO> waterResultListDTOS) {
        List<WaterListDTO> result = Lists.newArrayList();
        if (CollUtil.isNotEmpty(waterResultListDTOS)) {
            waterResultListDTOS.parallelStream().collect(Collectors.groupingBy(WaterResultListDTO::getId)).forEach((id, list) -> {
                WaterListDTO waterListDTO = new WaterListDTO();
                waterListDTO.setId(id).setName(list.get(0).getName())
                        .setLongitude(list.get(0).getLongitude())
                        .setLatitude(list.get(0).getLatitude())
                        .setHistoryLevel(list.get(0).getHistoryLevel())
                        .setHistoryLevelTime(list.get(0).getHistoryLevelTime())
                        .setTime(list.get(0).getTime())
                        .setUpperStream(NumberUtil.round(list.stream().filter(e -> e.getFactorId() != null && e.getFactorId() == 1L).map(WaterResultListDTO::getValue).reduce(0.0, Double::sum), 2).doubleValue())
                        .setDownStream(NumberUtil.round(list.stream().filter(e -> e.getFactorId() != null && e.getFactorId() == 2L).map(WaterResultListDTO::getValue).reduce(0.0, Double::sum), 2).doubleValue());
                result.add(waterListDTO);
            });
        }
        return result;
    }

    /**
     * @param lastValue 上一时刻数值
     * @param nowValue  查询时刻数值
     * @author  赵梦霞
     * @Description 判断变化情况
     * @since 2019/1/25 13:44
     **/
    private Integer waterStatus(Double lastValue, Double nowValue) {
        double flag = nowValue - lastValue;
        if (flag > 0) {
            return DataStatusEnum.UP.getStatus();
        } else if (flag == 0) {
            return DataStatusEnum.FLAT.getStatus();
        } else {
            return DataStatusEnum.LOW.getStatus();
        }
    }

}
