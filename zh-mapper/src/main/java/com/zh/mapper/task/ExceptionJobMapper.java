package com.zh.mapper.task;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.dto.task.ExceptionJobDTO;
import com.zh.entity.task.ExceptionJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 异常任务表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
public interface ExceptionJobMapper extends BaseMapper<ExceptionJob> {

    /**
     * @param userId 用户id
     * @author  赵梦霞
     * @Description 查询被分配的任务列表
     * @since 2019/1/14 16:51
     **/
    List<ExceptionJobDTO> selectTaskList(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * @param userId 用户id
     * @param taskStatus 任务状态
     * @param exceptionStatus 异常状态
     * @author  赵梦霞
     * @Description 查询自己分配的任务列表
     * @since 2019/1/14 16:52
     **/
    List<ExceptionJobDTO> selectAssignTaskList(@Param("userId") Long userId, @Param("taskStatus") Integer taskStatus, @Param("exceptionStatus") Integer exceptionStatus);
}
