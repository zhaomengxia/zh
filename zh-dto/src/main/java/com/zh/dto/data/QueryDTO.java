package com.zh.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 雨量查询条件封装
 *
 * @author  赵梦霞
 * @since 2019-01-04 13:56

 **/
@Data
@ApiModel
public class QueryDTO {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "开始时间戳")
    private Long start;

    @ApiModelProperty(value = "结束时间戳")
    private Long end;

    @ApiModelProperty(value = "时刻")
    private Long time;

}
