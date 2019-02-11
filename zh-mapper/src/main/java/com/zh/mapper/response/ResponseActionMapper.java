package com.zh.mapper.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.response.ResponseActionDTO;
import com.zh.entity.response.ResponseAction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 响应行动 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
public interface ResponseActionMapper extends BaseMapper<ResponseAction> {
    IPage<ResponseAction> selectAllPage(Page<ResponseAction> page,@Param("keywords") String keywords);

    List<ResponseActionDTO> selectAllList();
}
