package com.zh.scheduled;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.contants.RedisContant;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.SiteDetailDataDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.entity.sys.Site;
import com.zh.mapper.sys.SiteMapper;
import com.zh.service.data.RainService;
import com.zh.service.data.WaterRegimeService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author hahaha
 * @description:
 */
@Component
public class DataCacheSchedule {

    @Resource
    RainService rainService;

    @Resource
    WaterRegimeService waterRegimeService;

    @Resource
    SiteMapper siteMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0/1 * * * ?")
    @PostConstruct
    public void siteRealDataSchedule() {
        QueryDTO queryDTO = new QueryDTO();
        LocalDateTime now = LocalDateTime.now();
        Long beginTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endTime = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        queryDTO.setStart(beginTime);
        queryDTO.setEnd(endTime);
        queryDTO.setTime(endTime);
        List<RainListDTO> rainListDTOS = rainService.list(queryDTO);
        List<WaterListDTO> waterListDTOS = waterRegimeService.list(queryDTO);

        Map<Long, RainListDTO> rainMap = Maps.newHashMap();
        rainListDTOS.forEach(a -> rainMap.put(a.getId(), a));
        Map<Long, WaterListDTO> waterMap = Maps.newHashMap();
        waterListDTOS.forEach(a -> waterMap.put(a.getId(), a));

        List<SiteDetailDataDTO> list = Lists.newArrayList();

        List<Site> sites = siteMapper.selectList(null);

        sites.forEach(a -> {
            SiteDetailDataDTO siteDetailDataDTO = new SiteDetailDataDTO();
            siteDetailDataDTO.setSiteId(a.getId());
            siteDetailDataDTO.setSiteName(a.getName());
            RainListDTO rainListDTO = rainMap.get(a.getId());
            siteDetailDataDTO.setRainRealData(rainListDTO == null ? null : rainListDTO.getValue());
            WaterListDTO waterListDTO = waterMap.get(a.getId());
            if (waterListDTO != null) {
                siteDetailDataDTO.setDownstreamData(waterListDTO.getDownStream());
                siteDetailDataDTO.setUpstreamData(waterListDTO.getUpperStream());
            }
            list.add(siteDetailDataDTO);
        });
        redisTemplate.delete(RedisContant.SITE_REAL_DATA_LIST);
        redisTemplate.opsForValue().set(RedisContant.SITE_REAL_DATA_LIST, list);

    }
}
