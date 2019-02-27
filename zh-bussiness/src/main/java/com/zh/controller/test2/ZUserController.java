package com.zh.controller.test2;

import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.test2.ZUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-21
 */
@RestController
@RequestMapping("/zUser")
public class ZUserController {
    @Resource
    private ZUserService zUserService;

    @ApiOperation(value = "添加或修改用户信息")
    @PostMapping("/saveOrUpdate")
    @Log(desc = "添加或编辑用户")
    public Result saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO){
      return Result.success(zUserService.saveOrUpdate(userInertOrUpdateDTO));
    }
    @ApiOperation(value = "导出用户信息")
    @GetMapping
    @Log(desc = "导出用户信息")
    public void exportUser(HttpServletResponse response){
        try {
            zUserService.exportUser(response);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }



}

