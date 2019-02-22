package com.zh.controller.test2;

import com.zh.api.Result;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.service.test2.ZUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


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
    public Result saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO){
      return Result.success(zUserService.saveOrUpdate(userInertOrUpdateDTO));
    }

}

