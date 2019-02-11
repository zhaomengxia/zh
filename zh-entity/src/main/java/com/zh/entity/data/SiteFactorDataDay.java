package com.zh.entity.data;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 站点因子天数据
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("site_factor_data_day")
@ApiModel(value = "SiteFactorDataDay对象", description = "站点因子天数据")
public class SiteFactorDataDay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "泵站id.")
    @TableField("site_id")
    private Long siteId;

    @ApiModelProperty(value = "因子编码.")
    @TableField("factor_code")
    private String factorCode;

    @ApiModelProperty(value = "数值")
    @TableField("value")
    private Double value;

    @ApiModelProperty(value = "时间.")
    @TableField("time")
    private Long time;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
