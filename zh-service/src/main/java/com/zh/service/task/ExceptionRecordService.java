package com.zh.service.task;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.entity.task.ExceptionRecord;

import java.util.List;

/**
 * <p>
 * 异常记录表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
public interface ExceptionRecordService extends IService<ExceptionRecord> {

    List<ExceptionRecord> queryExceptionList(Long userId, Integer status);

}
