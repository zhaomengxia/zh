package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.WaterDTO;
import com.zh.entity.wading.Water;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.WaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 水库 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@RestController
@RequestMapping("/water")
@Api(description = "涉水工程-水库管理")
public class WaterController {

    @Resource
    private WaterService waterService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改水库信息")
    @Log(desc = "新增或修改水库信息")
    public Result addOrUpdate(@RequestBody @Valid Water water) {
        if (StrUtil.isBlank(water.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (waterService.saveOrUpdate(water)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获取水库信息列表")
    @Log(desc = "获取水库信息列表")
    public Result selectAllByMessage(Page<WaterDTO> page,String keywords) {
        return Result.success(waterService.selectbyMessage(page, keywords));
    }


    @ApiOperation(value = "删除水库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "水库主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除水库")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (waterService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "水库导入")
    @Log(desc = "水库导入")
    @PostMapping("/water")
    public Result water(MultipartFile file) throws IOException {
        if (importService.importWater(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "水塘excel导出")
    @GetMapping("/excel")
    @Log(desc = "水塘excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            waterService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

