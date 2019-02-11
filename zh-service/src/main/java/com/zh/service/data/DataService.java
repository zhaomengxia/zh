package com.zh.service.data;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import com.zh.dto.data.WaterListDTO;
import com.zh.dto.data.WaterResultListDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一数据接口
 *
 * @author  赵梦霞
 * @since 2019-01-11 16:03

 **/
public interface DataService {

    /**
     * @param waterResultListDTOS 查询结果
     * @author  赵梦霞
     * @Description 组装放回结果
     * @since 2019/1/11 16:09
     **/
    default List<WaterListDTO> assembleWaterList(List<WaterResultListDTO> waterResultListDTOS) {
        List<WaterListDTO> result = Lists.newArrayList();
        if (CollUtil.isNotEmpty(waterResultListDTOS)) {
            waterResultListDTOS.parallelStream().collect(Collectors.groupingBy(WaterResultListDTO::getTime)).forEach((time, list) -> {
                WaterListDTO waterListDTO = new WaterListDTO();
                waterListDTO.setId(list.get(0).getId())
                        .setName(list.get(0).getName())
                        .setLongitude(list.get(0).getLongitude())
                        .setLatitude(list.get(0).getLatitude())
                        .setTime(list.get(0).getTime())
                        .setUpperStream(NumberUtil.round(list.stream().filter(e -> e.getFactorId() == 1L).map(WaterResultListDTO::getValue).reduce(0.0, Double::sum),2).doubleValue())
                        .setDownStream(NumberUtil.round(list.stream().filter(e -> e.getFactorId() == 2L).map(WaterResultListDTO::getValue).reduce(0.0, Double::sum),2).doubleValue())
                        .setHistoryLevel(list.get(0).getHistoryLevel())
                        .setHistoryLevelTime(list.get(0).getHistoryLevelTime());
                result.add(waterListDTO);
            });
            //排序
            result.sort(Comparator.comparing(WaterListDTO::getTime));
        }
        return result;
    }

}
