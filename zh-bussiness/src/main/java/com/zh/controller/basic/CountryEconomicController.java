package com.zh.controller.basic;


import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.basic.CountryEconomic;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.CountryEconomicService;
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
 *  前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@RestController
@RequestMapping("/countryEconomic")
@Api(description =   "基础信息-县(市、区)社会经济")
public class CountryEconomicController {

    @Resource
    CountryEconomicService countryEconomicService;
    @Resource
    private ImportService importService;
    /**
     * 获取县(市、区)社会经济列表.
     * @param keywords
     * @return
     */
    @GetMapping(value = "getListPage")
    @ApiOperation(value = "获取县(市、区)社会经济列表")
    public Result getListPage(String keywords){
        return countryEconomicService.getListPage(keywords);
    }

    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增或修改")
    @Log(desc = "新增或修改县(市、区)社会经济")
    public Result addOrUpdate(@RequestBody CountryEconomic countryEconomic){
        if(countryEconomic.getId() == null){
            countryEconomicService.save(countryEconomic);
            return Result.success("新增成功!");
        }else {
            countryEconomicService.updateById(countryEconomic);
            return Result.success("修改成功!");
        }

    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody List<Long> ids){
        countryEconomicService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "县(市、区)社会经济信息导入")
    @PostMapping("/country")
    @Log(desc = "县(市、区)社会经济信息导入")
    public Result country(MultipartFile file) throws IOException {
        if (importService.importCountry(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "县(市、区)社会经济excel导出")
    @GetMapping("/excel")
    @Log(desc = "县(市、区)社会经济excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            countryEconomicService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

