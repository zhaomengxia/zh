package com.zh.mapper.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.response.ReponseActionMessageDTO;
import com.zh.dto.response.ResponseStartMapDTO;
import com.zh.entity.response.ResponseActionMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 响应启动消息表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface ResponseActionMessageMapper extends BaseMapper<ResponseActionMessage> {
    IPage<ReponseActionMessageDTO> selectAllActionPage(Page<ReponseActionMessageDTO> page, @Param("id") Long id, @Param("f") Boolean f);

    List<ResponseStartMapDTO> selectAllStartPage(@Param("id")Long id);

    List<ResponseStartMapDTO> selectAllStartMapPage(@Param("id") Long id, @Param("f") Boolean f);
}
