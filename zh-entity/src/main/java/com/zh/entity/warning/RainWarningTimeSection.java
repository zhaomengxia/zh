package com.zh.entity.warning;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 降雨预警时间区间
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("rain_warning_time_section")
@ApiModel(value="RainWarningTimeSection对象", description="降雨预警时间区间")
public class RainWarningTimeSection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("time_section")
    private Integer timeSection;

    @ApiModelProperty(value = "时间单位")
    @TableField("time_unit")
    private String timeUnit;


}
