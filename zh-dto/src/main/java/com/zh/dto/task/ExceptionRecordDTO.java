package com.zh.dto.task;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zh.entity.task.ExceptionAttachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 异常记录数据传输
 *
 * @author  赵梦霞
 * @since 2019-01-14 11:52

 **/
@Data
@ApiModel
public class ExceptionRecordDTO {

    @ApiModelProperty(value = "异常信息主键")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "异常点位")
    private String exceptionPoint;

    @ApiModelProperty(value = "上报时间（时间戳）")
    private Long reportTime;

    @ApiModelProperty(value = "说明")
    private String instruction;

    @ApiModelProperty(value = "异常状态1：新上报2：处置中3：待审核4：已完成")
    private Integer status;

    @ApiModelProperty(value = "上报人id")
    private Long reportMan;

    @ApiModelProperty(value = "上报人姓名")
    private String reportName;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件")
    List<ExceptionAttachment> attachments;
}
