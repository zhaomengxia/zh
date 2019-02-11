package com.zh.service.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.api.Result;
import com.zh.entity.basic.AutoStation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 自动监测站 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
public interface AutoStationService extends IService<AutoStation> {

    Result getPageList(Page<AutoStation> page, String keyword);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
