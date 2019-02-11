package com.zh.entity.wading;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 水库
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("water")
@ApiModel(value="Water对象", description="水库")
public class Water implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "水库名称")
    @TableField("water_name")
    private String waterName;

    @ApiModelProperty(value = "水库代码")
    @TableField("water_code")
    private String waterCode;

    @ApiModelProperty(value = "水库容量")
    @TableField("amount")
    private String amount;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField("update_time")
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;


}
