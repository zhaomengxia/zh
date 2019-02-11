package com.zh.service.task;

import com.zh.dto.task.ExceptionJobDTO;
import com.zh.entity.task.ExceptionJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 异常任务表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
public interface ExceptionJobService extends IService<ExceptionJob> {

    //List<ExceptionJobDTO> queryTaskList(Long userId, Integer status);

    List<ExceptionJobDTO> queryTaskList(Long userId, Integer status);

    List<ExceptionJobDTO> queryAssignTaskList(Long userId, Integer taskStatus, Integer exceptionStatus);
}
