package com.zh.service.wading.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.wading.BridgeDTO;
import com.zh.dto.wading.MessageDTO;
import com.zh.entity.wading.Bridge;
import com.zh.mapper.wading.BridgeMapper;
import com.zh.service.wading.BridgeService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 桥梁 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@Service
public class BridgeServiceImpl extends ServiceImpl<BridgeMapper, Bridge> implements BridgeService {

    @Resource
    private BridgeMapper bridgeMapper;
    @Override
    public IPage<BridgeDTO> selectAllByMessage(Page<BridgeDTO> page, String keywords) {

        return bridgeMapper.selectAllByMessage(page,keywords);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<BridgeDTO> bridgeDTOS=new ArrayList<>();
        List<BridgeDTO> bridgeDTOS1 = bridgeMapper.selectAllByMessage(keywords);
        int i=1;
        for (BridgeDTO bridgeDTO:bridgeDTOS1){
            bridgeDTO.setSerialNumber(i);
            bridgeDTOS.add(bridgeDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "桥梁", "桥梁", BridgeDTO.class, bridgeDTOS);
    }
}
