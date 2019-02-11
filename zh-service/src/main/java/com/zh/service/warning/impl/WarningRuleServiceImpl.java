package com.zh.service.warning.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.zh.api.Result;

import com.zh.contants.RedisContant;
import com.zh.dto.warning.FactorDegreeWarningDTO;
import com.zh.dto.warning.SiteFactorDTO;
import com.zh.dto.warning.WarningDetailDTO;
import com.zh.entity.warning.WarningDegree;
import com.zh.entity.warning.WarningRule;
import com.zh.entity.warning.WarningRuleDetail;
import com.zh.mapper.warning.RainWarningTimeSectionMapper;
import com.zh.mapper.warning.WarningRuleDetailMapper;
import com.zh.mapper.warning.WarningRuleMapper;
import com.zh.service.warning.WarningDegreeService;
import com.zh.service.warning.WarningRuleDetailService;
import com.zh.service.warning.WarningRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 预警规则表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Service
public class WarningRuleServiceImpl extends ServiceImpl<WarningRuleMapper, WarningRule> implements WarningRuleService {

    @Resource
    WarningRuleMapper warningRuleMapper;

    @Resource
    WarningRuleDetailService warningRuleDetailService;

    @Resource
    RainWarningTimeSectionMapper rainWarningTimeSectionMapper;

    @Resource
    WarningRuleDetailMapper warningRuleDetailMapper;

    @Resource
    WarningDegreeService warningDegreeService;

    @Override
    public List<SiteFactorDTO> getSiteFactorWarningRule(Long siteId, String factorCode) {
        List<WarningDetailDTO> list = warningRuleMapper.getSiteFactorWarningRule(siteId, factorCode);
        if (list.size() > 0) {
            //根据规则id分组.
            Map<Long, List<WarningDetailDTO>> ruleMap =
                    list.parallelStream().collect(Collectors.groupingBy(WarningDetailDTO::getRuleId));
            List<SiteFactorDTO> siteFactorDTOS = Lists.newArrayList();
            ruleMap.forEach((ruleId, factorDegreeWarnings) -> {
                if (factorDegreeWarnings.size() > 0) {
                    WarningDetailDTO warningDetailDTO = factorDegreeWarnings.get(0);
                    String factorName = warningDetailDTO.getFactorName();
                    String code = warningDetailDTO.getFactorCode();
                    SiteFactorDTO siteFactorDTO = new SiteFactorDTO();
                    siteFactorDTO.setFactorCode(code);
                    siteFactorDTO.setFactorName(factorName);
                    siteFactorDTO.setRuleId(ruleId);
                    siteFactorDTO.setSiteId(siteId);
                    //根据预警等级分组.
                    List<FactorDegreeWarningDTO> factorDegreeWarningDTOS = Lists.newArrayList();
                    if (factorDegreeWarnings.size() > 0 && factorDegreeWarnings.get(0).getWarningDegreeId() != null) {
                        Map<Long, List<WarningDetailDTO>> warningDegreeMap =
                                factorDegreeWarnings.parallelStream().collect(Collectors.groupingBy(WarningDetailDTO::getWarningDegreeId));
                        warningDegreeMap.forEach((degreeId, degreeList) -> {
                            FactorDegreeWarningDTO factorDegreeWarningDTO = new FactorDegreeWarningDTO();
                            factorDegreeWarningDTO.setRuleId(ruleId);
                            factorDegreeWarningDTO.setWarningDegreeId(degreeId);
                            factorDegreeWarningDTO.setWarningDetailDTOS(degreeList);
                            if (degreeList.size() > 0) {
                                factorDegreeWarningDTO.setWarningDegreeName(degreeList.get(0).getWarningDegreeName());
                            }

                            factorDegreeWarningDTOS.add(factorDegreeWarningDTO);
                        });
                        siteFactorDTO.setHasAllocation(true);
                    } else {
                        siteFactorDTO.setHasAllocation(false);
                    }

                    siteFactorDTO.setFactorDegreeWarningDTOS(factorDegreeWarningDTOS);
                    siteFactorDTOS.add(siteFactorDTO);
                }
            });
            return siteFactorDTOS;
        }
        return new ArrayList<>();
    }

    @Override
    @CacheEvict(cacheNames = RedisContant.WARNING_RULE, allEntries = true)
    public Result addSiteFactorRule(List<FactorDegreeWarningDTO> factorDegreeWarningDTO, Long ruleId) {
        if (factorDegreeWarningDTO.size() == 0 || ruleId == null) {
            return Result.fail("信息不完整!");
        }
        List<WarningRuleDetail> list = Lists.newArrayList();
        factorDegreeWarningDTO.forEach(a -> {
            List<WarningDetailDTO> warningDetailDTOS = a.getWarningDetailDTOS();
            Long warningDegreeId = a.getWarningDegreeId();
            warningDetailDTOS.forEach(warningDetailDTO -> {
                WarningRuleDetail warningRuleDetail = new WarningRuleDetail();
                BeanUtil.copyProperties(warningDetailDTO, warningRuleDetail);
                warningRuleDetail.setWarningDegree(warningDegreeId);
                warningRuleDetail.setRuleId(ruleId);
                list.add(warningRuleDetail);
            });
        });
        warningRuleDetailService.saveBatch(list);
        return Result.success("添加成功!");
    }

    @Override
    @CacheEvict(cacheNames = RedisContant.WARNING_RULE, allEntries = true)
    public Result updateSiteFactorRule(List<FactorDegreeWarningDTO> factorDegreeWarningDTO, Long ruleId) {
        if (factorDegreeWarningDTO.size() == 0 || ruleId == null) {
            return Result.fail("信息不完整!");
        }
        List<WarningRuleDetail> list = Lists.newArrayList();
        List<WarningRuleDetail> newList = Lists.newArrayList();
        List<WarningRuleDetail> deleteList = Lists.newArrayList();
        factorDegreeWarningDTO.forEach(a -> {
            List<WarningDetailDTO> warningDetailDTOS = a.getWarningDetailDTOS();
            Long warningDegreeId = a.getWarningDegreeId();
            warningDetailDTOS.forEach(warningDetailDTO -> {
                WarningRuleDetail warningRuleDetail = new WarningRuleDetail();
                BeanUtil.copyProperties(warningDetailDTO, warningRuleDetail);
                warningRuleDetail.setRuleId(ruleId);
                warningRuleDetail.setWarningDegree(warningDegreeId);
                if (warningDetailDTO.getDetailId() != null) {
                    warningRuleDetail.setId(warningDetailDTO.getDetailId());
                    if (warningRuleDetail.getDeleted()) {
                        deleteList.add(warningRuleDetail);
                    } else {
                        list.add(warningRuleDetail);
                    }

                } else {
                    newList.add(warningRuleDetail);
                }

            });
        });
        if (list.size() > 0) {
            warningRuleDetailService.updateBatchById(list);
        }
        if (newList.size() > 0) {
            warningRuleDetailService.saveBatch(newList);
        }
        if (deleteList.size() > 0) {
            warningRuleDetailService.removeByIds(deleteList.stream().map(WarningRuleDetail::getId).distinct().collect(Collectors.toList()));
        }
        return Result.success("修改成功!");
    }

    @Override
    public Result getRainWarningTimeSection() {
        return Result.success(rainWarningTimeSectionMapper.getList());
    }


    @Override
    public WarningDegree judgeWarningDegree(Long siteId, String factorCode, Integer timeZone, Double value) {
        List<WarningRuleDetail> ruleDetail = this.getRuleDetail(siteId, factorCode);
        if(CollUtil.isEmpty(ruleDetail)){
            return null;
        }
        if (timeZone != null) {
            ruleDetail = ruleDetail.stream().filter(a -> timeZone.equals(a.getTimeZone())).collect(Collectors.toList());
        }
        Long warningDegree = null;
        for (WarningRuleDetail warningRuleDetail : ruleDetail) {
            Double warningValue = warningRuleDetail.getWarningValue();
            if (value >= warningValue) {
                warningDegree = warningRuleDetail.getWarningDegree();
            }
        }
        if(warningDegree != null){
            WarningDegree result = warningDegreeService.getById(warningDegree);
            return result;
        }else {
            return null;
        }
    }

    @Cacheable(cacheNames = RedisContant.WARNING_RULE, key = "#factorCode")
    public List<WarningRuleDetail> getRuleDetail(Long siteId, String factorCode) {
        List<WarningDetailDTO> siteFactorWarningRule = warningRuleMapper.getSiteFactorWarningRule(siteId, factorCode);
        if (CollUtil.isNotEmpty(siteFactorWarningRule)) {
            List<WarningRuleDetail> warningRuleDetails =
                    warningRuleDetailMapper.getDetailByRuleId(siteFactorWarningRule.get(0).getRuleId());
            return warningRuleDetails;
        }
        return null;
    }
}
