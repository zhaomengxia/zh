package com.zh.service.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.api.Result;
import com.zh.entity.basic.RiverFloodProtection;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 中小型河流抗洪能力 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface RiverFloodProtectionService extends IService<RiverFloodProtection> {

    public Result getListPage(Page<RiverFloodProtection> page, String keyword);

    void  exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
