package com.zh.mapper.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.response.ResponseDisaterDTO;
import com.zh.dto.response.ResponseDisaterMapDTO;
import com.zh.entity.response.ResponseDisaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 灾情统计表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface ResponseDisasterMapper extends BaseMapper<ResponseDisaster> {
    IPage<ResponseDisaterDTO> selectAllById(Page<ResponseDisaterDTO> page,@Param("id") Long id, @Param("f") Boolean f);

    List<ResponseDisaterDTO> selectAllById(@Param("id") Long id, @Param("f") Boolean f);

    List<ResponseDisaterMapDTO> selectAllMap(@Param("id") Long id, @Param("f") Boolean f);
}
