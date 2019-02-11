package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.WaterDTO;
import com.zh.entity.wading.Water;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 水库 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WaterService extends IService<Water> {
    IPage<WaterDTO> selectbyMessage(Page<WaterDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
