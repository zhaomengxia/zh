package com.zh.dto.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/11 12:53
 */
@Data
@ApiModel
public class ResponseFeedbackDTO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "响应记录id")
    private Long warnRecordId;

    @ApiModelProperty(value = "反馈部门 部门id")
    private Long arganizationId;

    @ApiModelProperty(value = "反馈部门")
    private String arganizationName;

    @ApiModelProperty(value = "反馈人id")
    private Long userId;

    @ApiModelProperty(value = "反馈人")
    @Excel(name = "反馈人", width = 20)
    private String userName;


    @ApiModelProperty(value = "行政区域编码")
    private String areaCode;

    @ApiModelProperty(value = "行政区域名称")
    @Excel(name = "行政区划", width = 20)
    private String areaName;


    @ApiModelProperty(value = "统计开始时间")
    private Long startTime;
    @Excel(name = "统计开始日期", width = 20)
    private String startTimes;
    @ApiModelProperty(value = "统计结束时间")
    private Long endTime;
    @Excel(name = "统计结束日期", width = 20)
    private String endTimes;
    @ApiModelProperty(value = "反馈时间")
    private Long backTime;
    @Excel(name = "反馈时间", width = 20)
    private String backTimes;
    @ApiModelProperty(value = "投入抢险人员")
    @Excel(name = "投入抢险人员", width = 20)
    private String rescuePeople;

    @ApiModelProperty(value = "需转移群众")
    @Excel(name = "需转移群众", width = 20)
    private String needTransferPeople;

    @ApiModelProperty(value = "已转移群众")
    @Excel(name = "已转移群众", width = 20)
    private String transferredPeople;

    @ApiModelProperty(value = "受围困群众")
    @Excel(name = "受围困群众", width = 20)
    private String besiegedPeople;

    @ApiModelProperty(value = "已转移围困群众")
    @Excel(name = "已转移围困群众", width = 20)
    private String notBesiegedPeople;

    @ApiModelProperty(value = "是否已反馈")
    private Boolean feedback;
}
