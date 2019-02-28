package com.zh.controller.test2;

import com.zh.aop.annotation.Log;
import com.zh.aop.annotation.LogTwo;
import com.zh.api.Result;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.excel.ImportService;
import com.zh.service.test2.ZUserService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


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

    @Resource
    private ImportService importService;
    @ApiOperation(value = "添加或修改用户信息")
    @PostMapping("/saveOrUpdate")
    @Log(desc = "添加或编辑用户")
    public Result saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO,MultipartFile multipartFile, HttpServletRequest request) throws IOException {
      return Result.success(zUserService.saveOrUpdateUser(userInertOrUpdateDTO,multipartFile,request));
    }
    @ApiOperation(value = "导出用户信息")
    @GetMapping("/exportUser")
    @Log(desc = "导出用户信息")
    @LogTwo(descTwo = "导出用户信息11")
    public void exportUser(HttpServletResponse response){
        try {
            zUserService.exportUser(response);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }
    @Around("exportUser(HttpServletResponse response)")
    public Object testObject(ProceedingJoinPoint jp) throws Throwable {
        System.out.print(jp.getSignature()+"开始执行");
        Object v=jp.proceed();
        System.out.print(jp.getSignature()+"执行成功");
        return v;
    }

    @ApiOperation(value = "导入用户信息")
    @PostMapping("/importUser")
    @Log(desc = "导入用户信息")
    public void importUser(MultipartFile file){
        try {
            importService.importUser(file.getInputStream());
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
        }
    }

    @ApiOperation(value = "导出用户信息2")
    @GetMapping("/export")
    @Log(desc = "导出用户信息2")
    public void export(){

    }


}

