package com.zh.service.task;

import com.zh.dto.task.ExceptionJobDTO;
import com.zh.entity.task.ExceptionJob;
import com.zh.entity.task.ExceptionRecord;

import java.util.List;

/**
 * 异常任务 service 接口
 *
 * @author  赵梦霞
 * @date 2019-01-14 10:23

 **/
public interface ExceptionTaskService {

    boolean export(ExceptionRecord record);

    boolean assign(ExceptionJob job);

    List<ExceptionJobDTO> queryTask(Integer status);

    boolean complete(ExceptionJob exceptionJob);

    boolean review(Long exceptionId);

    List<ExceptionRecord> queryNewException();

    List<ExceptionJobDTO> queryHandleException(Long userId);

    List<ExceptionJobDTO> queryReviewException(Long userId);

    List<ExceptionJobDTO> queryCompleteException(Long userId);

    List<ExceptionRecord> queryNewExceptionList();
}
