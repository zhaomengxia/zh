package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.dto.wading.BridgeDTO;
import com.zh.entity.basic.RiverFloodProtection;
import com.zh.mapper.basic.RiverFloodProtectionMapper;
import com.zh.service.basic.RiverFloodProtectionService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 中小型河流抗洪能力 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Service
public class RiverFloodProtectionServiceImpl extends ServiceImpl<RiverFloodProtectionMapper, RiverFloodProtection> implements RiverFloodProtectionService {

    @Resource
    RiverFloodProtectionMapper riverFloodProtectionMapper;

    @Override
    public Result getListPage(Page<RiverFloodProtection> page, String keyword) {
        IPage<RiverFloodProtection> list = riverFloodProtectionMapper.getListPage(page, keyword);
        return Result.success(list);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<RiverFloodProtection> riverFloodProtections=new ArrayList<>();
        List<RiverFloodProtection> riverFloodProtections1=riverFloodProtectionMapper.getListPage(keywords);
        int i=1;
        for (RiverFloodProtection riverFloodProtection:riverFloodProtections1){
            if (riverFloodProtection.getHasDike()!=null) {
                if (riverFloodProtection.getHasDike()) {
                    riverFloodProtection.setHasDikes("是");
                } else {
                    riverFloodProtection.setHasDikes("否");
                }
            }
            else{
                riverFloodProtection.setHasDikes("");
            }
            riverFloodProtection.setSerialNumber(i);
            riverFloodProtections.add(riverFloodProtection);
            i++;
        }
        ExcelHelper.exportExcel(response, "中小型河流抗洪能力", "中小型河流抗洪能力", RiverFloodProtection.class, riverFloodProtections);
    }
}
