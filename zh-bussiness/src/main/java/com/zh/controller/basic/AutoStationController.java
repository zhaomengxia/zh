package com.zh.controller.basic;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.AutoStation;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.AutoStationService;
import com.zh.service.excel.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 自动监测站 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
@RestController
@RequestMapping("/autoStation")
@Api(description = "基础信息-自动监测站")
public class AutoStationController {

    @Resource
    AutoStationService autoStationService;
    @Resource
    private ImportService importService;
    /**
     *
     * @param page
     * @param keywords
     * @return
     */
    @GetMapping(value = "getListPage")
    @ApiOperation(value = "分页查询")
    @Log(desc = "分页查询")
    public Result getListsPage(Page<AutoStation> page, String keywords){
        return autoStationService.getPageList(page, keywords);
    }

    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改")
    @Log(desc = "新增或修改")
    public Result addOrUpdate(@RequestBody AutoStation station){
        if(station.getId() == null){
            autoStationService.save(station);
            return Result.success("新增成功!");
        }else {
            autoStationService.updateById(station);
            return Result.success("修改成功!");
        }
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    @Log(desc = "删除")
    public Result delete(@RequestBody List<Long> ids){
        autoStationService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "自动监测站信息导入")
    @PostMapping("/station")
    @Log(desc = "自动监测站信息导入")
    public Result station(MultipartFile file) throws IOException {
        if (importService.importAutoStation(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "自动监测站excel导出")
    @GetMapping("/excel")
    @Log(desc = "自动监测站excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            autoStationService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

