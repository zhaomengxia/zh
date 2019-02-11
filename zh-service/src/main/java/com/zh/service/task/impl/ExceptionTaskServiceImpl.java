package com.zh.service.task.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zh.dto.task.ExceptionJobDTO;
import com.zh.entity.sys.SysUser;
import com.zh.entity.task.ExceptionAttachment;
import com.zh.entity.task.ExceptionJob;
import com.zh.entity.task.ExceptionJobAttachment;
import com.zh.entity.task.ExceptionRecord;
import com.zh.enums.ExceptionStatusEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.security.util.SecurityUtil;
import com.zh.service.task.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

/**
 * @author  赵梦霞
 * @since 2019-01-14 10:24

 **/
@Service
public class ExceptionTaskServiceImpl implements ExceptionTaskService {

    @Resource
    private ExceptionRecordService exceptionRecordService;
    @Resource
    private ExceptionAttachmentService exceptionAttachmentService;
    @Resource
    private ExceptionJobService exceptionJobService;
    @Resource
    private ExceptionJobAttachmentService exceptionJobAttachmentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean export(ExceptionRecord record) {
        SysUser user = (SysUser) SecurityUtil.getCurrentUserInfo();
        record.setStatus(ExceptionStatusEnum.REPORT.getStatus());
        record.setReportMan(user.getId());
        record.setReportName(user.getName());
        if (exceptionRecordService.save(record)) {
            List<ExceptionAttachment> attachments = record.getAttachments();
            attachments.forEach(e -> e.setExceptionId(record.getId()));
            return exceptionAttachmentService.saveBatch(attachments);
        }
        throw new UnifiedException("异常上报失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assign(ExceptionJob job) {
        SysUser user = (SysUser) SecurityUtil.getCurrentUserInfo();
        job.setAssignMan(user.getId());
        job.setAssignName(user.getName());
        job.setAssignTime(Instant.now().toEpochMilli());
        job.setStatus(ExceptionStatusEnum.NEW_TASK.getStatus());
        //任务表插入一条记录
        if (exceptionJobService.save(job)) {
            ExceptionRecord record = exceptionRecordService.getById(job.getExceptionId());
            if (record == null) {
                throw new UnifiedException("异常已不存在");
            }
            //更改异常记录状态
            record.setStatus(ExceptionStatusEnum.HANDLE.getStatus());
            return exceptionRecordService.updateById(record);
        }
        throw new UnifiedException("任务指派失败");
    }

    @Override
    public List<ExceptionJobDTO> queryTask(Integer status) {
        SysUser user = (SysUser) SecurityUtil.getCurrentUserInfo();
        return exceptionJobService.queryTaskList(user.getId(), status);
    }

    @Override
    public boolean complete(ExceptionJob exceptionJob) {
        ExceptionJob job = exceptionJobService.getById(exceptionJob.getId());
        if (job == null) {
            throw new UnifiedException("查询不到此条任务记录");
        }
        if (!job.getStatus().equals(ExceptionStatusEnum.NEW_TASK.getStatus())) {
            throw new UnifiedException("此条任务状态不是待办");
        }
        ExceptionRecord record = exceptionRecordService.getById(job.getExceptionId());
        if (record == null) {
            throw new UnifiedException("查询不到此条任务关联异常信息");
        }
        if (!record.getStatus().equals(ExceptionStatusEnum.HANDLE.getStatus())) {
            throw new UnifiedException("查询不到此条任务关联异常状态不为待处理");
        }
        exceptionJob.setStatus(ExceptionStatusEnum.COMPLETE_TASK.getStatus());
        if (exceptionJobService.updateById(exceptionJob)) {
            List<ExceptionJobAttachment> attachments = exceptionJob.getAttachments();
            //保存附件
            attachments.forEach(e -> e.setExceptionJobId(exceptionJob.getId()));
            exceptionJobAttachmentService.saveBatch(attachments);
            record.setStatus(ExceptionStatusEnum.REVIEW.getStatus());
            return exceptionRecordService.updateById(record);
        }

        throw new UnifiedException("任务反馈失败");

    }

    @Override
    public boolean review(Long exceptionId) {
        ExceptionJob job = exceptionJobService.getOne(new LambdaQueryWrapper<ExceptionJob>()
                .eq(ExceptionJob::getExceptionId, exceptionId)
                .eq(ExceptionJob::getStatus, ExceptionStatusEnum.COMPLETE_TASK.getStatus()));
        if (job == null) {
            throw new UnifiedException("查询不到此条任务");
        }
        if (!job.getStatus().equals(ExceptionStatusEnum.COMPLETE_TASK.getStatus())) {
            throw new UnifiedException("此条任务状态不为已完成");
        }
        ExceptionRecord record = exceptionRecordService.getOne(new LambdaQueryWrapper<ExceptionRecord>()
                .eq(ExceptionRecord::getId, exceptionId));
        if (record == null) {
            throw new UnifiedException("此条异常信息不存在");
        }
        if (!record.getStatus().equals(ExceptionStatusEnum.REVIEW.getStatus())) {
            throw new UnifiedException("此条异常信息状态不为待审核");
        }
        record.setStatus(ExceptionStatusEnum.COMPLETE.getStatus());
        return exceptionRecordService.updateById(record);

    }

    @Override
    public List<ExceptionRecord> queryNewException() {
        SysUser user = (SysUser) SecurityUtil.getCurrentUserInfo();
        return exceptionRecordService.queryExceptionList(user.getId(), ExceptionStatusEnum.REPORT.getStatus());
    }

    @Override
    public List<ExceptionJobDTO> queryHandleException(Long userId) {
        return exceptionJobService.queryAssignTaskList(userId, ExceptionStatusEnum.NEW_TASK.getStatus(), ExceptionStatusEnum.HANDLE.getStatus());
    }

    @Override
    public List<ExceptionJobDTO> queryReviewException(Long userId) {
        return exceptionJobService.queryAssignTaskList(userId, ExceptionStatusEnum.COMPLETE_TASK.getStatus(), ExceptionStatusEnum.REVIEW.getStatus());
    }

    @Override
    public List<ExceptionJobDTO> queryCompleteException(Long userId) {
        return exceptionJobService.queryAssignTaskList(userId, ExceptionStatusEnum.COMPLETE_TASK.getStatus(), ExceptionStatusEnum.COMPLETE.getStatus());
    }

    @Override
    public List<ExceptionRecord> queryNewExceptionList() {
        return exceptionRecordService.queryExceptionList(null, ExceptionStatusEnum.REPORT.getStatus());
    }

}
