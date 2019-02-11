package com.zh.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@ApiModel
public class RainListDTO {

    @ApiModelProperty(value = "站点主键")
    private Long id;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "时间戳")
    private Long time;

    @ApiModelProperty(value = "因子code",hidden = true)
    @JsonIgnore
    private String factorCode;

    @ApiModelProperty(value = "累计降雨量")
    private Double value;

}
