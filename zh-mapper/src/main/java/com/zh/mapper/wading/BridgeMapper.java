package com.zh.mapper.wading;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.BridgeDTO;
import com.zh.entity.wading.Bridge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 桥梁 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface BridgeMapper extends BaseMapper<Bridge> {

    IPage<BridgeDTO> selectAllByMessage(Page<BridgeDTO> page, @Param("keywords") String keywords);

    List<BridgeDTO> selectAllByMessage(@Param("keywords") String keywords);
}
