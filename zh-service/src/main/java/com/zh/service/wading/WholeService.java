package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.WholeDTO;
import com.zh.entity.wading.Whole;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 整体情况 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface WholeService extends IService<Whole> {
    IPage<WholeDTO> selectbyMessage(Page<WholeDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
