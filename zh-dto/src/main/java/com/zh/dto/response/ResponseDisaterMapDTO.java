package com.zh.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/12 10:18
 */
@Data
@ApiModel
public class ResponseDisaterMapDTO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    @ApiModelProperty(value = "响应状态值 ")
    private Integer responseStatusValue;
    @ApiModelProperty(value = "响应状态")
    private String responseStatus;
    @ApiModelProperty(value = "预警状态值")
    private Integer statusValue;
    @ApiModelProperty(value = "响应等级值")
    private Integer responseDegreeValue;
    @ApiModelProperty(value = "响应等级")
    private String responseDegree;
    @ApiModelProperty(value = "响应记录id")
    private Long warnRecordId;
    @ApiModelProperty(value = "跨步标志")
    private Integer responseJump;
    @ApiModelProperty(value = "预警id")
    private Long warnId;

    @ApiModelProperty(value = "预警跳步标志")
    private Integer warningJump;

    @ApiModelProperty(value = "响应是否显示关闭")
    private Boolean normal;

    @ApiModelProperty(value = "预警名称")
    private String warnName;

    @ApiModelProperty(value = "预警状态")
    private String status;

    @ApiModelProperty(value = "预警 发布内容")
    private String warningContent;

    @ApiModelProperty(value = "预警等级")
    private String warningDegreeName;
    @ApiModelProperty(value = "预警等级值")
    private Long warningDegreeValue;
    @ApiModelProperty(value = "预警图标路径")
    private String icon;
    @ApiModelProperty(value = "预警时间")
    private Long warningTime;

    @ApiModelProperty(value = "正常值")
    private String normalValue;

    @ApiModelProperty(value = "报警值")
    private String alarmValue;

    @ApiModelProperty(value = "行政区域编码")
    private String areaCode;

    @ApiModelProperty(value = "行政区域名称")
    private String areaName;

    @ApiModelProperty(value = "相关站点id")
    private Long siteId;

    @ApiModelProperty(value = "相关站点名称")
    private String siteName;

    @ApiModelProperty(value = "相关站点编码")
    private String siteCode;

    @ApiModelProperty(value = "建设单位")
    private String constractionCompany;

    @ApiModelProperty(value = "管理单位")
    private String manageCompany;

    @ApiModelProperty(value = "通讯方式")
    private String telephone;

    @ApiModelProperty(value = "站点地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "泵站实景图地址")
    private String photo;

    @ApiModelProperty(value = "历史最高水位")
    private Double recordLevel;

    @ApiModelProperty(value = "历史最高水位出现时间")
    private LocalDateTime recordTime;

    @ApiModelProperty("是否重点")
    private Boolean isFocus;

    private List<ResponseDisaterDTO> responseDisaterDTOS;

}
