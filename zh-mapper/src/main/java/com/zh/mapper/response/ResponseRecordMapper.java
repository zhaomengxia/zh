package com.zh.mapper.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.response.ResponseManageDTO;
import com.zh.dto.response.ResponseRecordDTO;
import com.zh.entity.response.ResponseRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预警响应记录表 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface ResponseRecordMapper extends BaseMapper<ResponseRecord> {
    IPage<ResponseManageDTO> selectAll(Page<ResponseManageDTO> page, @Param("keywords") String keywords);

    List<ResponseManageDTO> selectAll(@Param("keywords") String keywords);

    ResponseRecordDTO selectResponseStartByWarnId(@Param("id") Long id,@Param("type") Integer type);
}
