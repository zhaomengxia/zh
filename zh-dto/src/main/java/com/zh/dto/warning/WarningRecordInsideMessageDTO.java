package com.zh.dto.warning;

import com.zh.entity.warning.WarningRecordInsideMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/13 12:49
 */
@Data
@ApiModel
public class WarningRecordInsideMessageDTO {
    /**
     * 预警记录id.
     */
    @ApiModelProperty(value = "预警记录id")
    private Long warnRecordId;

    /**
     * 预警名称.
     */
    @ApiModelProperty(value = "预警名称")
    private String warnRecordName;
    /**
     * 预警状态名称.
     */
    @ApiModelProperty(value = "预警状态名称")
    private String warningStatus;
    /**
     * 预警等级名称.
     */
    @ApiModelProperty(value = "预警等级名称")
    private String warningDegreeName;
    /**
     * 预警时间.
     */
    @ApiModelProperty(value = "预警时间")
    private Long warningTime;

    @ApiModelProperty(value = "预警通知")
    private String warningContent;


    @ApiModelProperty(value = "发布人")
    private String publishPerson;

    @ApiModelProperty(value = "发布内容")
    private String publishContent;


    @ApiModelProperty(value = "部门")
    private String department;
    @ApiModelProperty(value = "责任人")
    private String chargeMan;

    @ApiModelProperty(value = "部门成员信息")
    private List<DepartmentUserDTO> departmentUserDTOS;

    @ApiModelProperty(value = "防汛责任人信息")
    private List<FloodDutyOwnerUserDTO> floodDutyOwnerUserDTOS;
    private List<WarningRecordInsideMessage> warningRecordInsideMessages;
}
