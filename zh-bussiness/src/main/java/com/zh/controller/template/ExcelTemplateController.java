package com.zh.controller.template;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.entity.template.ExcelTemplate;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.template.ExcelTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * excel模板表 前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-25
 */
@RestController
@RequestMapping("/excelTemplate")
@Api(description = "模板管理")
public class ExcelTemplateController {

    @Resource
    private ExcelTemplateService excelTemplateService;

    @ApiOperation(value = "分页展示模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模板名称，模糊匹配")
    })
    @GetMapping("/page")
    public Result page(Page<ExcelTemplate> page, String name) {
        return Result.success(excelTemplateService.page(page, new LambdaQueryWrapper<ExcelTemplate>().like(ExcelTemplate::getName, name)));
    }

    @ApiOperation(value = "根据模板名称获取模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模板名")
    })
    @GetMapping("/get/{name}")
    public Result get(@PathVariable("name") String name) {
        return Result.success(excelTemplateService.getOne(new LambdaQueryWrapper<ExcelTemplate>().eq(ExcelTemplate::getName, name)));
    }

    @ApiOperation(value = "新增/或者修改模板")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(ExcelTemplate excelTemplate) {
        if (excelTemplateService.saveOrUpdate(excelTemplate)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "单个删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键")
    })
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        if (excelTemplateService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "批量删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键字符用','分割")
    })
    @DeleteMapping("/delete/{ids}")
    public Result deleteBatch(@PathVariable("ids") String ids) {
        if (StrUtil.isNotBlank(ids)) {
            List<Long> idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
            if (excelTemplateService.removeByIds(idList)) {
                return Result.success(ExceptionEnum.DELETE_BATCH_SUCCESS);
            }
        }
        throw new UnifiedException(ExceptionEnum.DELETE_BATCH_FAIL);
    }


}

