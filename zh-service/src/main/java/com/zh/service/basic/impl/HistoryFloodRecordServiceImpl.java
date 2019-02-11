package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.entity.basic.AutoStation;
import com.zh.entity.basic.HistoryFloodRecord;
import com.zh.mapper.basic.HistoryFloodRecordMapper;
import com.zh.service.basic.HistoryFloodRecordService;
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
public class HistoryFloodRecordServiceImpl extends ServiceImpl<HistoryFloodRecordMapper, HistoryFloodRecord> implements HistoryFloodRecordService {

    @Resource
    HistoryFloodRecordMapper historyFloodRecordMapper;

    @Override
    public Result getListPage(Page<HistoryFloodRecord> page, String keyword) {
        IPage<HistoryFloodRecord> historyFloodRecordIPage = historyFloodRecordMapper.getListPage(page, keyword);
        return Result.success(historyFloodRecordIPage);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<HistoryFloodRecord> historyFloodRecords=new ArrayList<>();
        List<HistoryFloodRecord> historyFloodRecords1=historyFloodRecordMapper.getListPage(keywords);
        int i=1;
        for (HistoryFloodRecord historyFloodRecord:historyFloodRecords1){
            historyFloodRecord.setSerialNumber(i);
            historyFloodRecords.add(historyFloodRecord);
            i++;
        }
        ExcelHelper.exportExcel(response, "历史洪涝灾害", "历史洪涝灾害", HistoryFloodRecord.class, historyFloodRecords);
    }
}
