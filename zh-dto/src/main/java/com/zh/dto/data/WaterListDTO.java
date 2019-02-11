package com.zh.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 列表展示数据传输
 *
 * @author  赵梦霞
 * @since 2019-01-04 15:59

 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel
public class WaterListDTO {

    @ApiModelProperty(value = "站点主键")
    private Long id;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "上游水位")
    private Double upperStream;

    @ApiModelProperty(value = "下游水位")
    private Double downStream;

    @ApiModelProperty(value = "上游水位状态")
    private Integer upperStreamStatus;

    @ApiModelProperty(value = "下游水位状态")
    private Integer downStreamStatus;

    @ApiModelProperty(value = "时间戳")
    private Long time;

    @ApiModelProperty(value = "历史最高水位")
    private Double historyLevel;

    @ApiModelProperty(value = "历史最高水位发生时间")
    private LocalDateTime historyLevelTime;


}
