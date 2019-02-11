package com.zh.service.basic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.basic.BasicDivisionOrganizationDTO;
import com.zh.entity.basic.Material;
import com.zh.mapper.basic.MaterialMapper;
import com.zh.service.basic.MaterialService;
import com.zh.util.file.excel.ExcelHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 物资表 服务实现类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;
    @Override
    public IPage<Material> selectAllByMessage(Page<Material> materialPage,String keywords,Long warehourseId) {
        return materialMapper.selectAllByMessage(materialPage,keywords,warehourseId);
    }

    @Override
    public void exportExcel(HttpServletResponse response, Long id, String keywords) throws IOException {
        List<Material> materials=materialMapper.selectAllByMessage(keywords,id);
        ExcelHelper.exportExcel(response, "防汛物资", "防汛物资", Material.class, materials);

    }
}
