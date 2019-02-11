package com.zh.service.response.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.response.ResponseFeedbackDTO;
import com.zh.dto.response.ResponseFeedbackMapDTO;
import com.zh.entity.response.ResponseFeedback;
import com.zh.mapper.response.ResponseFeedbackMapper;
import com.zh.service.response.ResponseFeedbackService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 响应反馈 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class ResponseFeedbackServiceImpl extends ServiceImpl<ResponseFeedbackMapper, ResponseFeedback> implements ResponseFeedbackService {

    @Resource
    private ResponseFeedbackMapper responseFeedbackMapper;
    @Override
    public IPage<ResponseFeedbackDTO> selectAllFeedback(Page<ResponseFeedbackDTO> page, Long id, Boolean f) {
        return responseFeedbackMapper.selectAllFeedback(page,id,f);
    }

    @Override
    public void exportExcel(HttpServletResponse response, Long id, Boolean f) throws IOException {
        List<ResponseFeedbackDTO> responseFeedbackDTOS=new ArrayList<>();
        List<ResponseFeedbackDTO> responseFeedbackDTOS1=responseFeedbackMapper.selectAllFeedback(id,f);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (ResponseFeedbackDTO responseFeedbackDTO:responseFeedbackDTOS1){
            if (responseFeedbackDTO.getStartTime()!=null){
                Date date=new Date(responseFeedbackDTO.getStartTime());
                responseFeedbackDTO.setStartTimes(String.valueOf(sdf.format(date)));
            }
            if (responseFeedbackDTO.getEndTime()!=null){
                Date date=new Date(responseFeedbackDTO.getEndTime());
                responseFeedbackDTO.setEndTimes(String.valueOf(sdf.format(date)));
            }
            if (responseFeedbackDTO.getBackTime()!=null){
                Date date=new Date(responseFeedbackDTO.getBackTime());
                responseFeedbackDTO.setBackTimes(String.valueOf(sdf.format(date)));
            }
            responseFeedbackDTOS.add(responseFeedbackDTO);
        }
        ExcelHelper.exportExcel(response, "响应反馈", "响应反馈", ResponseFeedbackDTO.class, responseFeedbackDTOS);
    }

    @Override
    public List<ResponseFeedbackMapDTO> selectAllFeedbackMap( Long id, Boolean f) {
        return responseFeedbackMapper.selectAllFeedbackMap(id,f);
    }
}
