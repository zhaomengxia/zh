package com.zh.mapper.warning;

import com.zh.dto.warning.SiteFactorDTO;
import com.zh.dto.warning.WarningDetailDTO;
import com.zh.entity.warning.WarningRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预警规则表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WarningRuleMapper extends BaseMapper<WarningRule> {


    /**
     * 获取泵站因子预警规则信息.
     * @param siteId
     * @param factorCode
     * @return
     */
    List<WarningDetailDTO> getSiteFactorWarningRule(@Param("siteId") Long siteId,
                                                    @Param("factorCode")String factorCode);
}
