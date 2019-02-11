package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.DikeDTO;
import com.zh.entity.wading.Dike;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.DikeService;
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
 * 堤防 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/dike")
@Api(description = "涉水工程-堤防管理")
public class DikeController {

    @Resource
    private DikeService dikeService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "添加或修改堤防信息")
    @Log(desc = "添加或修改堤防信息")
    public Result addOrUpdate(@RequestBody @Valid Dike dike) {
        if (StrUtil.isBlank(dike.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (dikeService.saveOrUpdate(dike)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获得堤防信息列表")
    @Log(desc = "获得堤防信息列表")
    public Result selectAllByMessage(Page<DikeDTO> page, String keywords) {
        return Result.success(dikeService.selectAllByMessage(page, keywords));
    }

    @ApiOperation(value = "删除堤防")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "堤防主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除堤防")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (dikeService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "堤防信息导入")
    @PostMapping("/dike")
    @Log(desc = "堤防信息导入")
    public Result dike(MultipartFile file) throws IOException {
        if (importService.importDike(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @GetMapping("/excel")
    @ApiOperation(value = "excel导出")
    @Log(desc = "堤防excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            dikeService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

