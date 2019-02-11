package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.MessageDTO;
import com.zh.dto.wading.RoadCulvertDTO;
import com.zh.entity.wading.RoadCulvert;
import com.zh.mapper.wading.RoadCulvertMapper;
import com.zh.service.wading.RoadCulvertService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 路涵 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Service
public class RoadCulvertServiceImpl extends ServiceImpl<RoadCulvertMapper, RoadCulvert> implements RoadCulvertService {

    @Resource
    private RoadCulvertMapper roadCulvertMapper;

    @Override
    public IPage<RoadCulvertDTO> selectAllByMessage(Page<RoadCulvertDTO> page,String keywords) {
        return roadCulvertMapper.selectAllByMessage(page, keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<RoadCulvertDTO> roadCulvertDTOS =new ArrayList<>();
        List<RoadCulvertDTO> roadCulvertDTOS1 = roadCulvertMapper.selectAllByMessage(keywords);
        int i=1;
        for (RoadCulvertDTO roadCulvertDTO:roadCulvertDTOS1){
            roadCulvertDTO.setSerialNumber(i);
            roadCulvertDTOS.add(roadCulvertDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "路涵", "路涵", RoadCulvertDTO.class, roadCulvertDTOS);
    }
}
