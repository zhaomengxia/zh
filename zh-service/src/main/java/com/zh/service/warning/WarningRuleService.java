package com.zh.service.warning;

import com.zh.api.Result;
import com.zh.dto.warning.FactorDegreeWarningDTO;
import com.zh.dto.warning.SiteFactorDTO;
import com.zh.dto.warning.WarningDetailDTO;
import com.zh.entity.warning.WarningDegree;
import com.zh.entity.warning.WarningRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.entity.warning.WarningRuleDetail;

import java.util.List;

/**
 * <p>
 * 预警规则表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WarningRuleService extends IService<WarningRule> {

    /**
     * 获取站点因子预警规则.
     * @param siteId 泵站id
     * @param factorCode 因子编码.
     * @return
     */
    List<SiteFactorDTO> getSiteFactorWarningRule(Long siteId, String factorCode);

    /**
     * 新增站点因子预警规则详情.
     * @param factorDegreeWarningDTO
     * @param ruleId
     * @return
     */
    Result addSiteFactorRule(List<FactorDegreeWarningDTO> factorDegreeWarningDTO, Long ruleId);

    /**
     * 修改站点因子预警规则详情.
     * @param factorDegreeWarningDTOS
     * @param ruleId
     * @return
     */
    Result updateSiteFactorRule(List<FactorDegreeWarningDTO> factorDegreeWarningDTOS, Long ruleId);

    /**
     * 获取雨水预警时间区间
     * @return
     */
    Result getRainWarningTimeSection();


    public WarningDegree judgeWarningDegree(Long siteId, String factorCode, Integer timeZone, Double value);
}
