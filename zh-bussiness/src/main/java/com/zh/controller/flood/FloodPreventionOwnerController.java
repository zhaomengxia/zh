package com.zh.controller.flood;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.flood.FloodPreventionOwnerDTO;
import com.zh.entity.flood.FloodDutyType;
import com.zh.entity.flood.FloodPreventionOwner;
import com.zh.entity.sys.SysUser;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.flood.FloodDutyTypeService;
import com.zh.service.flood.FloodPreventionOwnerService;
import com.zh.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
 * 防汛责任人表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
@RestController
@RequestMapping("/floodPreventionOwner")
@Api(description = "防汛资源-防汛责任人")
public class FloodPreventionOwnerController {

    @Resource
    private FloodPreventionOwnerService floodPreventionOwnerService;
    @Resource
    private FloodDutyTypeService floodDutyTypeService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private ImportService importService;

    @PostMapping(value = "/saveOrUpdateFloodType")
    @ApiOperation(value = "新增或修改防汛责任制类型")
    @Log(desc = "新增或修改防汛责任制类型")
    public Result saveOrUpdateType(@RequestBody @Valid FloodDutyType floodDutyType) {
        if (floodDutyTypeService.saveOrUpdate(floodDutyType)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "删除责任制类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "责任制类型主键")
    })
    @DeleteMapping("/deleteType/{id}")
    @Log(desc = "删除责任制类型")
    public Result deleteType(@PathVariable("id") Long id) {
       List<FloodPreventionOwner> floodPreventionOwners=
               floodPreventionOwnerService.list(new LambdaQueryWrapper<FloodPreventionOwner>()
                       .eq(FloodPreventionOwner::getTypeId,id));
       if (null!=floodPreventionOwners&&floodPreventionOwners.size()>0){
           return Result.fail("该责任类型下还有责任人！");
       }
        if (floodDutyTypeService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllType")
    @ApiOperation(value = "获得所有责任制类型")
    @Log(desc = "获得所有责任制类型")
    public Result selectAllType(){
        return Result.success(floodDutyTypeService
                .list(new LambdaQueryWrapper<FloodDutyType>().select(FloodDutyType::getId, FloodDutyType::getName)));
    }

    @GetMapping(value = "/selectAllUserByTypeId")
    @ApiOperation(value = "获得某责任制类型下的责任人")
    @Log(desc = "获得某责任制类型下的责任人")
    public Result selectAllUserByTypeId(Long typeId){
        return Result.success(floodPreventionOwnerService.selectAllUserByTypeId(typeId));
    }

    @PostMapping(value = "/saveOrUpdatePreventionOwner")
    @ApiOperation(value = "在某责任制类型下新增或修改防汛责任人信息")
    @Log(desc = "在某责任制类型下新增或修改防汛责任人信息")
    public Result saveOrUpdatePreventionOwner(@RequestBody @Valid FloodPreventionOwnerDTO floodPreventionOwnerDTO) {
        String userName=floodPreventionOwnerDTO.getUserName();
        if (StrUtil.isBlank(userName)){
            return Result.fail("请填姓名！");
        }
        List<SysUser> sysUsers=sysUserService.list(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, userName));
        if (sysUsers.size()==0){
           return Result.fail("此人不是系统用户，请先到“系统管理-用户管理”中添加！");
        }
        floodPreventionOwnerDTO.setUserId(sysUsers.get(0).getId());
        FloodPreventionOwner floodPreventionOwner=new FloodPreventionOwner();
        BeanUtils.copyProperties(floodPreventionOwnerDTO,floodPreventionOwner);
        boolean f=floodPreventionOwnerService.checkDutyUser(floodPreventionOwner);
        if (f&&floodPreventionOwnerService.saveOrUpdate(floodPreventionOwner)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @ApiOperation(value = "删除某责任制类型下的某防汛责任人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "某责任制类型下的某防汛责任人主键")
    })
    @DeleteMapping("/deletePreventionOwner/{id}")
    @Log(desc = "删除某责任制类型下的某防汛责任人")
    public Result deletePreventionOwner(@PathVariable("id") String id) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (floodPreventionOwnerService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllPreventionOwner/page")
    @ApiOperation(value = "获得某责任类型下防汛责任人信息列表")
    @Log(desc = "获得某责任类型下防汛责任人信息列表")
    public Result selectAllPreventionOwner(Page<FloodPreventionOwnerDTO> page, Long typeId, String keywords) {
        if (null==typeId){
            return Result.fail("请传责任制类型");
        }
        return Result.success(floodPreventionOwnerService.selectByTypeIdPage(page, typeId,keywords));
    }


    @ApiOperation(value = "防汛责任人信息导入")
    @PostMapping("/in")
    @Log(desc = "防汛责任人信息导入")
    public Result whole(MultipartFile file) throws IOException {
        if (importService.importPreventionOwner(file.getInputStream())) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "防汛责任人excel导出")
    @GetMapping("/excel")
    @Log(desc = "防汛责任人excel导出")
    public void excel(HttpServletResponse response, Long typeId, String keywords) {
        try {
            floodPreventionOwnerService.exportExcel(response, typeId,keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

