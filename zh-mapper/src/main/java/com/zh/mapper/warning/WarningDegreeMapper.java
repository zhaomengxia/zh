package com.zh.mapper.warning;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.warning.WarningDegree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Wrapper;

/**
 * <p>
 * 预警等级表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
public interface WarningDegreeMapper extends BaseMapper<WarningDegree> {

    IPage<WarningDegree> getWarningDegreeByPage(Page<WarningDegree> page, Wrapper wrapper);
}
