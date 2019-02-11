package com.zh.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.basic.EnterpriseDTO;
import com.zh.entity.basic.Enterprise;
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
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    IPage<EnterpriseDTO> getEnterpriseListPage(Page<EnterpriseDTO> page, @Param("keyword") String keyword);

    List<EnterpriseDTO> getEnterpriseListPage(@Param("keyword") String keyword);
}
