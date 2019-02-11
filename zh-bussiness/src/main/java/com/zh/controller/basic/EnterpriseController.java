package com.zh.controller.basic;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.basic.EnterpriseDTO;
import com.zh.entity.basic.Enterprise;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.EnterpriseService;
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
@RequestMapping("/enterprise")
@Api(description = "基础信息-企事业单位")
public class EnterpriseController {

    @Resource
    EnterpriseService enterpriseService;
    @Resource
    private ImportService importService;
    /**
     * 分页查询企事业单位.
     * @param page
     * @param keywords
     * @return
     */
    @GetMapping(value = "getEnterpriseListPage")
    @ApiOperation(value = "分页查询企事业单位")
    @Log(desc = "分页查询企事业单位")
    public Result getEnterpriseListPage(Page<EnterpriseDTO> page, String keywords){
        return enterpriseService.getEnterpriseListPage(page, keywords);
    }

    /**
     * 新增或修改企事业单位.
     * @param enterpriseDTO
     * @return
     */
    @PostMapping(value = "addOrUpdateEnterprise")
    @ApiOperation(value = "新增或修改企事业单位")
    @Log(desc="新增或修改企事业单位")
    public Result addOrUpdateEnterprise(@RequestBody EnterpriseDTO enterpriseDTO){
        if(enterpriseDTO.getId() == null){
            Enterprise enterprise = new Enterprise();
            BeanUtil.copyProperties(enterpriseDTO, enterprise);
            enterprise.setFloodThreat(enterpriseDTO.getFloodThreat());
            enterprise.setWaterlogged(enterpriseDTO.getWaterlogged());
            enterpriseService.save(enterprise);
            return Result.success("新增成功!");
        }else {
            Enterprise enterprise = new Enterprise();
            BeanUtil.copyProperties(enterpriseDTO, enterprise);
            enterprise.setFloodThreat(enterpriseDTO.getFloodThreat());
            enterprise.setWaterlogged(enterpriseDTO.getWaterlogged());
            enterpriseService.updateById(enterprise);
            return Result.success("修改成功!");
        }
    }

    /**
     * 删除成功.
     * @param ids
     * @return
     */
    @PostMapping(value = "deleteEnterprise")
    @ApiOperation(value = "删除企事业单位")
    @Log(desc = "删除企事业单位")
    public Result deleteEnterprise(@RequestBody List<Long> ids){
        enterpriseService.removeByIds(ids);
        return Result.success("删除成功!");
    }

    @ApiOperation(value = "企事业单位信息导入")
    @PostMapping("/enterprise")
    @Log(desc = "企事业单位信息导入")
    public Result polder(MultipartFile file) throws IOException {
        if (importService.importEnterprise(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "企事业单位excel导出")
    @GetMapping("/excel")
    @Log(desc = "企事业单位excel导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            enterpriseService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

