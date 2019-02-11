package com.zh.dto.log.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作日志查询条件封装类
 *
 * @author  赵梦霞
 * @since 2018-12-18 16:49

 **/
@Data
@ApiModel
public class OperateLogQuery {

    @ApiModelProperty(value = "人员姓名,模糊匹配")
    private String operator;

    @ApiModelProperty(value = "人员角色")
    private String operateRole;

    @ApiModelProperty(value = "开始时间，时间戳")
    private Long start;

    @ApiModelProperty(value = "结束时间，时间戳")
    private Long end;

}
