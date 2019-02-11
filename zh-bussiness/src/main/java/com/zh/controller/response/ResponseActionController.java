package com.zh.controller.response;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.response.ResponseAction;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.response.ResponseActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 响应行动 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
@RestController
@RequestMapping("/responseAction")
@Api(description = "应急响应-响应行动")
public class ResponseActionController {

    @Resource
    private ResponseActionService responseActionService;

    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增或修改响应行动")
    @Log(desc = "新增或修改响应行动")
    public Result saveOrUpdate(@RequestBody @Valid ResponseAction responseAction) {
        if (responseActionService.saveOrUpdate(responseAction)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAll/page")
    @ApiOperation(value = "获得响应行动信息列表")
    @Log(desc = "获得响应行动信息列表")
    public Result selectAllPage(Page<ResponseAction> page,String keywords) {
        return Result.success(responseActionService.selectAllPage(page,keywords));
    }

    @GetMapping(value = "/selectAll/List")
    @ApiOperation("获取响应行动列表")
    public Result selectAllList(){
        return responseActionService.selectAllList();
    }

    @DeleteMapping(value = "/deleteAction/{id}")
    @ApiOperation(value = "删除响应行动")
    @Log(desc = "删除响应行动")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "响应行动主键"))
    public Result deleteAction(@PathVariable("id") Long id) {
        if (responseActionService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }
}

