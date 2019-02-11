package com.zh.dto.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/11 11:22
 */
@Data
@ApiModel
public class ResponseDisaterDTO {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "反馈部门 部门id")
    private Long arganizationId;

    @ApiModelProperty(value = "反馈部门")
    private String arganizationName;

    @ApiModelProperty(value = "反馈人id")
    private Long userId;

    @ApiModelProperty(value = "行政区域编码")
    private String areaCode;

    @ApiModelProperty(value = "反馈人")
    @Excel(name = "反馈人", width = 20)
    private String userName;

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

    @ApiModelProperty(value = "受灾人口")

    @Excel(name = "受灾人口(人)", width = 20)
    private Long affectPeople;

    @ApiModelProperty(value = "倒塌房屋间数")
    @Excel(name = "倒塌房屋(间)", width = 20)
    private Long affectHouse;

    @ApiModelProperty(value = "死亡人口")
    @Excel(name = "死亡人口(人)", width = 20)
    private Long diePeople;

    @ApiModelProperty(value = "失踪人口")
    @Excel(name = "失踪人口(人)", width = 20)
    private Long missPeople;

    @ApiModelProperty(value = "转移人口")
    @Excel(name = "转移人口(人)", width = 20)
    private Long transferPeople;

    @ApiModelProperty(value = "直接经济损失（万元）")
    @Excel(name = "直接经济损失(万元)", width = 20)
    private String economicLosses;

    @ApiModelProperty(value = "响应记录id")
    private Long warnRecordId;

    @ApiModelProperty(value = "是否已反馈")
    private Boolean feedback;
}
