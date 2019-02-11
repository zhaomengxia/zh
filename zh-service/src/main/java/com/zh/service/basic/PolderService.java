package com.zh.service.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.api.Result;
import com.zh.entity.basic.Polder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 圩区 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
public interface PolderService extends IService<Polder> {

    Result getListPage(Page<Polder> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
