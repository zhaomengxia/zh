package com.zh.service.investigation.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.api.Result;
import com.zh.dto.basic.LowEarlyWarningDTO;
import com.zh.entity.investigation.LowEarlyWarning;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.investigation.LowEarlyWarningMapper;
import com.zh.service.investigation.LowEarlyWarningService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 低洼易涝村落雨量预警指标表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
@Service
public class LowEarlyWarningServiceImpl extends ServiceImpl<LowEarlyWarningMapper, LowEarlyWarning> implements LowEarlyWarningService {

    @Resource
    LowEarlyWarningMapper lowEarlyWarningMapper;

    @Override
    public boolean importExcel(MultipartFile file) {
        List<LowEarlyWarning> data = Lists.newArrayList();
        try {
            List<List<Object>> lists = ExcelHelper.importExcel(file.getInputStream(), 2, 0);
            for (int i = 0; i < lists.size(); i++) {
                data.add(LowEarlyWarning.builder()
                        .divisionName(lists.get(1).toString())
                        .divisionCode(lists.get(2).toString())
                        .earlyWarningTime(lists.get(3).toString())
                        .earlyWarningRain(lists.get(4).toString())
                        .build());
            }

        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
        }

        return this.saveBatch(data);

    }

    @Override
    public Result getListPage(Page<LowEarlyWarning> page, String keywords) {
        return Result.success(lowEarlyWarningMapper.getListPage(page, keywords));
    }

    @Override
    public void exportLowEarlyWarning(HttpServletResponse response, String keywords) {
        List<LowEarlyWarningDTO> lowEarlyWarningDTOS = lowEarlyWarningMapper.getList(keywords);
        Integer i = 0;
        for(LowEarlyWarningDTO lowEarlyWarning : lowEarlyWarningDTOS){
            if(lowEarlyWarning != null){
                i++;
                lowEarlyWarning.setIndex(i);
            }
        }
        try {
            ExcelHelper.exportExcel(response, "低洼易涝村落雨量预警指标", "低洼易涝村落雨量预警指标",
                    LowEarlyWarningDTO.class, lowEarlyWarningDTOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
