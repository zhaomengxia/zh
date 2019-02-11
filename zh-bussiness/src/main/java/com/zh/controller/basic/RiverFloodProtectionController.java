package com.zh.controller.basic;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.RiverFloodProtection;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.RiverFloodProtectionService;
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
 * 中小型河流抗洪能力 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@RestController
@RequestMapping("/riverFloodProtection")
@Api(description = "基础信息-中小河流现状防洪能力")
public class RiverFloodProtectionController {

    @Resource
    RiverFloodProtectionService riverFloodProtectionService;
    @Resource
    private ImportService importService;
    /**
     * 获取中小河流现状防洪能力列表
     * @param page
     * @param keywords
     * @return
     */
    @GetMapping(value = "getListPage")
    @ApiOperation(value = "获取列表")
    public Result getListPage(Page<RiverFloodProtection> page, String keywords){
        return riverFloodProtectionService.getListPage(page, keywords);
    }

    /**
     * 新增或修改中小河流现状防洪能力
     * @param riverFloodProtection
     * @return
     */
    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改")
    @Log(desc = "新增或修改中小河流现状防洪能力")
    public Result addOrUpdate(@RequestBody RiverFloodProtection riverFloodProtection){
        if( riverFloodProtection.getId() == null){
            riverFloodProtectionService.save(riverFloodProtection);
            return Result.success("新增成功!");
        }else {
            riverFloodProtectionService.updateById(riverFloodProtection);
            return Result.success("修改成功!");
        }
    }

    /**
     * 删除中小河流现状防洪能力
     * @param ids
     * @return
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    @Log(desc = "删除中小河流现状防洪能力")
    public Result delete(@RequestBody List<Long> ids){
        riverFloodProtectionService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "中小河流现状防洪能力信息导入")
    @PostMapping("/river")
    @Log(desc = "中小河流现状防洪能力信息导入")
    public Result river(MultipartFile file) throws IOException {
        if (importService.importRiverFlood(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "中小河流现状防洪能力excel导出")
    @GetMapping("/excel")
    @Log(desc = "中小河流现状防洪能力excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            riverFloodProtectionService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }



}

