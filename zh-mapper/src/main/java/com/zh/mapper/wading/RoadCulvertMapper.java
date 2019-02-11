package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.RoadCulvertDTO;
import com.zh.entity.wading.RoadCulvert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 路涵 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface RoadCulvertMapper extends BaseMapper<RoadCulvert> {

    IPage<RoadCulvertDTO> selectAllByMessage(Page<RoadCulvertDTO> page, @Param("keywords") String keywords);

    List<RoadCulvertDTO> selectAllByMessage(@Param("keywords") String keywords);
}
