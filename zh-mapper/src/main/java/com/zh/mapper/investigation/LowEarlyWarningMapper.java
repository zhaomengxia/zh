package com.zh.mapper.investigation;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.LowEarlyWarningDTO;
import com.zh.entity.investigation.LowEarlyWarning;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 低洼易涝村落雨量预警指标表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
public interface LowEarlyWarningMapper extends BaseMapper<LowEarlyWarning> {

    IPage<LowEarlyWarning> getListPage(Page<LowEarlyWarning> page, @Param("keyword")String keyword);

    List<LowEarlyWarningDTO> getList(@Param("keyword") String keyword);
}
