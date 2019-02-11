package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.DikeDTO;
import com.zh.entity.wading.Dike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 堤防 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface DikeMapper extends BaseMapper<Dike> {

    IPage<DikeDTO> selectAllByMessage(Page<DikeDTO> page, @Param("keywords") String keywords);

    List<DikeDTO> selectAllByMessage(@Param("keywords") String keywords);
}
