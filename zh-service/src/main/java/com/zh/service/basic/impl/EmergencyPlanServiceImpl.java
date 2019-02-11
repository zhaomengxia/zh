package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.EmergencyPlanDTO;
import com.zh.entity.basic.EmergencyPlan;
import com.zh.mapper.basic.EmergencyPlanMapper;
import com.zh.service.basic.EmergencyPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 应急预案表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
@Service
public class EmergencyPlanServiceImpl extends ServiceImpl<EmergencyPlanMapper, EmergencyPlan> implements EmergencyPlanService {

    @Resource
    private EmergencyPlanMapper emergencyPlanMapper;
    @Override
    public Boolean deletedByPlanTypeId(Long planTypeId) {
        return emergencyPlanMapper.deletedByPlanTypeId(planTypeId);
    }

    @Override
    public IPage<EmergencyPlanDTO> selectAllByTypeIdAndName(Page<EmergencyPlanDTO> page, Long id, String keywords) {
        return emergencyPlanMapper.selectAllByTypeIdAndName(page,id,keywords);
    }

//    @Override
//    public IPage<EmergencyPlanDTO> selectAllByTypeIdAndName(Page<EmergencyPlanDTO> page, Long planTypeId, String planName) {
//        return emergencyPlanMapper.selectAllByTypeIdAndName(page,planTypeId,planName);
//    }

}
