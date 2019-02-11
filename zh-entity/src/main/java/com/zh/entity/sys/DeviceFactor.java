package com.zh.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 设备因子表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("device_factor")
@ApiModel(value = "DeviceFactor对象", description = "设备因子表")
public class DeviceFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "泵站id.")
    @TableField("device_id")
    private Long deviceId;

    @ApiModelProperty(value = "因子id")
    @TableField("factor_id")
    private Long factorId;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @ApiModelProperty(value = "因子编码")
    @TableField("factor_code")
    private String factorCode;


}
