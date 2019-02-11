package com.zh.controller.warning;


import com.zh.api.Result;
import com.zh.service.warning.WarningSitePeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@RestController
@RequestMapping("/warningSitePeople")
@Api(description = "预警配置-预警人员配置")
public class WarningSitePeopleController {

    @Resource
    WarningSitePeopleService warningSitePeopleService;

    /**
     * 查询站点预警配置人员.
     * @param siteId
     * @return
     */
    @GetMapping(value = "getSiteWarningPeople")
    @ApiOperation(value = "查询站点预警配置人员")
    public Result getSiteWarningPeople(Long siteId){
        return warningSitePeopleService.getSiteWarningPeople(siteId);
    }

    /**
     * 新增或修改站点预警人员配置.
     * @param headQuarters
     * @param department
     * @param siteId
     * @return
     */
    @GetMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改站点预警人员配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headQuarters", value = "防汛指挥部id"),
            @ApiImplicitParam(name = "department", value = "防汛责任人"),
            @ApiImplicitParam(name = "siteId", value = "站点id")
    })
    public Result addOrUpdate(String headQuarters, String department, Long siteId){
        return warningSitePeopleService.addOrUpdate(headQuarters, department, siteId);
    }
}

