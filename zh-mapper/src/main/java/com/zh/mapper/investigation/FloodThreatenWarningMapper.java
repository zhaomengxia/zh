package com.zh.mapper.investigation;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.FloodThreatenWarningDTO;
import com.zh.entity.investigation.FloodThreatenWarning;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 外洪威胁村落水位预警指示表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
public interface FloodThreatenWarningMapper extends BaseMapper<FloodThreatenWarning> {

    IPage<FloodThreatenWarning> getPageList(Page<FloodThreatenWarning> page, @Param("keyword") String keyword);

    List<FloodThreatenWarningDTO> getList(@Param("keyword") String keyword);
}
