package com.zh.service.investigation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.api.Result;
import com.zh.entity.investigation.LowEarlyWarning;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 低洼易涝村落雨量预警指标表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
public interface LowEarlyWarningService extends IService<LowEarlyWarning> {


    boolean importExcel(MultipartFile file);

    Result getListPage(Page<LowEarlyWarning> page, String keywords);

    void exportLowEarlyWarning(HttpServletResponse response, String keywords);
}
