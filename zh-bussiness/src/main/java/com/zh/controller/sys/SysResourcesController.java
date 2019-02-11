package com.zh.controller.sys;


import com.zh.api.Result;
import com.zh.dto.sys.MenuInfoDTO;
import com.zh.service.sys.SysResourcesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/sysResources")
public class SysResourcesController {

    @Resource
    private SysResourcesService sysResourcesService;

    /**
     * 新增或修改菜单信息.
     * @param menuInfoDTO
     * @return
     */
    @PostMapping("addOrUpdateMenuInfo")
    public Result addOrUpdateMenuInfo(@RequestBody MenuInfoDTO menuInfoDTO){
        return sysResourcesService.addOrUpdateMenuInfo(menuInfoDTO);
    }

}

