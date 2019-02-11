package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.basic.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 物资表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
public interface MaterialMapper extends BaseMapper<Material> {

    IPage<Material> selectAllByMessage(Page<Material> materialPage,@Param("keywords") String keywords, @Param("wareHourseId") Long wareHourseId);
    List<Material> selectAllByMessage(@Param("keywords") String keywords, @Param("wareHourseId") Long wareHourseId);
}
