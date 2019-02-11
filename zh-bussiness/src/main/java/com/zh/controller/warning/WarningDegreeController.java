package com.zh.controller.warning;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.contants.RedisContant;
import com.zh.entity.warning.WarningDegree;
import com.zh.service.warning.WarningDegreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 预警等级表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@RestController
@RequestMapping("/warningDegree")
@Api(description = "预警等级管理")
public class WarningDegreeController {

    @Resource
    WarningDegreeService warningDegreeService;

    /**
     * 新增或修改预警等级
     * @param warningDegree
     * @return
     */
    @ApiOperation(value = "新增或修改预警等级")
    @PostMapping("/addOrUpdateWarningDegree")
    @CacheEvict(cacheNames = RedisContant.WARNING_DEGREE, allEntries = true)
    public Result addOrUpdateWarningDegree(@RequestBody WarningDegree warningDegree){
        if(warningDegree.getId() == null){
            warningDegreeService.save(warningDegree);
        }else {
            warningDegreeService.updateById(warningDegree);
        }
        return Result.success("操作成功!");
    }

    /**
     * 分页查询.
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询预警等级")
    @GetMapping(value = "/getWarningDegreePage")
    public Result getWarningDegreePage(Page<WarningDegree> page){
        IPage<WarningDegree> warningDegrees = warningDegreeService.queryPage(page);
        return Result.success(warningDegrees);
    }

    /**
     * 获取预警等级列表
     * @return
     */
    @ApiOperation(value = "获取预警等级列表")
    @GetMapping(value = "/getWarningDegreeList")
    public List<WarningDegree> getWarningDegreeList(){
        return warningDegreeService.list();
    }

    /**
     * 删除预警等级.
     * @param id
     * @return
     */
    @ApiOperation(value = "删除预警等级")
    @PostMapping(value = "/deleteWarningDegree")
    public Result deleteWarningDegree(@RequestBody List<Long> id){
        boolean flag = warningDegreeService.removeByIds(id);
        if(flag){
            return Result.success("删除成功!");
        }else {
            return Result.fail("删除失败!");
        }
    }

}

