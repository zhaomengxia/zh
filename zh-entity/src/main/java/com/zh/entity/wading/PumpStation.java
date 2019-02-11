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
 * 泵站
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
@TableName("pump_station")
@ApiModel(value="PumpStation对象", description="泵站")
public class PumpStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;


    @ApiModelProperty(value = "行政区划编码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "泵站名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "泵站编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "泵站类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "装机功率(kw)")
    @TableField("power")
    private String power;

    @ApiModelProperty(value = "装机流量")
    @TableField("flow")
    private String flow;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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
