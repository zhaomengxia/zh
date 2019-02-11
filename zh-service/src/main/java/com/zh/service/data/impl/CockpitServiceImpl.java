package com.zh.service.data.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.api.Result;
import com.zh.contants.RedisContant;
import com.zh.dto.data.*;
import com.zh.dto.response.ResponseMapDTO;
import com.zh.entity.sys.Site;
import com.zh.entity.warning.WarningDegree;
import com.zh.mapper.data.CockpitMapper;
import com.zh.mapper.sys.SiteMapper;
import com.zh.mapper.warning.WarningDegreeMapper;
import com.zh.service.data.CockpitService;
import com.zh.service.data.RainService;
import com.zh.service.data.WaterRegimeService;
import com.zh.service.response.ResponseRecordService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Service
public class CockpitServiceImpl implements CockpitService {

    @Resource
    CockpitMapper cockpitMapper;

    @Resource
    WarningDegreeMapper warningDegreeMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    WaterRegimeService waterRegimeService;

    @Resource
    RainService rainService;

    @Resource
    ResponseRecordService responseRecordService;

    @Resource
    SiteMapper siteMapper;

    private static Long MILLIS_OF_HOUR = 1000 * 60L;

    @Override
    public Result getMapInfo() {
        //查询所有监测点基础信息.
        List<SiteMapInfoDTO> siteMapInfoDTOS = cockpitMapper.getSiteMapInfo();
        //查询监测点因子的未关闭的最新预警信息.
        List<SiteWarningDTO> siteWarningDTOS = cockpitMapper.getNewestSiteWarning(null);

        List<WarningDegree> warningDegrees = warningDegreeMapper.selectList(null);

        //从redis中取出监测点的实时数据.
        List<SiteRealTimeDataDTO> siteRealTimeDataDTOS =
                (List<SiteRealTimeDataDTO>) redisTemplate.opsForValue().get(RedisContant.SITE_REAL_DATA);
        Map<String, SiteRealTimeDataDTO> realTimeDataDTOMap = Maps.newHashMap();
        MapInfoDTO mapInfoDTO = new MapInfoDTO();

        List<SiteFactorDTO> list = cockpitMapper.getSiteFactorInfo();
        Map<Long, List<SiteFactorDTO>> listMap = list.stream().collect(Collectors.groupingBy(SiteFactorDTO::getSiteId));
        //如果查不到实时数据.
        if (siteRealTimeDataDTOS == null || siteRealTimeDataDTOS.size() == 0) {

            mapInfoDTO.setNormalCount(0);
            mapInfoDTO.setOfflineCount(list.size());
            mapInfoDTO.setSiteMapInfoDTOS(siteMapInfoDTOS);
            mapInfoDTO.setWarningDegrees(warningDegrees);
            return Result.success(mapInfoDTO);
        }
        siteRealTimeDataDTOS.forEach(a -> realTimeDataDTOMap.put(a.getFactorCode(), a));
        Map<Long, List<SiteRealTimeDataDTO>> map =
                siteRealTimeDataDTOS.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));

        Map<Long, WarningDegree> warningDegreeMap = Maps.newHashMap();
        warningDegrees.forEach(a -> warningDegreeMap.put(a.getId(), a));
        Map<Long, List<SiteWarningDTO>> collect =
                siteWarningDTOS.stream().collect(Collectors.groupingBy(SiteWarningDTO::getSiteId));
        Integer normalCount = 0;
        Integer offlineCount = 0;
        Long now = System.currentTimeMillis();
        for (SiteMapInfoDTO a : siteMapInfoDTOS) {
            a.setSiteWarningDTOS(collect.get(a.getSiteId()));
            List<SiteRealTimeDataDTO> realTimeDataDTOS = map.get(a.getSiteId());
            List<SiteWarningDTO> warningDTOS = a.getSiteWarningDTOS();
            //无预警时
            if (warningDTOS == null || warningDTOS.size() == 0) {
                //无实时数据
                if (realTimeDataDTOS == null || realTimeDataDTOS.size() == 0) {
                    List<SiteFactorDTO> siteFactorDTOS = listMap.get(a.getSiteId());
                    offlineCount = offlineCount + (siteFactorDTOS == null ? 0 : siteFactorDTOS.size());
                } else {
                    for (SiteRealTimeDataDTO siteRealTimeDataDTO : realTimeDataDTOS) {
                        if (now - siteRealTimeDataDTO.getValueTime() > MILLIS_OF_HOUR * 30) {
                            offlineCount++;
                        } else {
                            normalCount++;
                        }
                    }
                }
            } else {

                Map<String, List<SiteWarningDTO>> stringListMap =
                        warningDTOS.stream().collect(Collectors.groupingBy(SiteWarningDTO::getFactorCode));
                for (SiteRealTimeDataDTO siteRealTimeDataDTO : realTimeDataDTOS) {
                    if (now - siteRealTimeDataDTO.getValueTime() > MILLIS_OF_HOUR * 30) {
                        offlineCount++;
                    } else if (stringListMap.get(siteRealTimeDataDTO.getFactorCode()) == null) {
                        normalCount++;
                    }
                }
            }
            a.setWarningCount(a.getSiteWarningDTOS() == null ? 0 : a.getSiteWarningDTOS().size());
            if (warningDTOS != null && warningDTOS.size() > 0) {
                //找出监测站最新的预警信息.
                SiteWarningDTO siteWarningDTO = warningDTOS.stream().max(new Comparator<SiteWarningDTO>() {
                    @Override
                    public int compare(SiteWarningDTO o1, SiteWarningDTO o2) {
                        return o1.getWarningTime().compareTo(o2.getWarningTime());
                    }
                }).get();
                a.setWarningDegreeIcon(siteWarningDTO.getDegreeIcon());
                a.setWarningDegreeId(siteWarningDTO.getDegreeId());
                a.setWarningDegreeName(siteWarningDTO.getDegreeName());
            } else {
                a.setWarningDegreeIcon("无预警");
            }

        }
        mapInfoDTO.setNormalCount(normalCount);
        mapInfoDTO.setOfflineCount(offlineCount);
        mapInfoDTO.setSiteMapInfoDTOS(siteMapInfoDTOS);
        mapInfoDTO.setWarningDegrees(warningDegrees);
        return Result.success(mapInfoDTO);
    }

    @Override
    public Result getSiteWarningDetail(Long siteId) {
        List<SiteWarningDTO> siteWarningDTOS = cockpitMapper.getNewestSiteWarning(siteId);
        List<WarningDegree> warningDegrees = warningDegreeMapper.selectList(null);
        Map<Long, WarningDegree> degreeMap = Maps.newHashMap();
        warningDegrees.forEach(a->degreeMap.put(a.getId(), a));
        siteWarningDTOS.forEach(a->{
            if(a.getDegreeId() != null){
                a.setDegreeIcon(degreeMap.get(a.getDegreeId()).getIcon());
            }
        });
        return Result.success(siteWarningDTOS);
    }

    @Override
    public Result getResponseTrends() {
        ResponseMapDTO responseMapDTO = responseRecordService.selectAllMap();
        return Result.success(responseMapDTO);
    }

    @Override
    public Result getRainWaterData(Long siteId, Long time) {
        QueryDTO queryDTO = new QueryDTO();
        if(time == null){
            List<SiteDetailDataDTO> siteDetailDataDTOS = (List<SiteDetailDataDTO>)redisTemplate.opsForValue().get(RedisContant.SITE_REAL_DATA_LIST);
            if(siteId != null){
                List<SiteDetailDataDTO> collect = siteDetailDataDTOS.stream().filter(a -> siteId.equals(a.getSiteId())).collect(Collectors.toList());
                return Result.success(collect);
            }else {
                return Result.success(siteDetailDataDTOS);
            }

        }
        queryDTO.setTime(time);
        queryDTO.setId(siteId);
        Instant instant = Instant.ofEpochMilli(time);
        Long beginTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).minusHours(1)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        queryDTO.setStart(beginTime);
        queryDTO.setEnd(time);

        List<RainListDTO> rainListDTOS = rainService.list(queryDTO);
        List<WaterListDTO> waterListDTOS = waterRegimeService.list(queryDTO);
        List<SiteDetailDataDTO> siteDetailDataDTOS = Lists.newArrayList();
        if(siteId != null){
            SiteDetailDataDTO siteDetailDataDTO = new SiteDetailDataDTO();
            if(rainListDTOS.size() > 0){
                Map<Long, RainListDTO> rainListDTOMap = Maps.newHashMap();
                rainListDTOS.forEach(a->rainListDTOMap.put(a.getId(), a));
                RainListDTO rainListDTO = rainListDTOMap.get(siteId);
                siteDetailDataDTO.setRainRealData(rainListDTO == null ? null : rainListDTO.getValue());
                siteDetailDataDTO.setSiteName(rainListDTO == null ? null : rainListDTO.getName());
            }
            if(waterListDTOS.size() > 0){
                Map<Long, WaterListDTO> waterListDTOMap = Maps.newHashMap();
                waterListDTOS.forEach(a->waterListDTOMap.put(a.getId(), a));
                WaterListDTO waterListDTO = waterListDTOMap.get(siteId);
                siteDetailDataDTO.setSiteName(waterListDTO == null ? null : waterListDTO.getName());
                siteDetailDataDTO.setUpstreamData(waterListDTO == null ? null : waterListDTO.getUpperStream());
                siteDetailDataDTO.setDownstreamData(waterListDTO == null ? null : waterListDTO.getDownStream());
            }
            siteDetailDataDTOS.add(siteDetailDataDTO);
            return Result.success(siteDetailDataDTOS);
        }else {
            Map<Long, RainListDTO> map = Maps.newHashMap();
            rainListDTOS.forEach(a->map.put(a.getId(), a));
            Map<Long, WaterListDTO> waterListDTOMap = Maps.newHashMap();
            waterListDTOS.forEach(a->waterListDTOMap.put(a.getId(), a));
            List<Site> list = siteMapper.selectList(null);
            list.forEach(a->{
                SiteDetailDataDTO siteDetailDataDTO = new SiteDetailDataDTO();
                siteDetailDataDTO.setSiteName(a.getName());
                RainListDTO rainListDTO = map.get(a.getId());
                siteDetailDataDTO.setRainRealData(rainListDTO == null ? null : rainListDTO.getValue());
                WaterListDTO waterListDTO = waterListDTOMap.get(a.getId());
                siteDetailDataDTO.setUpstreamData(waterListDTO == null ? null : waterListDTO.getUpperStream());
                siteDetailDataDTO.setDownstreamData(waterListDTO == null ? null : waterListDTO.getDownStream());
                siteDetailDataDTO.setSiteId(siteId);
                siteDetailDataDTOS.add(siteDetailDataDTO);
            });
            return Result.success(siteDetailDataDTOS);
        }
    }

    @Override
    public Result getKeyPointDataTrend(Long beginTime, Long endTime) {
        List<KeyPointDataDTO> list = cockpitMapper.getKeySiteList(null);
        //获取上游水位数据.
        List<SiteRealTimeDataDTO> upstreamDatas = cockpitMapper.getLevelTimeData(beginTime, endTime, 1L);
        Map<Long, List<SiteRealTimeDataDTO>> upstreamMap = upstreamDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        //获取下游水位数据.
        List<SiteRealTimeDataDTO> downstreamDatas = cockpitMapper.getLevelTimeData(beginTime, endTime, 2L);
        Map<Long, List<SiteRealTimeDataDTO>> downstreamMap = downstreamDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        //获取雨量小时数据.
        List<SiteRealTimeDataDTO> rainDatas = cockpitMapper.getRainHourData(beginTime, endTime, null);
        Map<Long, List<SiteRealTimeDataDTO>> rainMap = rainDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        list.forEach(a -> {
            Long siteId = a.getSiteId();
            a.setUpstreamData(upstreamMap.get(siteId));
            a.setDownstreamData(downstreamMap.get(siteId));
            a.setRainData(rainMap.get(siteId));
        });
        return Result.success(list);
    }

    @Override
    public Result getKeyPointDataTrendDetail(Long siteId, Long beginTime, Long endTime) {
        List<KeyPointDataDTO> list = cockpitMapper.getKeySiteList(siteId);
        //获取上游水位数据.
        List<SiteRealTimeDataDTO> upstreamDatas = cockpitMapper.getLevelTimeDataDetail(beginTime, endTime, 1L, siteId);
        Map<Long, List<SiteRealTimeDataDTO>> upstreamMap = upstreamDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        //获取下游水位数据.
        List<SiteRealTimeDataDTO> downstreamDatas = cockpitMapper.getLevelTimeDataDetail(beginTime, endTime, 2L, siteId);
        Map<Long, List<SiteRealTimeDataDTO>> downstreamMap = downstreamDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        //获取雨量小时数据.
        List<SiteRealTimeDataDTO> rainDatas = cockpitMapper.getRainHourData(beginTime, endTime, siteId);
        Map<Long, List<SiteRealTimeDataDTO>> rainMap = rainDatas.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        list.forEach(a -> {
            Long id = a.getSiteId();
            a.setUpstreamData(upstreamMap.get(id));
            a.setDownstreamData(downstreamMap.get(id));
            a.setRainData(rainMap.get(id));
        });
        return Result.success(list);
    }

    @Override
    public Result getSiteHistoryData(Long beginTime, Long endTime, Long siteId) {
        SiteDetailDataDTO siteDetailDataDTO = new SiteDetailDataDTO();
        List<SiteRealTimeDataDTO> list =
                (List<SiteRealTimeDataDTO>) redisTemplate.opsForValue().get(RedisContant.SITE_REAL_DATA);
        Map<Long, List<SiteRealTimeDataDTO>> collect = list.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        List<SiteRealTimeDataDTO> realTimeDataDTOS = collect.get(siteId);
        if(CollUtil.isNotEmpty(realTimeDataDTOS)){
            for (SiteRealTimeDataDTO siteRealTimeDataDTO : realTimeDataDTOS){
                Double dataValue = siteRealTimeDataDTO.getDataValue();
                Long factorId = siteRealTimeDataDTO.getFactorId();
                if(factorId == 1L){
                    siteDetailDataDTO.setUpstreamData(dataValue);
                }else if(factorId == 2L){
                    siteDetailDataDTO.setDownstreamData(dataValue);
                }else {
                    siteDetailDataDTO.setRainRealData(dataValue);
                }
            }
        }
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setTime(endTime);
        queryDTO.setStart(beginTime);
        queryDTO.setId(siteId);
        queryDTO.setEnd(endTime);
        List<WaterListDTO> waterListDTOS = waterRegimeService.section(queryDTO);
        siteDetailDataDTO.setWaterListDTOS(waterListDTOS);
        if(waterListDTOS.size() == 0){
            List<SiteRealTimeDataDTO> rainDatas = cockpitMapper.getRainHourData(beginTime, endTime, siteId);
            siteDetailDataDTO.setRainDatas(rainDatas);
        }
        Map<String, Double> histogram = rainService.histogram(queryDTO);
        siteDetailDataDTO.setRainMap(histogram);
        return Result.success(siteDetailDataDTO);
    }

    @Override
    public Result getSituationalAwareness() {

        return null;
    }

    @Override
    public Result alarmCenterInfo(Integer type) {
        return null;
    }

    @Override
    public Result getInnerWarningDetail(Long siteId) {
        AreaDetailDTO areaDetailDTO = cockpitMapper.getSiteArea(siteId);
        return Result.success(areaDetailDTO);
    }
}
