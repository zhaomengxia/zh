package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.api.Result;
import com.zh.entity.basic.Polder;
import com.zh.entity.basic.RiverFloodProtection;
import com.zh.mapper.basic.PolderMapper;
import com.zh.service.basic.PolderService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 圩区 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
@Service
public class PolderServiceImpl extends ServiceImpl<PolderMapper, Polder> implements PolderService {

    @Resource
    PolderMapper polderMapper;

    @Override
    public Result getListPage(Page<Polder> page, String keywords) {
        return Result.success(polderMapper.getListPage(page, keywords));
    }

    @Override
    public void exportExcel(HttpServletResponse response, String keywords) throws IOException {
        List<Polder> polders=new ArrayList<>();
        List<Polder> polders1=polderMapper.getListPage(keywords);
        int i=1;
        for (Polder polder:polders1){
            polder.setSerialNumber(i);
            polders.add(polder);
            i++;
        }
        ExcelHelper.exportExcel(response, "圩区", "圩区", Polder.class, polders);

    }
}
