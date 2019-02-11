package com.zh.entity.data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 站点因子分钟数据
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
@TableName("site_factor_data_minute")
@ApiModel(value="SiteFactorDataMinute对象", description="站点因子分钟数据")
public class SiteFactorDataMinute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "泵站id.")
    @TableField("site_id")
    private Long siteId;

    @ApiModelProperty(value = "因子编码")
    @TableField("factor_code")
    private String factorCode;

    @ApiModelProperty(value = "数值")
    @TableField("value")
    private Double value;

    @ApiModelProperty(value = "时间戳")
    @TableField("time")
    private Long time;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
