package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.wading.DikeDTO;
import com.zh.dto.wading.MessageDTO;
import com.zh.entity.wading.Dike;
import com.zh.mapper.wading.DikeMapper;
import com.zh.service.wading.DikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 堤防 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@Service
public class DikeServiceImpl extends ServiceImpl<DikeMapper, Dike> implements DikeService {

    @Resource
    private DikeMapper dikeMapper;

    @Override
    public IPage<DikeDTO> selectAllByMessage(Page<DikeDTO> page, String keywords) {
        return dikeMapper.selectAllByMessage(page, keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<DikeDTO> dikeDTOS =new ArrayList<>();
        List<DikeDTO> dikeDTOS1 = dikeMapper.selectAllByMessage(keywords);
        int i=1;
        for (DikeDTO dikeDTO:dikeDTOS1){
            dikeDTO.setSerialNumber(i);
            dikeDTOS.add(dikeDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "堤防", "堤防", DikeDTO.class, dikeDTOS);
    }
}
