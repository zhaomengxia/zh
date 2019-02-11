package com.zh.dto.sys.site;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/26 9:47
 */
@Data
@ApiModel
public class DeviceFactorDTO {
    /**
     * 设备因子id
     */
    @ApiModelProperty(value = "设备因子id")
    private Long deviceFactorId;
    /**
     * 因子编码
     */
    @ApiModelProperty(value = "因子编码")
    private String factorCode;

    @ApiModelProperty(value = "因子名称")
    private String factorName;

    /**
     * 因子id
     */
    @ApiModelProperty(value = "因子id")
    private Long factorId;
}
