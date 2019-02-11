package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.entity.basic.AutoStation;
import com.zh.entity.basic.Polder;
import com.zh.mapper.basic.AutoStationMapper;
import com.zh.service.basic.AutoStationService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自动监测站 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
@Service
public class AutoStationServiceImpl extends ServiceImpl<AutoStationMapper, AutoStation> implements AutoStationService {

    @Resource
    AutoStationMapper autoStationMapper;

    @Override
    public Result getPageList(Page<AutoStation> page, String keyword) {
        return Result.success(autoStationMapper.getPageList(page, keyword));
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<AutoStation> autoStations=new ArrayList<>();
        List<AutoStation> autoStations1=autoStationMapper.getPageList(keywords);
        int i=1;
        for (AutoStation autoStation:autoStations1){
            autoStation.setSerialNumber(i);
            autoStations.add(autoStation);
            i++;
        }
        ExcelHelper.exportExcel(response, "自动监测站", "自动监测站", AutoStation.class, autoStations);

    }
}
