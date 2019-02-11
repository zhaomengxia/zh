package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.MessageDTO;
import com.zh.dto.wading.SmallReservoirDTO;
import com.zh.entity.wading.SmallReservoir;
import com.zh.mapper.wading.SmallReservoirMapper;
import com.zh.service.wading.SmallReservoirService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 塘坝 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@Service
public class SmallReservoirServiceImpl extends ServiceImpl<SmallReservoirMapper, SmallReservoir> implements SmallReservoirService {
    @Resource
    private SmallReservoirMapper smallReservoirMapper;

    @Override
    public IPage<SmallReservoirDTO> selectbyMessage(Page<SmallReservoirDTO> page, String keywords) {

        return smallReservoirMapper.selectbyMessage(page,keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<SmallReservoirDTO> smallReservoirDTOS=new ArrayList<>();
        List<SmallReservoirDTO> smallReservoirDTOS1 = smallReservoirMapper.selectbyMessage(keywords);
        int i=1;
        for (SmallReservoirDTO smallReservoirDTO:smallReservoirDTOS1){
            smallReservoirDTO.setSerialNumber(i);
            smallReservoirDTOS.add(smallReservoirDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "塘坝", "塘坝", SmallReservoirDTO.class, smallReservoirDTOS);
    }
}
