package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.MessageDTO;
import com.zh.dto.wading.WaterDTO;
import com.zh.entity.wading.Water;
import com.zh.mapper.wading.WaterMapper;
import com.zh.service.wading.WaterService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 水库 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Service
public class WaterServiceImpl extends ServiceImpl<WaterMapper, Water> implements WaterService {

    @Resource
    private WaterMapper waterMapper;
    @Override
    public IPage<WaterDTO> selectbyMessage(Page<WaterDTO> page, String keywords) {
        return waterMapper.selectbyMessage(page,keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<WaterDTO> waterDTOS =new ArrayList<>();
        List<WaterDTO> waterDTOS1 = waterMapper.selectbyMessage(keywords);
        int i=1;
        for (WaterDTO waterDTO:waterDTOS1){
            waterDTO.setSerialNumber(i);
            waterDTOS.add(waterDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "水库", "水库", WaterDTO.class, waterDTOS);
    }
}
