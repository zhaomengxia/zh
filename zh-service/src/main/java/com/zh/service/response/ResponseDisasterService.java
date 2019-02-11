package com.zh.service.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.response.ResponseDisaterDTO;
import com.zh.dto.response.ResponseDisaterMapDTO;
import com.zh.entity.response.ResponseDisaster;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 灾情统计表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface ResponseDisasterService extends IService<ResponseDisaster> {
    IPage<ResponseDisaterDTO> selectAllById(Page<ResponseDisaterDTO> page, Long id, Boolean f);

    void  exportExcel(HttpServletResponse response, Long id, Boolean f) throws IOException;

    List<ResponseDisaterMapDTO> selectAllMap( Long id, Boolean f);
}
