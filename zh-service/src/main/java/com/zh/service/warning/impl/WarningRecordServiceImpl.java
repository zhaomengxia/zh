package com.zh.service.warning.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.contants.RedisContant;
import com.zh.dto.data.*;
import com.zh.dto.warning.WarningRecordDTO;
import com.zh.entity.warning.WarningRecord;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.data.CockpitMapper;
import com.zh.mapper.warning.WarningRecordMapper;
import com.zh.service.warning.WarningRecordService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 预警记录表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@Service
public class WarningRecordServiceImpl extends ServiceImpl<WarningRecordMapper, WarningRecord> implements WarningRecordService {
    @Resource
    private WarningRecordMapper warningRecordMapper;
    @Resource
    CockpitMapper cockpitMapper;
    @Resource
    RedisTemplate redisTemplate;
    private static Long MILLIS_OF_HOUR = 1000 * 60L;
    @Override
    public IPage<WarningRecordDTO> selectAllWarnRecord(Page<WarningRecordDTO> page, String areaCode, String keywords, Long startTime, Long endTime,Integer value) {
        return warningRecordMapper.selectAllWarnRecord(page,areaCode,keywords,startTime,endTime,value);
    }

    @Override
    public List<WarningRecordDTO> selectAllWarnRecord(String areaCode, String keywords, Long startTime, Long endTime, Integer value) {
        List<WarningRecordDTO> warningRecordDTOS=warningRecordMapper.selectAllWarnRecord(areaCode,keywords,startTime,endTime,value);
        warningRecordDTOS=warningRecordDTOS.stream().filter(e->e.getStatus()!=5).collect(Collectors.toList());
        return  warningRecordDTOS;
    }

    @Override
    public boolean updateWarnSattus(Long warnRecordId,Integer status) {
        if (null==warnRecordId){
            throw new UnifiedException("请传预警id！");
        }
        WarningRecord warningRecords =this.getById(warnRecordId);
        //下面判断 在执行外部预警 发布预警时对预警记录更新状态之前，判断是否跳过了 内部预警发布预警，如果跳过则给jump赋值为1
        if (status==3){
            if (warningRecords.getStatus()==1){
                //新预警跨到外部预警  越过了内部预警  此时的1就表示 跨过了内部预警
                warningRecords.setJump(1);
            }
        }
        if (status==4){
            if (warningRecords.getStatus()==2){
                //可以执行 内部预警到响应启动的跨步操作  越过了外部预警  此时的2就表示 跨过了外部预警
                warningRecords.setJump(2);
            }
            else if(warningRecords.getStatus()==1){
                warningRecords.setJump(3);
            }
        }
        if (warningRecords.getStatus().equals(status)){
            return false;
        }
        warningRecords.setStatus(status);
        if (this.updateById(warningRecords)){
            return true;
        }
        else{
            return  false;
        }
    }

    @Override
    public MapInfoDTO getAll() {
        //查询所有监测点基础信息.
        List<SiteMapInfoDTO> siteMapInfoDTOS = cockpitMapper.getSiteMapInfo();
        //查询监测点因子的未关闭的最新预警信息.
        List<SiteWarningDTO> siteWarningDTOS = cockpitMapper.getNewestSiteWarning(null);
        //从redis中取出监测点的实时数据.
        List<SiteRealTimeDataDTO> siteRealTimeDataDTOS =
                (List<SiteRealTimeDataDTO>) redisTemplate.opsForValue().get(RedisContant.SITE_REAL_DATA);
        Map<Long, List<SiteRealTimeDataDTO>> map =
                siteRealTimeDataDTOS.stream().collect(Collectors.groupingBy(SiteRealTimeDataDTO::getSiteId));
        Map<Long, List<SiteWarningDTO>> collect =
                siteWarningDTOS.stream().collect(Collectors.groupingBy(SiteWarningDTO::getSiteId));
        List<SiteFactorDTO> list = cockpitMapper.getSiteFactorInfo();
        Map<Long, List<SiteFactorDTO>> listMap = list.stream().collect(Collectors.groupingBy(SiteFactorDTO::getSiteId));
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
        }
        MapInfoDTO mapInfoDTO = new MapInfoDTO();
        mapInfoDTO.setNormalCount(normalCount);
        mapInfoDTO.setOfflineCount(offlineCount);
        return mapInfoDTO;
    }
}
