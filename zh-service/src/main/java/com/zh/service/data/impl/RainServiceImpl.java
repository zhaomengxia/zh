package com.zh.service.data.impl;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.vo.BaseEntityTypeConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.service.BaseService;
import com.zh.service.data.RainService;
import com.zh.service.data.SiteFactorDataHourService;
import com.zh.service.data.SiteFactorDataMinuteService;
import com.zh.util.DateUtil;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author  赵梦霞
 * @since 2019-01-04 14:28

 **/
@Service
public class RainServiceImpl extends BaseService implements RainService {

    @Resource
    private SiteFactorDataMinuteService siteFactorDataMinuteService;
    @Resource
    private SiteFactorDataHourService siteFactorDataHourService;


    @Override
    public List<RainListDTO> list(QueryDTO queryDTO) {
        return siteFactorDataMinuteService.rainList(queryDTO);
    }

    @Override
    public List<List<RainListDTO>> strength(QueryDTO queryDTO) {

        List<List<RainListDTO>> result = Lists.newArrayList();

        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() <= 9.9).collect(Collectors.toList()));
        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() >= 10 && e.getValue() <= 24.9).collect(Collectors.toList()));
        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() >= 25 && e.getValue() <= 49.9).collect(Collectors.toList()));
        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() >= 50 && e.getValue() <= 99.9).collect(Collectors.toList()));
        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() >= 100 && e.getValue() <= 249.9).collect(Collectors.toList()));
        result.add(this.list(queryDTO).parallelStream().filter(e -> e.getValue() >= 250).collect(Collectors.toList()));

        return result;

    }

    @Override
    public List<RainListDTO> polyline(QueryDTO queryDTO) {

        if (this.judgeTimeSection(queryDTO.getStart(), queryDTO.getEnd())) {
            return siteFactorDataMinuteService.rainSection(queryDTO);
        }

        return siteFactorDataHourService.rainSection(queryDTO);
    }

    @Override
    public Map<String, Double> histogram(QueryDTO queryDTO) {
        Long time = queryDTO.getTime();
        Instant instant = Instant.ofEpochMilli(time);
        Map<String, Double> result = Maps.newLinkedHashMap();
        result.put("one", siteFactorDataMinuteService.accumulative(queryDTO.getId(), instant.minus(1, ChronoUnit.HOURS).toEpochMilli(), time));
        result.put("three", siteFactorDataMinuteService.accumulative(queryDTO.getId(), instant.minus(3, ChronoUnit.HOURS).toEpochMilli(), time));
        result.put("six", siteFactorDataMinuteService.accumulative(queryDTO.getId(), instant.minus(6, ChronoUnit.HOURS).toEpochMilli(), time));
        result.put("twelve", siteFactorDataMinuteService.accumulative(queryDTO.getId(), instant.minus(12, ChronoUnit.HOURS).toEpochMilli(), time));
        result.put("twentyFour", siteFactorDataMinuteService.accumulative(queryDTO.getId(), instant.minus(24, ChronoUnit.HOURS).toEpochMilli(), time));
        result.put("untilNow", siteFactorDataMinuteService.accumulative(queryDTO.getId(), DateUtil.getTodayZero().toEpochMilli(), time));
        result.put("eightUtilNow", siteFactorDataMinuteService.accumulative(queryDTO.getId(), DateUtil.getTodayZero().plus(8, ChronoUnit.HOURS).toEpochMilli(), time));
        return result;
    }

    @Override
    public void export(HttpServletResponse response, QueryDTO queryDTO) throws IOException {
        //构造excel
        List<ExcelExportEntity> entity = Lists.newArrayList();
        ExcelExportEntity excelentity = new ExcelExportEntity("序号", "serial", 20);
        excelentity.setType(BaseEntityTypeConstants.DOUBLE_TYPE);
        entity.add(excelentity);
        entity.add(new ExcelExportEntity("名称", "name", 20));
        entity.add(new ExcelExportEntity("累计雨量", "value", 30));
        //查询数据
        List<RainListDTO> list = this.list(queryDTO);
        List<Map<String, Object>> data = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = new HashMap<>(6);
            map.put("serial", i + 1);
            map.put("name", list.get(i).getName());
            map.put("value", list.get(i).getValue());
            data.add(map);
        }
        ExcelHelper.exportExcel(response, entity, data, "雨量信息", "雨量信息");
    }

}
