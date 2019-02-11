package com.zh.entity.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * 
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("country_economic")
@ApiModel(value="CountryEconomic对象", description="")
public class CountryEconomic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField(exist = false)
    @Excel(name = "序号", width = 20)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer serialNumber;

    @TableField("index_one")
    @Excel(name = "指标", width = 20)
    private String indexOne;

    @TableField("unit")
    @Excel(name = "单位", width = 20)
    private String unit;

    @TableField("number")
    @Excel(name = "数量", width = 20)
    private Double number;

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

    @ApiModelProperty(value = "指标类型,1:基本情况 2:综合经济 3:农业、工业及投资")
    @TableField("type")
    private Integer type;


}
