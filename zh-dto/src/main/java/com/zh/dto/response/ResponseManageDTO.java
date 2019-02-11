package com.zh.dto.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/8 9:42
 */
@Data
@ApiModel
public class ResponseManageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预警记录id")
    private Long id;

    @ApiModelProperty(value = "预警名称")
    private String name;

    @ApiModelProperty(value = "预警状态")
    private Long statusValue;
    @ApiModelProperty(value = "预警状态")
    private String status;

    @ApiModelProperty(value = "预警记录名称")
    @Excel(name = "预警名称", width = 20)
    private String warningName;

    @ApiModelProperty(value = "预警等级")
    @Excel(name = "预警等级", width = 20)
    private String warningDegree;

    @ApiModelProperty(value = "预警时间")
    private Long warningTime;

    @Excel(name = "预警时间", width = 20)
    private String warningTimes;

    @ApiModelProperty(value = "预警测站点")
    private Long siteId;

    @ApiModelProperty(value = "预警站点名称")
    private String siteName;

    @ApiModelProperty(value = "响应记录id")
    private Long responseRecordId;

    @ApiModelProperty(value = "响应等级")
    @Excel(name = "响应等级", width = 20)
    private String responseDegree;

    @ApiModelProperty(value = "响应内容")
    @Excel(name = "响应措施", width = 20)
    private String responseContent;

    @ApiModelProperty(value = "响应启动时间")
    private Long responseTime;

    @Excel(name = "响应启动时间", width = 20)
    private String responseTimes;

    @ApiModelProperty(value = "响应状态 1:已启动响应 2:响应反馈中 3：关闭响应  4:灾情统计中 ")
    @Excel(name = "当前状态", width = 20)
    private String responseStatus;

    @ApiModelProperty(value = "响应状态对应的值")
    private Integer responseStatusValue;
    @ApiModelProperty(value = "防汛负责人")
    private String chargeMan;

    @ApiModelProperty(value = "防汛部门")
    private String department;

    @ApiModelProperty(value = "行政区划代码")
    private String areaCode;

    @ApiModelProperty(value = "行政区划名称")
    private String areaName;
    @ApiModelProperty(value = "预警跨步")
    private Integer warnJump;
    @ApiModelProperty(value = "响应跨步")
    private Integer responseJump;

    @ApiModelProperty(value = "是否回复正常")
    private Boolean isNormal;
}
