package com.zh.controller.basic;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.Material;
import com.zh.entity.basic.Warehouse;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.MaterialService;
import com.zh.service.basic.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 物资表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
@RestController
@RequestMapping("/material")
@Api(description = "防汛资源-防汛物资")
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @Resource
    private WarehouseService warehouseService;

    @PostMapping(value = "/saveOrUpdateMaterial")
    @ApiOperation(value = "新增或修改某物资存放点下物资信息")
    @Log(desc = "新增或修改某物资存放点下物资信息")
    public Result saveOrUpdateMaterial(@RequestBody @Valid Material material) {
        if (materialService.saveOrUpdate(material)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllMaterialByMessage/page")
    @ApiOperation(value = "获得某物资存放点下物资信息列表")
    @Log(desc = "获得某物资存放点下物资信息列表")
    public Result selectAllMaterialByMessage(Page<Material> page, String keywords, Long warehourseId) {
        if (null == warehourseId) {
            return Result.fail("请传物资存放点！");
        }
        return Result.success(materialService.selectAllByMessage(page, keywords, warehourseId));
    }


    @ApiOperation(value = "物资excel导出")
    @GetMapping("/excel")
    @Log(desc = "物资excel导出")
    public void excel(HttpServletResponse response, Long id, String keywords) {
        try {
            materialService.exportExcel(response, id,keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }


    @ApiOperation(value = "删除物资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物资主键")
    })
    @DeleteMapping("/deleteMaterial/{id}")
    @Log(desc = "删除物资")
    public Result deleteMaterial(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());

        if (materialService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @PostMapping(value = "/saveOrUpdateWarehouse")
    @ApiOperation(value = "新增或修改物资存放点")
    @Log(desc = "新增或修改物资存放点")
    public Result saveOrUpdateWarehouse(@RequestBody @Valid Warehouse warehouse) {
        if (warehouseService.saveOrUpdate(warehouse)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllWarehouseByMessage/page")
    @ApiOperation(value = "获得物资存放点信息列表")
    @Log(desc = "获得物资存放点信息列表")
    public Result selectAllWarehouseByMessage(Page<Warehouse> page, String keywords) {
        return Result.success(warehouseService.selectAllByMessage(page, keywords));
    }


    @ApiOperation(value = "删除物资存放点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物资存放点主键")
    })
    @DeleteMapping("/deleteWarehouse/{id}")
    @Log(desc = "删除物资存放点")
    public Result deleteWarehouse(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        for (Long id1:idList) {
            List<Material> materials = materialService.list(new LambdaQueryWrapper<Material>().eq(Material::getWarehourseId, id1));
            if (null != materials && materials.size() > 0) {
                return Result.fail("删除失败，因该物资存放点下还有物资！");
            }
        }
        if (warehouseService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }


}

