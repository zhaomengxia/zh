package com.zh.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 水位数据查询结果封装
 *
 * @author  赵梦霞
 * @since 2019-01-11 14:14

 **/
@Data
public class WaterResultListDTO {

    @ApiModelProperty(value = "站点主键")
    private Long id;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "水位数值")
    private Double value;

    @ApiModelProperty(value = "因子主键，用于判断上游或者下游")
    private Long factorId;

    @ApiModelProperty(value = "因子code",hidden = true)
    @JsonIgnore
    private String factorCode;

    @ApiModelProperty(value = "时间戳")
    private Long time;

    @ApiModelProperty(value = "历史最高水位")
    private Double historyLevel;

    @ApiModelProperty(value = "历史最高水位发生时间")
    private LocalDateTime historyLevelTime;
}
