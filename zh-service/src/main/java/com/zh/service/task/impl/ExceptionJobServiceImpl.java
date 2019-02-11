package com.zh.service.task.impl;

import com.zh.dto.task.ExceptionJobDTO;
import com.zh.entity.task.ExceptionJob;
import com.zh.mapper.task.ExceptionJobMapper;
import com.zh.service.task.ExceptionJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 异常任务表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
@Service
public class ExceptionJobServiceImpl extends ServiceImpl<ExceptionJobMapper, ExceptionJob> implements ExceptionJobService {

    @Resource
    private ExceptionJobMapper exceptionJobMapper;

    @Override
    public List<ExceptionJobDTO> queryTaskList(Long userId, Integer status) {
        return exceptionJobMapper.selectTaskList(userId, status);
    }

    @Override
    public List<ExceptionJobDTO> queryAssignTaskList(Long userId, Integer taskStatus, Integer exceptionStatus) {
        return exceptionJobMapper.selectAssignTaskList(userId, taskStatus, exceptionStatus);
    }
}
