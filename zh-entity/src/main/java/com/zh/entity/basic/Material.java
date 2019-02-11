package com.zh.entity.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 物资表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("material")
@ApiModel(value = "Material对象", description = "物资表")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "物资名称/种类")
    @TableField("material_name")
    @Excel(name = "物资种类", width = 20)
    private String materialName;

    @ApiModelProperty(value = "数量")
    @TableField("material_mount")
    @Excel(name = "数量", width = 20)
    private String materialMount;

    @ApiModelProperty(value = "单位")
    @TableField("unit")
    @Excel(name = "单位", width = 20)
    private String unit;

    @ApiModelProperty(value = "说明")
    @TableField("description")
    @Excel(name = "说明", width = 20)
    private String description;

    @ApiModelProperty(value = "仓库id")
    @TableField("warehourse_id")
    private Long warehourseId;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

}
