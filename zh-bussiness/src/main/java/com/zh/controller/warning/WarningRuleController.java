package com.zh.controller.warning;


import com.zh.api.Result;
import com.zh.dto.warning.FactorDegreeWarningDTO;
import com.zh.dto.warning.SiteFactorDTO;
import com.zh.dto.warning.WarningDetailDTO;
import com.zh.entity.warning.WarningDegree;
import com.zh.mapper.sys.FactorMapper;
import com.zh.service.warning.WarningRuleDetailService;
import com.zh.service.warning.WarningRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 预警规则表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Validated
@RestController
@RequestMapping("/warningRule")
@Api(description = "预警规则管理")
public class WarningRuleController {

    @Resource
    FactorMapper factorMapper;

    @Resource
    WarningRuleService warningRuleService;

    @GetMapping(value = "getSiteFactorList")
    @ApiOperation(value = "获取泵站因子列表")
    public List<SiteFactorDTO> getSiteFactorList(Long siteId, String factorCode){
        return warningRuleService.getSiteFactorWarningRule(siteId, factorCode);
    }

    /**
     * 新增因子预警规则.
     * @param factorDegreeWarningDTO
     * @param ruleId
     * @return
     */
    @PostMapping(value = "addSiteFactorRule")
    public Result addSiteFactorRule(@RequestBody @Valid List<FactorDegreeWarningDTO> factorDegreeWarningDTO, Long ruleId){
        return warningRuleService.addSiteFactorRule(factorDegreeWarningDTO, ruleId);
    }

    /**
     * 修改因子预警规则.
     * @param factorDegreeWarningDTOS
     * @param ruleId
     * @return
     */
    @PostMapping(value = "updateSiteFactorRule")
    public Result updateSiteFactorRule(@RequestBody @Valid List<FactorDegreeWarningDTO> factorDegreeWarningDTOS, Long ruleId){
        return warningRuleService.updateSiteFactorRule(factorDegreeWarningDTOS, ruleId);
    }

    @ApiOperation(value = "获取雨水预警时间区间")
    @GetMapping(value = "getRainWarningTimeSection")
    public Result getRainWarningTimeSection(){
        return warningRuleService.getRainWarningTimeSection();
    }

    @GetMapping(value = "judgeWarningDegree")
    @ApiOperation(value = "判断预警等级")
    public WarningDegree judgeWarningDegree(Long siteId, String factorCode, Integer timeZone, Double value){
        return warningRuleService.judgeWarningDegree(siteId, factorCode, timeZone, value);
    }
}

