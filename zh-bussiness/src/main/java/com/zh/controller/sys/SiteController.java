package com.zh.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.sys.site.SiteDTO;
import com.zh.entity.sys.Site;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.sys.DeviceFactorService;
import com.zh.service.sys.DeviceService;
import com.zh.service.sys.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 自动监测站 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@RestController
@RequestMapping("/site")
@Api(description = "站点管理")
public class SiteController {
    @Resource
    private SiteService siteService;
    @Resource
    private DeviceFactorService deviceFactorService;
    @Resource
    private DeviceService deviceService;
    /**
     * 先保存  site 站点信息 再拿到 某站点的信息 将 站点跟因子绑定一起
     */
    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "保存或修改站点概要")
    @Transactional(rollbackFor = Exception.class)
    @Log(desc = "保存或修改站点概要")
    public Result addOrUpdate(@RequestBody SiteDTO siteDTO) {
        //某站点信息
        Site site = new Site();
        if (null!=siteDTO.getName()&&!"".equals(siteDTO.getName())){
            //这里要判断  站点名称是否重复
            BeanUtils.copyProperties(siteDTO, site);

            if (siteService.checkCode(site)&&siteService.checkName(site)) {
                siteService.saveOrUpdate(site);
            }

        }
        if (null==site.getId()&&null!=siteDTO.getId()) {
            siteDTO.setId(siteDTO.getId());
        }
        else{
            siteDTO.setId(site.getId());
        }
        boolean f = siteService.saveOrUpdateOthers(siteDTO);
        if (f) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS,siteDTO.getId());
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectBySiteName")
    @ApiOperation(value = "根据站点名称获得站点信息并分页")
    @Log(desc = "根据站点名称获得站点信息并分页")
    public Result selectBySiteName(Page<Site> page, String keywords) {
        return Result.success(siteService.selectBySiteName(page, keywords));
    }

    @GetMapping(value = "/selectByKeywords")
    @ApiOperation(value = "根据关键词查询监测点列表")
    public Result selectByKeywords(Page<SiteDTO> page, String keywords){
        return Result.success(siteService.selectByKeywords(page, keywords));
    }

    @GetMapping(value = "getSiteList")
    @ApiOperation(value = "获取站点列表")
    public Result getSiteList(){
        return Result.success(siteService.getSiteList());
    }


    @GetMapping(value = "/selectBySiteId")
    @ApiOperation(value = "根据站点id 查看或编辑 站点信息")
    @Log(desc = "根据站点id 查看或编辑 站点信息")
    public Result selectById(Long siteId) {
        return Result.success(siteService.selectBySiteId(siteId));
    }


    @ApiOperation(value = "批量删除站点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "站点主键 数组")
    })
    @DeleteMapping("/deleteMany/{ids}")
    @Log(desc = "删除站点")
    public Result deleteByIds(@PathVariable("ids") String ids) {
        List<Long> idList = new ArrayList<>();
        //先查找该站点下有没有设备信息
        if (StrUtil.isNotBlank(ids)) {
            idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        }
        for (Long id : idList) {
            if (siteService.selectAllBySiteId(id)) {
                return Result.fail("删除失败，因有站点下还有设备信息！");
            }
        }
        if (siteService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }


    @ApiOperation(value = "删除站点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "站点主键 ")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除站点")
    public Result deleteById(@PathVariable("id") Long id) {
        //先查找该站点下有没有设备信息
        if (siteService.selectAllBySiteId(id)) {
            return Result.fail("删除失败，因该站点下还有设备信息！");
        }
        if (siteService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }


    @ApiOperation(value = "删除设备  批量，单个删除都可用 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "设备主键 ")
    })
    @DeleteMapping("/deleteDeviceByIds/{ids}")
    @Log(desc = "删除设备")
    @Transactional
    public Result deleteDeviceById(@PathVariable("ids") String ids) {
        List<Long> idList = new ArrayList<>();
        //先查找该站点下有没有设备信息
        if (StrUtil.isNotBlank(ids)) {
            idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        }
        if (deviceService.removeByIds(idList)) {
            siteService.deleteByDeviceIds(idList);
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

}

