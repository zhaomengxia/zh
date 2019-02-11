package com.zh.service.investigation.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.api.Result;
import com.zh.dto.basic.FloodThreatenWarningDTO;
import com.zh.entity.investigation.FloodThreatenWarning;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.investigation.FloodThreatenWarningMapper;
import com.zh.service.investigation.FloodThreatenWarningService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 外洪威胁村落水位预警指示表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
@Service
public class FloodThreatenWarningServiceImpl extends ServiceImpl<FloodThreatenWarningMapper, FloodThreatenWarning> implements FloodThreatenWarningService {

    @Resource
    FloodThreatenWarningMapper floodThreatenWarningMapper;

    @Override
    public boolean importExcel(MultipartFile file) {

        List<FloodThreatenWarning> data = Lists.newArrayList();
        try {
            List<List<Object>> lists = ExcelHelper.importExcel(file.getInputStream(), 2, 0);
            for (int i = 0; i < lists.size(); i++) {
                data.add(FloodThreatenWarning.builder()
                        .divisionName(lists.get(1).toString())
                        .divisionCode(lists.get(2).toString())
                        .riverName(lists.get(3).toString())
                        .riverCode(lists.get(4).toString())
                        .riverControlCode(lists.get(5).toString())
                        .warningSiteName(lists.get(6).toString())
                        .warningSiteCode(lists.get(7).toString())
                        .warningStyle(lists.get(8).toString())
                        .warningValue(lists.get(9).toString())
                        .remark(lists.get(10).toString())
                        .build());
            }
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
        }

        return this.saveBatch(data);
    }

    @Override
    public Result getListPage(Page<FloodThreatenWarning> page, String keyword) {
        return Result.success(floodThreatenWarningMapper.getPageList(page, keyword));
    }

    @Override
    public void exportFloodThreatenWarning(HttpServletResponse response, String keywords) {
        List<FloodThreatenWarningDTO> floodThreatenWarningDTOS = floodThreatenWarningMapper.getList(keywords);
        Integer index = 0;
        for(FloodThreatenWarningDTO floodThreatenWarningDTO : floodThreatenWarningDTOS){
            if(floodThreatenWarningDTO != null){
                index ++;
                floodThreatenWarningDTO.setIndex(index);
            }
        }
        try {
            ExcelHelper.exportExcel(response, "外洪威胁村落水位预警指示", "外洪威胁村落水位预警指示",
                    FloodThreatenWarningDTO.class, floodThreatenWarningDTOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
