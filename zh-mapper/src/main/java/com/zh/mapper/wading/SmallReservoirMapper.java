package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.SmallReservoirDTO;
import com.zh.entity.wading.SmallReservoir;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 塘坝 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface SmallReservoirMapper extends BaseMapper<SmallReservoir> {

    IPage<SmallReservoirDTO> selectbyMessage(Page<SmallReservoirDTO> page, @Param("keywords") String keywords);

    List<SmallReservoirDTO> selectbyMessage(@Param("keywords") String keywords);
}
