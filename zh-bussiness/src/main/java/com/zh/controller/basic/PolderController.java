package com.zh.controller.basic;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.Polder;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.PolderService;
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
 * 圩区 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
@RestController
@RequestMapping("/polder")
@Api(description = "基础信息-圩区")
public class PolderController {

    @Resource
    PolderService polderService;

    @Resource
    private ImportService importService;
    @GetMapping(value = "getListPage")
    @ApiOperation(value = "分页查询")
    public Result getListPage(Page<Polder> page, String keywords){
        return polderService.getListPage(page, keywords);
    }

    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改")
    @Log(desc = "新增或修改圩区信息")
    public Result addOrUpdate(@RequestBody Polder polder){
        if(polder.getId() == null){
            polderService.save(polder);
            return Result.success("新增成功!");
        }else {
            polderService.updateById(polder);
            return Result.success("修改成功!");
        }
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    @Log(desc = "删除圩区信息")
    public Result delete(@RequestBody List<Long> ids){
        polderService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "圩区信息导入")
    @PostMapping("/polder")
    @Log(desc = "圩区信息导入")
    public Result polder(MultipartFile file) throws IOException {
        if (importService.importPolder(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "圩区excel导出")
    @GetMapping("/excel")
    @Log(desc = "圩区excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            polderService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }
}

