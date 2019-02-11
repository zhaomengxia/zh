package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.basic.EmergencyPlanDTO;
import com.zh.entity.basic.EmergencyPlan;

/**
 * <p>
 * 应急预案表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
public interface EmergencyPlanService extends IService<EmergencyPlan> {
    Boolean deletedByPlanTypeId(Long planTypeId);

    IPage<EmergencyPlanDTO> selectAllByTypeIdAndName(Page<EmergencyPlanDTO> page, Long id, String keywords);

//    IPage<EmergencyPlanDTO> selectAllByTypeIdAndName(Page<EmergencyPlanDTO> page, Long planTypeId, String planName);
}
