package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.PumpStationDTO;
import com.zh.entity.wading.PumpStation;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.PumpStationService;
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
 * 泵站 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@RestController
@RequestMapping("/pumpStation")
@Api(description = "涉水工程-泵站管理")
public class PumpStationController {

    @Resource
    private PumpStationService pumpStationService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "添加或修改泵站信息")
    @Log(desc = "添加或修改泵站信息")
    public Result addOrUpdate(@RequestBody @Valid PumpStation pumpStation) {
        if (StrUtil.isBlank(pumpStation.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (pumpStationService.saveOrUpdate(pumpStation)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获得泵站信息列表")
    @Log(desc = "获得泵站信息列表")
    public Result selectAllByMessage(Page<PumpStationDTO> page, String keywords) {
        return Result.success(pumpStationService.selectAllByMessage(page, keywords));
    }

    @ApiOperation(value = "删除泵站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "泵站主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除泵站")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (pumpStationService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "泵站信息导入")
    @Log(desc = "泵站信息导入")
    @PostMapping("/station")
    public Result station(MultipartFile file) throws IOException {
        if (importService.importStation(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "泵站excel导出")
    @GetMapping("/excel")
    @Log(desc = "泵站excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            pumpStationService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }


}

