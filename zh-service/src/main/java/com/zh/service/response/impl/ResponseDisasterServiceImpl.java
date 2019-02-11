package com.zh.service.response.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.response.ResponseDisaterDTO;
import com.zh.dto.response.ResponseDisaterMapDTO;
import com.zh.entity.response.ResponseDisaster;
import com.zh.mapper.response.ResponseDisasterMapper;
import com.zh.service.response.ResponseDisasterService;
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
 * 灾情统计表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class ResponseDisasterServiceImpl extends ServiceImpl<ResponseDisasterMapper, ResponseDisaster> implements ResponseDisasterService {
    @Resource
    private ResponseDisasterMapper responseDisasterMapper;
    @Override
    public IPage<ResponseDisaterDTO> selectAllById(Page<ResponseDisaterDTO> page,Long id, Boolean f) {
        return responseDisasterMapper.selectAllById(page,id,f);
    }

    @Override
    public void exportExcel(HttpServletResponse response,Long id, Boolean f) throws IOException {
        List<ResponseDisaterDTO> responseDisaterDTOS=new ArrayList<>();
        List<ResponseDisaterDTO> responseDisaterDTOS1=responseDisasterMapper.selectAllById(id,f);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (ResponseDisaterDTO responseDisaterDTO:responseDisaterDTOS1){
            if (responseDisaterDTO.getStartTime()!=null){
                Date date=new Date(responseDisaterDTO.getStartTime());
                responseDisaterDTO.setStartTimes(String.valueOf(sdf.format(date)));
            }
            if (responseDisaterDTO.getEndTime()!=null){
                Date date=new Date(responseDisaterDTO.getEndTime());
                responseDisaterDTO.setEndTimes(String.valueOf(sdf.format(date)));
            }
            if (responseDisaterDTO.getBackTime()!=null){
                Date date=new Date(responseDisaterDTO.getBackTime());
                responseDisaterDTO.setBackTimes(String.valueOf(sdf.format(date)));
            }
            responseDisaterDTOS.add(responseDisaterDTO);
        }

        ExcelHelper.exportExcel(response, "灾情统计", "灾情统计", ResponseDisaterDTO.class, responseDisaterDTOS);
    }

    @Override
    public List<ResponseDisaterMapDTO> selectAllMap( Long id, Boolean f) {

        return responseDisasterMapper.selectAllMap(id,f);
    }
}
