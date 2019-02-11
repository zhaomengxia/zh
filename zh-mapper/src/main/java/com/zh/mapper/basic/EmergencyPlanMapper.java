package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.EmergencyPlanDTO;
import com.zh.entity.basic.EmergencyPlan;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 应急预案表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
public interface EmergencyPlanMapper extends BaseMapper<EmergencyPlan> {
    Boolean deletedByPlanTypeId(Long planTypeId);

    IPage<EmergencyPlanDTO> selectAllByTypeIdAndName(Page<EmergencyPlanDTO> page, @Param("id") Long id, @Param("keywords") String keywords);
}
