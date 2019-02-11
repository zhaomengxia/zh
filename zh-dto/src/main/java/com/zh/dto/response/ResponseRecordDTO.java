package com.zh.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/13 15:31
 */
@Data
@ApiModel
public class ResponseRecordDTO {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预警记录id")
    private Long warningRecordId;

    @ApiModelProperty(value = "响应等级")
    private Integer responseDegree;

    @ApiModelProperty(value = "响应等级名称")
    private String responseDegreeName;

    @ApiModelProperty(value = "响应内容")
    private String responseContent;

    @ApiModelProperty(value = "响应状态")
    private String responseStatus;

    @ApiModelProperty(value = "防汛负责人")
    private String chargeMan;

    @ApiModelProperty(value = "防汛部门")
    private String department;

    @ApiModelProperty(value = "响应启动时间")
    private Long responseTime;
}
