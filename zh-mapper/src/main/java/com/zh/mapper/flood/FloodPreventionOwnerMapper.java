package com.zh.mapper.flood;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.UserDTO;
import com.zh.dto.flood.FloodPreventionOwnerDTO;
import com.zh.entity.flood.FloodPreventionOwner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 防汛责任人表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
public interface FloodPreventionOwnerMapper extends BaseMapper<FloodPreventionOwner> {
    IPage<FloodPreventionOwnerDTO> selectByTypeIdPage(Page<FloodPreventionOwnerDTO> page, @Param("typeId") Long typeId, @Param("keywords") String keywords);

    List<FloodPreventionOwnerDTO> selectByTypeIdPage(@Param("typeId") Long typeId, @Param("keywords") String keywords);

    List<UserDTO> selectAllUserByTypeId(@Param("typeId") Long typeId);
}
