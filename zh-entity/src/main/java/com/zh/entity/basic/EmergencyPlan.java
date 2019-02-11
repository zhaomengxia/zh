package com.zh.entity.basic;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 应急预案表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("emergency_plan")
@ApiModel(value="EmergencyPlan对象", description="应急预案表")
public class EmergencyPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "上传时间")
    @TableField("upload_time")
    private String uploadTime;

    @ApiModelProperty(value = "上传人员")
    @TableField("upload_people")
    private String uploadPeople;

    @TableField("plan_type_id")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long planTypeId;

    @ApiModelProperty(value = "预案类型名称")
    @TableField("plan_type_name")
    private String planTypeName;

    @ApiModelProperty(value = "预案名称")
    @TableField("plan_name")
    private String planName;

    @TableField("area_code")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String areaCode;

    @ApiModelProperty(value = "所属行政区id")
    @TableField(exist = false)
    private Long areaId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @ApiModelProperty(value = "文件路径")
    @TableField("file_path")
    private String filePath;


}
