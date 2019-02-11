package com.zh.entity.warning;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 因子实时数据
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("factor_real_data")
@ApiModel(value="FactorRealData对象", description="因子实时数据")
public class FactorRealData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "因子编码.")
    @TableField("factor_code")
    private String factorCode;

    @ApiModelProperty(value = "站点id.")
    @TableField("site_id")
    private Long siteId;

    @ApiModelProperty(value = "时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty(value = "数值")
    @TableField("value")
    private Double value;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
