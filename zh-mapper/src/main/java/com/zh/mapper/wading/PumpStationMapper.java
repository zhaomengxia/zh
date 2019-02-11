package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.PumpStationDTO;
import com.zh.entity.wading.PumpStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 泵站 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface PumpStationMapper extends BaseMapper<PumpStation> {

    IPage<PumpStationDTO> selectAllByMessage(Page<PumpStationDTO> page, @Param("keywords")String keywords);

    List<PumpStationDTO> selectAllByMessage(@Param("keywords") String keywords);
}
