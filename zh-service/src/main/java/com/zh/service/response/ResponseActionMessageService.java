package com.zh.service.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.response.ReponseActionMessageDTO;
import com.zh.dto.response.ResponseStartMapDTO;
import com.zh.entity.response.ResponseActionMessage;

import java.util.List;

/**
 * <p>
 * 响应启动消息表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface ResponseActionMessageService extends IService<ResponseActionMessage> {
    IPage<ReponseActionMessageDTO> selectAllActionPage(Page<ReponseActionMessageDTO> page, Long id, Boolean f);

    List<ResponseStartMapDTO> selectAllStartPage( Long id);

    List<ResponseStartMapDTO> selectAllStartMapPage(Long id, Boolean f);
}
