package com.zh.service.sys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.dto.sys.site.DeviceDTO;
import com.zh.dto.sys.site.DeviceFactorDTO;
import com.zh.dto.sys.site.SiteDTO;
import com.zh.entity.sys.Device;
import com.zh.entity.sys.DeviceFactor;
import com.zh.entity.sys.Site;
import com.zh.entity.warning.WarningRule;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.sys.DeviceMapper;
import com.zh.mapper.sys.SiteMapper;
import com.zh.service.sys.DeviceFactorService;
import com.zh.service.sys.DeviceService;
import com.zh.service.sys.SiteService;
import com.zh.service.warning.WarningRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * <p>
 * 自动监测站 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {
    @Resource
    private DeviceService deviceService;
    @Resource
    private DeviceFactorService deviceFactorService;
    @Resource
    WarningRuleService warningRuleService;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private SiteMapper siteMapper;

    @Override
    public Boolean checkName(Site site) {
        Boolean f=true;
        Long id=site.getId();
        String name=site.getName();
        List<Site> sites=this.list(new LambdaQueryWrapper<Site>().
                eq(Site::getName, name));
        if (null==id){
            if (null!=sites&&sites.size()>0){
                f=false;
                throw new UnifiedException("该站点名称已存在！");
            }
        }
        else if (null!=id){
            if (!id.equals(sites.get(0).getId())){
                f=false;
                throw new UnifiedException("该站点名称已存在！");
            }
        }
        return f;
    }

    @Override
    public Boolean checkCode(Site site) {
        Boolean f=true;
        Long id=site.getId();
        String code=site.getCode();
        if (null!=code&&!"".equals(code)) {
            List<Site> sites = this.list(new LambdaQueryWrapper<Site>().
                    eq(Site::getCode, code));
            if (null == id) {
                if (null != sites && sites.size() > 0) {
                    f = false;
                    throw new UnifiedException("该站点编号已存在！");
                }
            } else if (null != id) {
                if (!id.equals(sites.get(0).getId())) {
                    f = false;
                    throw new UnifiedException("该站点编号已存在！");
                }
            }
        }
        return f;
    }

    @Override
    public boolean saveOrUpdateOthers(SiteDTO siteDTO) {
        boolean f = false;
        Long deviceId = null;
        List<DeviceDTO> deviceDTOS = siteDTO.getDeviceDTOS();
        if (null != deviceDTOS) {
            for (DeviceDTO deviceDTO : deviceDTOS) {
                if (null != deviceDTO.getDeviceName() && !"".equals(deviceDTO.getDeviceName())) {
                    //该站点设备信息
                    Device device = new Device();
                    if (deviceDTO.getDeviceId() != null) {
                        device.setId(deviceDTO.getDeviceId());
                    }
                    //这里要判断 设备编码是否重复
                    if (isDeviceCodeSame(deviceDTO)==false) {
                        throw new UnifiedException("该设备编码已存在！");
                    }
                        device.setCode(deviceDTO.getDeviceCode());
                        device.setName(deviceDTO.getDeviceName());
                        if (null!=siteDTO.getId()){
                            device.setSiteId(siteDTO.getId());
                            deviceService.saveOrUpdate(device);
                            deviceId = device.getId();
                            f = true;
                        }
                }
                List<DeviceFactorDTO> deviceFactorDTOS = deviceDTO.getDeviceFactorDTOS();
                List<DeviceFactor> deviceFactors = new ArrayList<>();

                if (null != deviceFactorDTOS && deviceFactorDTOS.size() > 0) {
                    List<WarningRule> warningRules = Lists.newArrayList();
                    for (DeviceFactorDTO deviceFactorDTO : deviceFactorDTOS) {
                        List<Long> deviceIds = new ArrayList<>();
                        if (StrUtil.isNotBlank(deviceFactorDTO.getFactorCode())) {
                            //该设备设备因子 信息
                            DeviceFactor deviceFactor = new DeviceFactor();
                            deviceFactor.setDeviceId(deviceId);
                            deviceFactor.setFactorId(deviceFactorDTO.getFactorId());
                            deviceIds.add(deviceId);
                            if (null != deviceFactorDTO.getDeviceFactorId()) {
                                //改的是 库里已存在的 设备因子的信息  没有更改设备类型
                                deviceFactor.setId(deviceFactorDTO.getDeviceFactorId());
                            } else {
                                //如果 更改了设备类型  则需要把该设备下已有的因子信息先删除
                                deleteByDeviceIds(deviceIds);
                            }
                            //这里要判断 因子编码是否重复
                            if (isFactorCodeSame(deviceFactorDTO)==false){
                                throw new UnifiedException("该因子编码已存在！");
                            }
                            deviceFactor.setFactorCode(deviceFactorDTO.getFactorCode());
                            deviceFactors.add(deviceFactor);
                            //同时保存泵站的因子预警规则记录
                            WarningRule warningRule = new WarningRule();
                            warningRule.setSiteId(siteDTO.getId());
                            warningRule.setFactorCode(deviceFactorDTO.getFactorCode());
                            warningRules.add(warningRule);
                        }
                    }
                    warningRuleService.saveBatch(warningRules);
                    deviceFactorService.saveOrUpdateBatch(deviceFactors);
                    f = true;
                }
            }
        } else {
            f = true;
        }
        return f;
    }

    public Boolean isDeviceCodeSame(DeviceDTO deviceDTO) {
        Long id = deviceDTO.getDeviceId();
        String deviceCode = deviceDTO.getDeviceCode();
        List<Device> devices = deviceService.list(new QueryWrapper<Device>().lambda().eq(Device::getCode, deviceCode));
        Boolean f=false;
        if (devices.size()>0) {
            if (null != id  && id.equals(devices.get(0).getId())) {
                f = true;
            }
        }
        else {
            f=true;
        }
        return f;
    }
    public Boolean isFactorCodeSame(DeviceFactorDTO deviceFactorDTO){
        String factorCode=deviceFactorDTO.getFactorCode();
        List<DeviceFactor> deviceFactors=deviceFactorService.list(new QueryWrapper<DeviceFactor>().lambda().eq(DeviceFactor::getFactorCode, factorCode));
        Boolean f=false;
        if (deviceFactors.size()==0){
            f=true;
        }
        return f;
    }
    @Override
    public boolean selectAllBySiteId(Long siteId) {
        boolean f = false;
        List<Device> devices = deviceMapper.selectAllBySiteId(siteId);
        if (null != devices && devices.size() > 0) {
            f = true;
        }
        return f;
    }

    @Override
    public IPage<Site> selectBySiteName(Page<Site> page, String keywords) {
        return siteMapper.selectBySiteName(page, keywords);
    }

    @Override
    public IPage<SiteDTO> selectByKeywords(Page<SiteDTO> page, String keywords) {
        return siteMapper.selectByKeywords(page, keywords);
    }

    @Override
    public List<SiteDTO> selectBySiteId(Long siteId) {

        return siteMapper.selectBySiteId(siteId);
    }

    @Override
    public Boolean deleteByDeviceIds(List<Long> deviceIds) {
        return siteMapper.deleteByDeviceIds(deviceIds);
    }

    @Override
    public List<Site> getSiteList() {
        List<Site> list = siteMapper.selectList(null);
        return list;
    }
}
