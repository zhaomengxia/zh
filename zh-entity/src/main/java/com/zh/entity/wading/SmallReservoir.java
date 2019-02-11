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
 * 塘坝
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("small_reservoir")
@ApiModel(value="SmallReservoir对象", description="塘坝")
public class SmallReservoir implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "塘坝编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "容量")
    @TableField("capacity")
    private String capacity;

    @ApiModelProperty(value = "坝高")
    @TableField("height")
    private String height;

    @TableField("length")
    private String length;

    @ApiModelProperty(value = "挡水坝主要类型")
    @TableField("type")
    private String type;

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
