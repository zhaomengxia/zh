package com.zh.service.sys;

import com.zh.api.Result;
import com.zh.dto.sys.MenuInfoDTO;
import com.zh.entity.sys.SysResources;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-20
 */
public interface SysResourcesService extends IService<SysResources> {

    /**
     * 新增或修改菜单信息.
     * @param menuInfoDTO 菜单信息dto
     * @return 操作结果集.
     */
    Result addOrUpdateMenuInfo(MenuInfoDTO menuInfoDTO);
}
