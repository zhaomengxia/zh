package com.zh.service.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.response.ResponseFeedbackDTO;
import com.zh.dto.response.ResponseFeedbackMapDTO;
import com.zh.entity.response.ResponseFeedback;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 响应反馈 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface ResponseFeedbackService extends IService<ResponseFeedback> {
    IPage<ResponseFeedbackDTO> selectAllFeedback(Page<ResponseFeedbackDTO> page, Long id, Boolean f);

    void exportExcel(HttpServletResponse response, Long id, Boolean f) throws IOException;

    List<ResponseFeedbackMapDTO> selectAllFeedbackMap( Long id, Boolean f);
}
