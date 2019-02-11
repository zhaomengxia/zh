package com.zh.dto.task;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zh.entity.task.ExceptionJobAttachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 异常任务查询数据传输
 *
 * @author  赵梦霞
 * @since 2019-01-14 11:49

 **/
@Data
@ApiModel
public class ExceptionJobDTO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "任务标题")
    private String title;

    @ApiModelProperty(value = "任务说明")
    private String instruction;

    @ApiModelProperty(value = "指派人id")
    private Long assignMan;

    @ApiModelProperty(value = "指派人姓名")
    private String assignName;

    @ApiModelProperty(value = "指派时间")
    private Long assignTime;

    @ApiModelProperty(value = "执行人主键")
    private Long executeMan;

    @ApiModelProperty(value = "执行人姓名")
    private String executeName;

    @ApiModelProperty(value = "截止日")
    private Long deadline;

    @ApiModelProperty(value = "状态：1：待办任务2：已办任务")
    private Integer status;

    @ApiModelProperty(value = "反馈说明")
    private String feedbackDesc;

    @ApiModelProperty(value = "完成时间")
    private Long finishTime;

    @ApiModelProperty(value = "异常记录")
    private ExceptionRecordDTO exceptionRecord;

    @ApiModelProperty(value = "任务关联附件")
    private List<ExceptionJobAttachment> attachments;


}
