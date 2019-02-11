package com.zh.mapper.sys;

import com.zh.entity.sys.SysResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-20
 */
public interface SysResourcesMapper extends BaseMapper<SysResources> {

    List<SysResources> getMenuByParentIdAndSeq(@Param("parentId") Long parentId, @Param("seq") Integer seq);

    List<SysResources> getMenuByParentId(@Param("parentId")Long parentId);

}
