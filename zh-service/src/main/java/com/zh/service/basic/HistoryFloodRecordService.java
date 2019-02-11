package com.zh.service.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.api.Result;
import com.zh.entity.basic.HistoryFloodRecord;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface HistoryFloodRecordService extends IService<HistoryFloodRecord> {

    Result getListPage(Page<HistoryFloodRecord> page, String keyword);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
