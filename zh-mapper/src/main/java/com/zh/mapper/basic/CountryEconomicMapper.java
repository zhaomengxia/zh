package com.zh.mapper.basic;

import com.zh.entity.basic.CountryEconomic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface CountryEconomicMapper extends BaseMapper<CountryEconomic> {

    List<CountryEconomic> getList(@Param("keyword") String keyword);
}
