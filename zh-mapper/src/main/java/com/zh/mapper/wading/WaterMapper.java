package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.WaterDTO;
import com.zh.entity.wading.Water;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 水库 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WaterMapper extends BaseMapper<Water> {
    IPage<WaterDTO> selectbyMessage(Page<WaterDTO> page, @Param("keywords") String keywords);

    List<WaterDTO> selectbyMessage(@Param("keywords") String keywords);
}
