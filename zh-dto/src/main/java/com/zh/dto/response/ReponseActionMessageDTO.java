package com.zh.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/11 15:36
 */
@Data
@ApiModel
public class ReponseActionMessageDTO {
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "响应记录id")
    private Long responseRecordId;


    @ApiModelProperty(value = "行政区域编码")
    private String areaCode;

    @ApiModelProperty(value = "行政区域名称")
    private String areaName;

    @ApiModelProperty(value = "反馈人id")
    private Long receiverId;

    @ApiModelProperty(value = "反馈人")
    private String userName;

    @ApiModelProperty(value = "反馈时间")
    private Long feedbackTime;

    @ApiModelProperty(value = "反馈内容")
    private String feedbackContent;

    @ApiModelProperty(value = "是否已反馈")
    private Boolean feedback;

    @ApiModelProperty(value = "反馈部门id")
    private Long organizationId;

    @ApiModelProperty(value = "反馈部门")
    private String organizationName;
}
