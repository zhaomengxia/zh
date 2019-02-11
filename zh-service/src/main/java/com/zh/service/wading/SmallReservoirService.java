package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.SmallReservoirDTO;
import com.zh.entity.wading.SmallReservoir;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p>
 * 塘坝 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface SmallReservoirService extends IService<SmallReservoir> {
    IPage<SmallReservoirDTO> selectbyMessage(Page<SmallReservoirDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
