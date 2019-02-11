package com.zh.entity.response;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 响应行动
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("response_action")
@ApiModel(value = "ResponseAction对象", description = "响应行动")
public class ResponseAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行动名称")
    @TableField("action_name")
    private String actionName;

    @ApiModelProperty(value = "所属行政区代码")
    @TableField("area_code")
    private String areaCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属行政区代码")
    private String areaName;
    @ApiModelProperty(value = "上传人员")
    @TableField("people")
    private String people;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "一级响应行动文件路径")
    @TableField("first_path")
    private String firstPath;

    @ApiModelProperty(value = "二级响应行动文件路径")
    @TableField("second_path")
    private String secondPath;
    @ApiModelProperty(value = "三级响应行动文件路径")
    @TableField("third_path")
    private String thirdPath;
    @ApiModelProperty(value = "四级响应行动文件路径")
    @TableField("fourth_path")
    private String fourthPath;

    @ApiModelProperty(value = "上传时间")
    @TableField("upload_time")
    private Long uploadTime;
}
