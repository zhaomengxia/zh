package com.zh.dto.sys.site;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/22 16:48
 */
@Data
@ApiModel
public class SiteDTO {
    /**
     * 站点id
     */
    @ApiModelProperty(value = "站点id")
    private Long id;
    /**
     * 站点名称
     */
    @ApiModelProperty("站点名称")
    private String name;
    /**
     * 泵站编码
     */
    @ApiModelProperty("泵站编码")
    private String code;
    /**
     * 建设单位
     */
    @ApiModelProperty("建设单位")
    private String constractionCompany;
    /**
     * 管理单位
     */
    @ApiModelProperty("管理单位")
    private String manageCompany;
    /**
     * 通讯方式
     */
    @ApiModelProperty("通讯方式")
    private String telephone;
    /**
     * 所属行政区
     */
    @ApiModelProperty("所属行政区的编号")
    private String areaCode;

    @ApiModelProperty("所属行政区的名称")
    private String areaName;
    /**
     * 站点地址
     */
    @ApiModelProperty("站点地址")
    private String address;
    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;
    /**
     * 泵站实景图地址
     */
    @ApiModelProperty("泵站实景图地址")
    private String photo;

    @ApiModelProperty("是否重点")
    private Boolean isFocus;

    @ApiModelProperty(value = "历史最高水位")
    private Double recordLevel;

    @ApiModelProperty(value = "历史最高水位出现时间")
    private LocalDateTime recordTime;

    private List<DeviceDTO> deviceDTOS;

}
