package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/7 10:45
 */
@Data
public class WholeDTO {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区名称")
    @Excel(name = "行政区名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区代码")
    @Excel(name = "行政区代码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "堤防长度")
    @Excel(name = "堤防长度(米)", width = 20)
    private String dikeLength;

    @ApiModelProperty(value = "水库，塘坝数量")
    @Excel(name = "水库,塘坝数量(座)", width = 20)
    private Long waterCount;

    @ApiModelProperty(value = "路涵数量")
    @Excel(name = "路涵数量(座)", width = 20)
    private Long roadCount;

    @ApiModelProperty(value = "桥梁数量")
    @Excel(name = "桥梁数量(座)", width = 20)
    private Long bridgeCount;

    @ApiModelProperty(value = "泵站数量")
    @Excel(name = "泵站数量(座)", width = 20)
    private Long pumpCount;

}
