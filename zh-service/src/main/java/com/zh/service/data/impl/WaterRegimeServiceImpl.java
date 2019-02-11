package com.zh.service.data.impl;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.vo.BaseEntityTypeConstants;
import com.google.common.collect.Lists;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.service.BaseService;
import com.zh.service.data.SiteFactorDataHourService;
import com.zh.service.data.SiteFactorDataMinuteService;
import com.zh.service.data.WaterRegimeService;
import com.zh.util.DateUtil;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  赵梦霞
 * @since 2019-01-07 11:01

 **/
@Service
public class WaterRegimeServiceImpl extends BaseService implements WaterRegimeService {

    @Resource
    private SiteFactorDataMinuteService siteFactorDataMinuteService;
    @Resource
    private SiteFactorDataHourService siteFactorDataHourService;

    @Override
    public List<WaterListDTO> list(QueryDTO queryDTO) {
        return siteFactorDataMinuteService.waterList(queryDTO);
    }

    @Override
    public List<WaterListDTO> section(QueryDTO queryDTO) {
        if (this.judgeTimeSection(queryDTO.getStart(), queryDTO.getEnd())) {
            return siteFactorDataMinuteService.waterSection(queryDTO);
        }
        return siteFactorDataHourService.waterSection(queryDTO);
    }

    @Override
    public void export(HttpServletResponse response, QueryDTO queryDTO) throws IOException {
        //构造excel
        List<ExcelExportEntity> entity = Lists.newArrayList();
        ExcelExportEntity excelentity1 = new ExcelExportEntity("序号", "serial", 20);
        excelentity1.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        ExcelExportEntity excelentity2 = new ExcelExportEntity("名称", "name", 20);
        ExcelExportEntity excelentity3 = new ExcelExportEntity("上游水位", "upperStream", 30);
        excelentity3.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        ExcelExportEntity excelentity4 = new ExcelExportEntity("下游水位", "downStream", 30);
        excelentity4.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        ExcelExportEntity excelentity5 = new ExcelExportEntity("历史最高水位", "historyLevel", 30);
        excelentity5.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        ExcelExportEntity excelentity6 = new ExcelExportEntity("历史最高水位出现时间", "historyLevelTime", 30);
        entity.add(excelentity1);
        entity.add(excelentity2);
        entity.add(excelentity3);
        entity.add(excelentity4);
        entity.add(excelentity5);
        entity.add(excelentity6);
        //查询数据
        List<WaterListDTO> list = this.list(queryDTO);
        List<Map<String, Object>> data = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = new HashMap<>(6);
            map.put("serial", i + 1);
            map.put("name", list.get(i).getName());
            map.put("upperStream", list.get(i).getUpperStream());
            map.put("downStream", list.get(i).getDownStream());
            map.put("historyLevel", list.get(i).getHistoryLevel());
            map.put("historyLevelTime", DateUtil.parse("yyyy-MM-dd HH:mm:ss", list.get(i).getHistoryLevelTime()));
            data.add(map);
        }
        ExcelHelper.exportExcel(response, entity, data, "水位信息", "水位信息");
    }
}
