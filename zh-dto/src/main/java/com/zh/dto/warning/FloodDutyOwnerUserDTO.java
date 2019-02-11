package com.zh.dto.warning;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/15 13:44
 */
@Data
@ApiModel
public class FloodDutyOwnerUserDTO {
    @ApiModelProperty(value = "防汛责任人类型id")
    private Long dutyId;
    @ApiModelProperty(value = "防汛责任人类型名称")
    private String dutyName;
    @ApiModelProperty(value = "防汛责任人id")
    private Long userId;
    @ApiModelProperty(value = "防汛责任人姓名")
    private String userName;
}
