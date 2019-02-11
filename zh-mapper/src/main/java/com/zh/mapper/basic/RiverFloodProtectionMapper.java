package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.entity.basic.RiverFloodProtection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 中小型河流抗洪能力 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface RiverFloodProtectionMapper extends BaseMapper<RiverFloodProtection> {

    IPage<RiverFloodProtection> getListPage(Page<RiverFloodProtection> page, @Param("keyword") String keyword);

    List<RiverFloodProtection> getListPage(@Param("keyword") String keyword);
}
