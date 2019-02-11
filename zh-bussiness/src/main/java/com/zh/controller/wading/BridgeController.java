package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.BridgeDTO;
import com.zh.entity.wading.Bridge;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.BridgeService;
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
 * 桥梁 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/bridge")
@Api(description = "涉水工程-桥梁管理")
public class BridgeController {

    @Resource
    private BridgeService bridgeService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改桥梁信息")
    @Log(desc = "新增或修改桥梁信息")
    public Result addOrUpdate(@RequestBody @Valid Bridge bridge) {
        if (StrUtil.isBlank(bridge.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (bridgeService.saveOrUpdate(bridge)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获得桥梁信息列表")
    @Log(desc = "获得桥梁信息列表")
    public Result selectAllByMessage(Page<BridgeDTO> page, String keywords) {
        return Result.success(bridgeService.selectAllByMessage(page, keywords));
    }

    @ApiOperation(value = "删除桥梁")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "桥梁主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除桥梁")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (bridgeService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "桥梁信息导入")
    @PostMapping("/bridge")
    @Log(desc = "桥梁信息导入")
    public Result bridge(MultipartFile file) throws IOException {
        if (importService.importBridge(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "桥梁excel导出")
    @GetMapping("/excel")
    @Log(desc = "桥梁excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            bridgeService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }
}

