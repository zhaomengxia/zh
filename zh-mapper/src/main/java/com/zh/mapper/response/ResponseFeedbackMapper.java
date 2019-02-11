package com.zh.mapper.response;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.response.ResponseFeedbackDTO;
import com.zh.dto.response.ResponseFeedbackMapDTO;
import com.zh.entity.response.ResponseFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 响应反馈 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
public interface ResponseFeedbackMapper extends BaseMapper<ResponseFeedback> {
    IPage<ResponseFeedbackDTO> selectAllFeedback(Page<ResponseFeedbackDTO> page, @Param("id") Long id, @Param("f") Boolean f);

    List<ResponseFeedbackDTO> selectAllFeedback(@Param("id") Long id, @Param("f") Boolean f);

   List<ResponseFeedbackMapDTO> selectAllFeedbackMap( @Param("id") Long id, @Param("f") Boolean f);
}
