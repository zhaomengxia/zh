package com.zh.dto.sys.site;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 13:34
 */
@Data
@ApiModel
public class DeviceTypeAndFactorIdDTO {
    @ApiModelProperty(value = "设备类型id")
    private Long deviceTypeId;
    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;
    @ApiModelProperty(value = "因子id")
    private Long factorId;
    @ApiModelProperty(value = "因子名称名称")
    private String factorName;
    @ApiModelProperty(value = "因子单位")
    private String factorUnit;
}
