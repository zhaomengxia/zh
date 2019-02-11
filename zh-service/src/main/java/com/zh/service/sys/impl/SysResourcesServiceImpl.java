package com.zh.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.dto.sys.MenuInfoDTO;
import com.zh.entity.sys.SysResources;
import com.zh.mapper.sys.SysResourcesMapper;
import com.zh.service.sys.SysResourcesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-20
 */
@Service
public class SysResourcesServiceImpl extends ServiceImpl<SysResourcesMapper, SysResources> implements SysResourcesService {

    @Resource
    private SysResourcesMapper sysResourcesMapper;

    private static Long ONE_LAYER_PARENT_ID = -1L;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdateMenuInfo(@Validated MenuInfoDTO menuInfoDTO) {
        if (menuInfoDTO == null) {
            return Result.fail("对象获取为空!");
        }
        SysResources sysResources = new SysResources();
        if (menuInfoDTO.getMenuId() == null) {
            if (menuInfoDTO.getLayer() == 1) {
                List<SysResources> resources = sysResourcesMapper.getMenuByParentIdAndSeq(ONE_LAYER_PARENT_ID, menuInfoDTO.getSeq());
                if (resources.size() > 0) {
                    return Result.fail("不能设置相同的菜单顺序!");
                }
                sysResources.setNode(menuInfoDTO.getSeq().toString());
                sysResources.setParentId(ONE_LAYER_PARENT_ID);
            } else {
                if (menuInfoDTO.getParentId() == null) {
                    return Result.fail("必须选择一级菜单");
                }
                List<SysResources> resources = sysResourcesMapper
                        .getMenuByParentIdAndSeq(menuInfoDTO.getParentId(), menuInfoDTO.getSeq());
                if (resources.size() > 0) {
                    return Result.fail("不能设置相同的菜单顺序!");
                }
                SysResources parent = sysResourcesMapper.selectById(menuInfoDTO.getParentId());
                sysResources.setNode(parent.getNode() + "-" + menuInfoDTO.getSeq().toString());
                sysResources.setParentId(menuInfoDTO.getParentId());
            }
        } else {
            sysResources = sysResourcesMapper.selectById(menuInfoDTO.getMenuId());
            if (menuInfoDTO.getLayer() == 1) {
                //如果改变一级菜单的顺序.
                if (!menuInfoDTO.getSeq().equals(sysResources.getSeq())) {
                    List<SysResources> resources = sysResourcesMapper.getMenuByParentIdAndSeq(ONE_LAYER_PARENT_ID, menuInfoDTO.getSeq());
                    if (resources.size() > 0) {
                        return Result.fail("不能设置相同的菜单顺序!");
                    }
                    List<SysResources> sonList = sysResourcesMapper.getMenuByParentId(menuInfoDTO.getMenuId());
                    if (sonList.size() > 0) {
                        sonList.forEach(a -> a.setNode(menuInfoDTO.getSeq().toString() + "-" + a.getSeq().toString()));
                        this.updateBatchById(sonList);
                    }
                }
                sysResources.setParentId(ONE_LAYER_PARENT_ID);
                sysResources.setNode(menuInfoDTO.getSeq().toString());
            } else {
                if (sysResources.getParentId() == -1) {
                    return Result.fail("一级菜单不能调整为二级菜单");
                }
                if (menuInfoDTO.getParentId() == null) {
                    return Result.fail("必须选择一级菜单");
                }
                List<SysResources> list = sysResourcesMapper
                        .getMenuByParentIdAndSeq(menuInfoDTO.getParentId(), menuInfoDTO.getSeq());
                if (list.size() > 0 && !list.get(0).getId().equals(menuInfoDTO.getMenuId())) {
                    return Result.fail("该一级菜单下已存在该顺序的二级菜单!");
                }
                SysResources parent = sysResourcesMapper.selectById(menuInfoDTO.getParentId());
                sysResources.setNode(parent.getSeq().toString() + "-" + menuInfoDTO.getSeq().toString());
                sysResources.setParentId(menuInfoDTO.getParentId());
            }

        }
        sysResources.setDescription(menuInfoDTO.getDescription());
        sysResources.setHttpPath(menuInfoDTO.getHttpPath());
        sysResources.setName(menuInfoDTO.getMenuName());
        sysResources.setSeq(menuInfoDTO.getSeq());
        sysResources.setLayer(menuInfoDTO.getLayer());
        if (sysResources.getId() == null) {
            sysResourcesMapper.insert(sysResources);
        } else {
            sysResourcesMapper.updateById(sysResources);
        }
        return Result.success("操作成功!");
    }
}
