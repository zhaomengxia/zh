package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.dto.basic.EnterpriseDTO;
import com.zh.entity.basic.Enterprise;
import com.zh.entity.basic.HistoryFloodRecord;
import com.zh.mapper.basic.EnterpriseMapper;
import com.zh.service.basic.EnterpriseService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService {

    @Resource
    EnterpriseMapper enterpriseMapper;

    @Override
    public Result getEnterpriseListPage(Page<EnterpriseDTO> page, String keyword) {
        IPage<EnterpriseDTO> enterpriseDTOIPage = enterpriseMapper.getEnterpriseListPage(page, keyword);
        return Result.success(enterpriseDTOIPage);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<EnterpriseDTO> enterpriseDTOS=new ArrayList<>();
        List<EnterpriseDTO> enterpriseDTOS1=enterpriseMapper.getEnterpriseListPage(keywords);
        int i=1;
        for (EnterpriseDTO enterpriseDTO:enterpriseDTOS1){
            if (enterpriseDTO.getFloodThreat()!=null) {
                if (enterpriseDTO.getFloodThreat()) {
                    enterpriseDTO.setFloodThreats("是");
                } else {
                    enterpriseDTO.setFloodThreats("否");
                }
            }
            else{
                enterpriseDTO.setFloodThreats("");
            }
            if (enterpriseDTO.getWaterlogged()!=null) {
                if (enterpriseDTO.getWaterlogged()) {
                    enterpriseDTO.setWaterloggs("是");
                } else {
                    enterpriseDTO.setWaterloggs("否");
                }
            }
            else{
                enterpriseDTO.setWaterloggs("");
            }
            enterpriseDTO.setSerialNumber(i);
            enterpriseDTOS.add(enterpriseDTO);
            i++;
        }
        ExcelHelper.exportExcel(response, "企事业单位", "企事业单位", EnterpriseDTO.class, enterpriseDTOS);
    }
}
