package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.MessageDTO;
import com.zh.dto.wading.WaterDTO;
import com.zh.dto.wading.WholeDTO;
import com.zh.entity.wading.Whole;
import com.zh.mapper.wading.WholeMapper;
import com.zh.service.wading.WholeService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 整体情况 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class WholeServiceImpl extends ServiceImpl<WholeMapper, Whole> implements WholeService {

    @Resource
    private WholeMapper wholeMapper;

    @Override
    public IPage<WholeDTO> selectbyMessage(Page<WholeDTO> page, String keywords) {
        return wholeMapper.selectbyMessage(page,keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<WholeDTO> wholeDTOS =new ArrayList<>();
        List<WholeDTO> wholeDTOS1 = wholeMapper.selectbyMessage(keywords);
        int i=1;
        for (WholeDTO wholeDTO:wholeDTOS1){
            wholeDTO.setSerialNumber(i);
            wholeDTOS.add(wholeDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "整体情况", "整体情况", WholeDTO.class, wholeDTOS);
    }
}
