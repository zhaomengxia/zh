package com.zh.service.task.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.entity.task.ExceptionRecord;
import com.zh.mapper.task.ExceptionRecordMapper;
import com.zh.service.task.ExceptionRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 异常记录表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
@Service
public class ExceptionRecordServiceImpl extends ServiceImpl<ExceptionRecordMapper, ExceptionRecord> implements ExceptionRecordService {

    @Resource
    private ExceptionRecordMapper exceptionRecordMapper;

    @Override
    public List<ExceptionRecord> queryExceptionList(Long userId, Integer status) {
        return exceptionRecordMapper.selectExceptionList(userId, status);
    }

}
