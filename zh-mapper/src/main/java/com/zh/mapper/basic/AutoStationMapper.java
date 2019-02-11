package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.basic.AutoStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 自动监测站 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
public interface AutoStationMapper extends BaseMapper<AutoStation> {

    IPage<AutoStation> getPageList(Page<AutoStation> page, @Param("keywords")String keywords);
    List<AutoStation> getPageList(@Param("keywords")String keywords);
}
