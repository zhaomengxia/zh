package com.zh.mapper.warning;

import com.zh.entity.warning.RainWarningTimeSection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 降雨预警时间区间 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-10
 */
public interface RainWarningTimeSectionMapper extends BaseMapper<RainWarningTimeSection> {

    List<RainWarningTimeSection> getList();
}
