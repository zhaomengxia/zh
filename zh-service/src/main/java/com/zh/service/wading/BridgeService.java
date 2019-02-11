package com.zh.service.wading;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.wading.BridgeDTO;
import com.zh.entity.wading.Bridge;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 桥梁 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
public interface BridgeService extends IService<Bridge> {
    IPage<BridgeDTO> selectAllByMessage(Page<BridgeDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
