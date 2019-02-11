package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.WholeDTO;
import com.zh.entity.wading.Whole;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.WholeService;
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
 * 整体情况 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@RestController
@RequestMapping("/whole")
@Api(description = "涉水工程-整体情况")
public class WholeController {
    @Resource
    private WholeService wholeService;

    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改整体情况")
    @Log(desc = "新增或修改整体情况信息")
    public Result addOrUpdate(@RequestBody @Valid Whole whole) {
        if (StrUtil.isBlank(whole.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (wholeService.saveOrUpdate(whole)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获得整体情况信息列表")
    @Log(desc = "获得整体情况信息列表")
    public Result selectAllByMessage(Page<WholeDTO> page, String keywords) {
        return Result.success(wholeService.selectbyMessage(page, keywords));
    }

    @ApiOperation(value = "删除整体情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "整体情况主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除整体情况")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (wholeService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "整体情况信息导入")
    @PostMapping("/in")
    @Log(desc = "整体情况信息导入")
    public Result whole(MultipartFile file) throws IOException {
        if (importService.importWhole(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "整体情况excel导出")
    @GetMapping("/excel")
    @Log(desc = "整体情况excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            wholeService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

