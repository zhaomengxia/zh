package com.zh.mapper.task;

import com.zh.entity.task.ExceptionRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 异常记录表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
public interface ExceptionRecordMapper extends BaseMapper<ExceptionRecord> {

    List<ExceptionRecord> selectExceptionList(@Param("userId") Long userId, @Param("status") Integer status);
}
