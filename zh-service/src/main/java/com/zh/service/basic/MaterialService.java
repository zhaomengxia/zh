package com.zh.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.entity.basic.Material;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 物资表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
public interface MaterialService extends IService<Material> {

    IPage<Material> selectAllByMessage(Page<Material> materialPage, String keywords, Long warehourseId);

    void exportExcel(HttpServletResponse response, Long id, String keywords) throws IOException;
}
