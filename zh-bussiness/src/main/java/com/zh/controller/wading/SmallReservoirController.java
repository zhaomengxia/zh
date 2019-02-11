package com.zh.controller.wading;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.wading.SmallReservoirDTO;
import com.zh.entity.wading.SmallReservoir;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.wading.SmallReservoirService;
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
 * 塘坝 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/smallReservoir")
@Api(description = "涉水工程-塘坝管理")
public class SmallReservoirController {

    @Resource
    private SmallReservoirService smallReservoirService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改塘坝信息")
    @Log(desc = "新增或修改塘坝信息")
    public Result addOrUpdate(@RequestBody @Valid SmallReservoir smallReservoir) {
        if (StrUtil.isBlank(smallReservoir.getAreaCode())){
            return Result.fail("请传行政区划！");
        }
        if (smallReservoirService.saveOrUpdate(smallReservoir)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @GetMapping(value = "/selectAllByMessage/page")
    @ApiOperation(value = "获取塘坝信息列表")
    @Log(desc = "获取塘坝信息列表")
    public Result selectAllByMessage(Page<SmallReservoirDTO> page, String keywords) {
        return Result.success(smallReservoirService.selectbyMessage(page, keywords));
    }

    @ApiOperation(value = "删除塘坝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "塘坝主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除塘坝")
    public Result delete(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (smallReservoirService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "塘坝导入")
    @Log(desc = "塘坝导入")
    @PostMapping("/pond")
    public Result pond(MultipartFile file) throws IOException {
        if (importService.importPond(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "塘坝excel导出")
    @GetMapping("/excel")
    @Log(desc = "塘坝excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            smallReservoirService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }
}

