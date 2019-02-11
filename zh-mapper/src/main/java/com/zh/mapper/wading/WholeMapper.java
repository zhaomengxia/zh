package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.WholeDTO;
import com.zh.entity.wading.Whole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 整体情况 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface WholeMapper extends BaseMapper<Whole> {
    IPage<WholeDTO> selectbyMessage(Page<WholeDTO> page, @Param("keywords") String keywords);

    List<WholeDTO> selectbyMessage(@Param("keywords") String keywords);
}
