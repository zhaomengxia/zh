package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.MessageDTO;
import com.zh.dto.wading.PumpStationDTO;
import com.zh.entity.wading.PumpStation;
import com.zh.mapper.wading.PumpStationMapper;
import com.zh.service.wading.PumpStationService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 泵站 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Service
public class PumpStationServiceImpl extends ServiceImpl<PumpStationMapper, PumpStation> implements PumpStationService {
    @Resource
    private PumpStationMapper pumpStationMapper;

    @Override
    public IPage<PumpStationDTO> selectAllByMessage(Page<PumpStationDTO> page, String keywords) {
        return pumpStationMapper.selectAllByMessage(page, keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<PumpStationDTO> pumpStationDTOS=new ArrayList<>();
        List<PumpStationDTO> pumpStationDTOS1 = pumpStationMapper.selectAllByMessage(keywords);
        int i=1;
        for (PumpStationDTO pumpStationDTO:pumpStationDTOS1){
            pumpStationDTO.setSerialNumber(i);
            pumpStationDTOS.add(pumpStationDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "泵站", "泵站", PumpStationDTO.class, pumpStationDTOS);
    }
}
