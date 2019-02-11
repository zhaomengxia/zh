package com.zh.mapper.warning;

import com.zh.entity.warning.WarningRuleDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预警信息详情 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WarningRuleDetailMapper extends BaseMapper<WarningRuleDetail> {

    /**
     * 根据规则id查询规则详情.
     * @param ruleId
     * @return
     */
    List<WarningRuleDetail> getDetailByRuleId(@Param("ruleId")Long ruleId);
}
