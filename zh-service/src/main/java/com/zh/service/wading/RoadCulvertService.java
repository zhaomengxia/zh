package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.RoadCulvertDTO;
import com.zh.entity.wading.RoadCulvert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 路涵 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface RoadCulvertService extends IService<RoadCulvert> {
    IPage<RoadCulvertDTO> selectAllByMessage(Page<RoadCulvertDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
