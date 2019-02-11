package com.zh.scheduled;

import com.google.common.collect.Lists;
import com.zh.contants.RedisContant;
import com.zh.dto.data.SiteFactorDTO;
import com.zh.dto.data.SiteRealTimeDataDTO;
import com.zh.entity.data.SiteFactorDataDay;
import com.zh.entity.data.SiteFactorDataHour;
import com.zh.entity.data.SiteFactorDataMinute;
import com.zh.entity.warning.FactorRealData;
import com.zh.mapper.data.CockpitMapper;
import com.zh.service.data.SiteFactorDataDayService;
import com.zh.service.data.SiteFactorDataHourService;
import com.zh.service.data.SiteFactorDataMinuteService;
import com.zh.service.warning.FactorRealDataService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Component
public class MakeFakeDataSchedule {

    @Resource
    CockpitMapper cockpitMapper;

    @Resource
    FactorRealDataService factorRealDataService;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    SiteFactorDataMinuteService siteFactorDataMinuteService;

    @Resource
    SiteFactorDataHourService siteFactorDataHourService;

    @Resource
    SiteFactorDataDayService siteFactorDataDayService;

    /**
     * 专业造假，童叟无欺
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void makeFakeRealData(){
        List<SiteFactorDTO> siteFactorInfo = cockpitMapper.getSiteFactorInfo();
        Long time = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), 0)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        List<FactorRealData> factorRealDatas = Lists.newArrayList();
        List<SiteRealTimeDataDTO> siteRealTimeDataDTOS = Lists.newArrayList();
        List<SiteFactorDataMinute> siteFactorDataMinutes = Lists.newArrayList();
        siteFactorInfo.forEach(a -> {
            FactorRealData factorRealData = new FactorRealData();
            factorRealData.setFactorCode(a.getFactorCode());
            factorRealData.setSiteId(a.getSiteId());
            factorRealData.setTime(time);
            Double value = 0.0;
            Long factorId = a.getFactorId();
            Random random = new Random();
            if (factorId == 1L || factorId == 2L) {
                value = random.nextInt(5) + random.nextDouble();
            } else {
                value = random.nextDouble();
            }
            factorRealData.setValue(value);
            factorRealDatas.add(factorRealData);
            SiteRealTimeDataDTO siteRealTimeDataDTO = new SiteRealTimeDataDTO();
            siteRealTimeDataDTO.setDataValue(value);
            siteRealTimeDataDTO.setFactorCode(a.getFactorCode());
            siteRealTimeDataDTO.setSiteId(a.getSiteId());
            siteRealTimeDataDTO.setValueTime(time);
            siteRealTimeDataDTO.setFactorId(factorId);
            siteRealTimeDataDTOS.add(siteRealTimeDataDTO);

            SiteFactorDataMinute siteFactorDataMinute = new SiteFactorDataMinute();
            siteFactorDataMinute.setFactorCode(a.getFactorCode());
            siteFactorDataMinute.setSiteId(a.getSiteId());
            siteFactorDataMinute.setTime(time);
            siteFactorDataMinute.setValue(value);
            siteFactorDataMinutes.add(siteFactorDataMinute);
        });

        //实时数据存储.
        factorRealDataService.saveBatch(factorRealDatas);
        //分钟数据存储.
        siteFactorDataMinuteService.saveBatch(siteFactorDataMinutes);
        //实时数据redis更新.
        redisTemplate.delete(RedisContant.SITE_REAL_DATA);
        redisTemplate.opsForValue().set(RedisContant.SITE_REAL_DATA, siteRealTimeDataDTOS);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void makeFakeHourData() {
        Long endTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0, 0)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long beginTime = Instant.ofEpochMilli(endTime).minus(1, ChronoUnit.HOURS).toEpochMilli();
        List<SiteRealTimeDataDTO> avgHourData = cockpitMapper.getAvgHourData(beginTime, endTime);
        List<SiteRealTimeDataDTO> sumHourData = cockpitMapper.getSumHourData(beginTime, endTime);
        avgHourData.addAll(sumHourData);
        List<SiteFactorDataHour> siteFactorDataHours = Lists.newArrayList();
        avgHourData.forEach(a -> {
            SiteFactorDataHour siteFactorDataHour = new SiteFactorDataHour();
            siteFactorDataHour.setFactorCode(a.getFactorCode());
            siteFactorDataHour.setSiteId(a.getSiteId());
            siteFactorDataHour.setTime(beginTime);
            siteFactorDataHour.setValue(a.getDataValue());
            siteFactorDataHours.add(siteFactorDataHour);
        });
        siteFactorDataHourService.saveBatch(siteFactorDataHours);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void makeFakeDayData() {
        Long endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long beginTime = Instant.ofEpochMilli(endTime).minus(1, ChronoUnit.DAYS).toEpochMilli();
        List<SiteRealTimeDataDTO> avgHourData = cockpitMapper.getAvgDayData(beginTime, endTime);
        List<SiteRealTimeDataDTO> sumDayData = cockpitMapper.getSumDayData(beginTime, endTime);
        avgHourData.addAll(sumDayData);
        List<SiteFactorDataDay> siteFactorDataDays = Lists.newArrayList();
        avgHourData.forEach(a -> {
            SiteFactorDataDay siteFactorDataHour = new SiteFactorDataDay();
            siteFactorDataHour.setFactorCode(a.getFactorCode());
            siteFactorDataHour.setSiteId(a.getSiteId());
            siteFactorDataHour.setTime(beginTime);
            siteFactorDataHour.setValue(a.getDataValue());
            siteFactorDataDays.add(siteFactorDataHour);
        });
        siteFactorDataDayService.saveBatch(siteFactorDataDays);
    }
}
