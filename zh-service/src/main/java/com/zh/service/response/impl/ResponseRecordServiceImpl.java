package com.zh.service.response.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.response.*;
import com.zh.entity.response.ResponseRecord;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.response.ResponseRecordMapper;
import com.zh.service.response.ResponseActionMessageService;
import com.zh.service.response.ResponseDisasterService;
import com.zh.service.response.ResponseFeedbackService;
import com.zh.service.response.ResponseRecordService;
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
 * 预警响应记录表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@Service
public class ResponseRecordServiceImpl extends ServiceImpl<ResponseRecordMapper, ResponseRecord> implements ResponseRecordService {

    @Resource
    private ResponseRecordMapper responseRecordMapper;

    @Resource
    private ResponseActionMessageService responseActionMessageService;
    @Resource
    private ResponseFeedbackService responseFeedbackService;
    @Resource
    private ResponseDisasterService responseDisasterService;

    @Override
    public ResponseMapDTO selectAllMap() {
        ResponseMapDTO responseMapDTO = new ResponseMapDTO();
        List<ResponseStartMapDTO> responseStartMapDTOS = responseActionMessageService.selectAllStartMapPage(null, null);
        List<ResponseFeedbackMapDTO> responseFeedbackMapDTOS = responseFeedbackService.selectAllFeedbackMap(null, null);
        List<ResponseDisaterMapDTO> responseDisaterMapDTOS = responseDisasterService.selectAllMap(null, null);
        responseMapDTO.setResponseDisaterMapDTOS(responseDisaterMapDTOS);
        responseMapDTO.setResponseFeedbackMapDTOS(responseFeedbackMapDTOS);
        responseMapDTO.setResponseStartMapDTOS(responseStartMapDTOS);
        return responseMapDTO;
    }

    @Override
    public ResponseRecordDTO selectResponseStartByWarnId(Long id, Integer type) {
        return responseRecordMapper.selectResponseStartByWarnId(id, type);
    }


    @Override
    public Boolean updateRecord(Long id) {
        List<ResponseRecord> responseRecords1 = this.list(new LambdaQueryWrapper<ResponseRecord>().eq(ResponseRecord::getWarningRecordId, id));
        List<ResponseRecord> responseRecords = new ArrayList<>();
        for (ResponseRecord responseRecord1 : responseRecords1) {
            responseRecord1.setResponseStatus(5);
            responseRecords.add(responseRecord1);
        }
        if (this.saveOrUpdateBatch(responseRecords)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkstatus(Long id, Integer responseStatus) {
        if (id == null) {
            throw new UnifiedException("请传响应记录id！");
        }

        if (responseStatus == 0) {
            throw new UnifiedException("请传responseStatus");
        }
        ResponseRecord responseRecord = this.getById(id);
        if (responseRecord.getResponseStatus().equals(responseStatus)){
            return false;
        }
        responseRecord.setResponseStatus(responseStatus);
        if (this.updateById(responseRecord)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<ResponseManageDTO> selectAll(Page<ResponseManageDTO> page, String keywords) {
        return responseRecordMapper.selectAll(page, keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<ResponseManageDTO> responseManageDTOS = new ArrayList<>();
        List<ResponseManageDTO> responseManageDTOS1 = responseRecordMapper.selectAll(keywords);
        for (ResponseManageDTO responseManageDTO : responseManageDTOS1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            if (responseManageDTO.getWarningTime() != null) {
                Date date = new Date(responseManageDTO.getWarningTime());
                String times = String.valueOf(sdf.format(date));
                responseManageDTO.setWarningTimes(times);
            }
            if (responseManageDTO.getResponseTime() != null) {
                Date date1 = new Date(responseManageDTO.getResponseTime());
                String times2 = String.valueOf(sdf.format(date1));
                responseManageDTO.setResponseTimes(times2);
            }
            responseManageDTOS.add(responseManageDTO);
        }
        ExcelHelper.exportExcel(response, "预警响应", "预警响应", ResponseManageDTO.class, responseManageDTOS);
    }
}
