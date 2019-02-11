package com.zh.service.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.response.ResponseManageDTO;
import com.zh.dto.response.ResponseMapDTO;
import com.zh.dto.response.ResponseRecordDTO;
import com.zh.entity.response.ResponseRecord;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 预警响应记录表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface ResponseRecordService extends IService<ResponseRecord> {
    ResponseMapDTO selectAllMap();

    ResponseRecordDTO selectResponseStartByWarnId(Long id,Integer type);

    Boolean updateRecord(Long id);

    Boolean checkstatus(Long id,Integer responseStatus);

    IPage<ResponseManageDTO> selectAll(Page<ResponseManageDTO> page, String keywords);

    void exportExcel(HttpServletResponse response, String keywords) throws IOException;
}
