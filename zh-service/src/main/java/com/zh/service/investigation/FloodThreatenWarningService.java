package com.zh.service.investigation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.entity.investigation.FloodThreatenWarning;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 外洪威胁村落水位预警指示表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
public interface FloodThreatenWarningService extends IService<FloodThreatenWarning> {

    boolean importExcel(MultipartFile file);

    Result getListPage(Page<FloodThreatenWarning> page, String keyword);

    void exportFloodThreatenWarning(HttpServletResponse response, String keywords);
}
