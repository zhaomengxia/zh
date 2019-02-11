package com.zh.dto.sys.site;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/28 9:57
 */
@Data
public class DeviceDTO {
    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id")
    private Long deviceId;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    /**
     * 设备编码
     */
    @ApiModelProperty(value = "设备编码")
    private String deviceCode;

    @ApiModelProperty(value = "设备类型id")
    private Long deviceTypeId;

    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;

    private List<DeviceFactorDTO> deviceFactorDTOS;
}
