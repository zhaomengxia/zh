package com.zh.scheduled;

import org.springframework.stereotype.Component;


/**
 * Copyright 2016 envCloud Inc.
 *
 * @author hahaha
 * @description:
 */
@Component
public class DataCacheSchedule {

//    @Resource
//    RainService rainService;
//
//    @Resource
//    WaterRegimeService waterRegimeService;
//
//    @Resource
//    SiteMapper siteMapper;
//
//    @Resource
//    RedisTemplate redisTemplate;
//
//    @Scheduled(cron = "0 0/1 * * * ?")
//    @PostConstruct
//    public void siteRealDataSchedule() {
//        QueryDTO queryDTO = new QueryDTO();
//        LocalDateTime now = LocalDateTime.now();
//        Long beginTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        Long endTime = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        queryDTO.setStart(beginTime);
//        queryDTO.setEnd(endTime);
//        queryDTO.setTime(endTime);
//        List<RainListDTO> rainListDTOS = rainService.list(queryDTO);
//        List<WaterListDTO> waterListDTOS = waterRegimeService.list(queryDTO);
//
//        Map<Long, RainListDTO> rainMap = Maps.newHashMap();
//        rainListDTOS.forEach(a -> rainMap.put(a.getId(), a));
//        Map<Long, WaterListDTO> waterMap = Maps.newHashMap();
//        waterListDTOS.forEach(a -> waterMap.put(a.getId(), a));
//
//        List<SiteDetailDataDTO> list = Lists.newArrayList();
//
//        List<Site> sites = siteMapper.selectList(null);
//
//        sites.forEach(a -> {
//            SiteDetailDataDTO siteDetailDataDTO = new SiteDetailDataDTO();
//            siteDetailDataDTO.setSiteId(a.getId());
//            siteDetailDataDTO.setSiteName(a.getName());
//            RainListDTO rainListDTO = rainMap.get(a.getId());
//            siteDetailDataDTO.setRainRealData(rainListDTO == null ? null : rainListDTO.getValue());
//            WaterListDTO waterListDTO = waterMap.get(a.getId());
//            if (waterListDTO != null) {
//                siteDetailDataDTO.setDownstreamData(waterListDTO.getDownStream());
//                siteDetailDataDTO.setUpstreamData(waterListDTO.getUpperStream());
//            }
//            list.add(siteDetailDataDTO);
//        });
//        redisTemplate.delete(RedisContant.SITE_REAL_DATA_LIST);
//        redisTemplate.opsForValue().set(RedisContant.SITE_REAL_DATA_LIST, list);
//
//    }
}
