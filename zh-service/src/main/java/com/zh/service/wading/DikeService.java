package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.DikeDTO;
import com.zh.entity.wading.Dike;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 堤防 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface DikeService extends IService<Dike> {
    IPage<DikeDTO> selectAllByMessage(Page<DikeDTO> page, String keywords);

    void exportExcel(HttpServletResponse response,String keywords) throws IOException;
}
