package com.zh.entity.flood;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 防汛责任人表
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
@TableName("flood_prevention_owner")
@ApiModel(value="FloodPreventionOwner对象", description="防汛责任人表")
public class FloodPreventionOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "行政区代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "单位")
    @TableField("company")
    private String company;

    @ApiModelProperty(value = "姓名  用户表id")
    @TableField("user_id")
    private Long userId;


    @ApiModelProperty(value = "职务")
    @TableField("work")
    private String work;

    @ApiModelProperty(value = "固定电话")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "备注")
    @TableField("mark")
    private String mark;

    @ApiModelProperty(value = "责任制类型Id")
    @TableField("type_id")
    private Long typeId;

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


}
