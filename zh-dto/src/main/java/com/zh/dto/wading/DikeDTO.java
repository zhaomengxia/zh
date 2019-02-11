package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 16:49
 */
@Data
public class DikeDTO {

    private Long id;

    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区域名称")
    @Excel(name = "行政区域名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区域编码")
    @Excel(name = "行政区域代码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "堤防名称")
    @Excel(name = "堤防名称", width = 20)
    private String name;

    @ApiModelProperty(value = "堤防编码")
    @Excel(name = "堤防代码", width = 20)
    private String code;

    @ApiModelProperty(value = "所在河流(湖泊、海岸)")
    @Excel(name = "所在河流(湖泊、海岸)", width = 20)
    private String river;

    @ApiModelProperty(value = "河流岸别")
    @Excel(name = "河流岸别", width = 20)
    private String riverType;

    @ApiModelProperty(value = "堤坝跨界情况")
    @Excel(name = "堤坝跨界情况", width = 20)
    private String transboundary;

    @ApiModelProperty(value = "堤防类型")
    @Excel(name = "堤防类型", width = 20)
    private String type;

    @ApiModelProperty(value = "堤防形式")
    @Excel(name = "堤防形式", width = 20)
    private String form;

    @ApiModelProperty(value = "堤防级别")
    @Excel(name = "堤防级别", width = 20)
    private String degree;

    @ApiModelProperty(value = "设计防洪(潮)标准［重现期(年)")
    @Excel(name = "设计防洪(潮)标准［重现期(年)", width = 20)
    private String recurrencePeriod;

    @ApiModelProperty(value = "堤防长度")
    @Excel(name = "堤防长度", width = 20)
    private String length;

    @ApiModelProperty(value = "达到规划防洪(潮)标准的长度 (m)")
    @Excel(name = "达到规划防洪(潮)标准的长度 (m)", width = 20)
    private String standardLength;

    @ApiModelProperty(value = "高程系统")
    @Excel(name = "高程系统", width = 20)
    private String altitudeSystem;

    @ApiModelProperty(value = "设计水（高潮）位 (m)")
    @Excel(name = "设计水（高潮）位 (m)", width = 20)
    private String designLevel;

    @ApiModelProperty(value = "堤防高度：（最大值） (m)")
    @Excel(name = "堤防高度：（最大值） (m)", width = 20)
    private String heightMax;

    @ApiModelProperty(value = "堤防高度：（最小值） (m)")
    @Excel(name = "堤防高度：（最小值） (m)", width = 20)
    private String heightMin;

    @ApiModelProperty(value = "堤顶宽度：（最大值） (m)")
    @Excel(name = "堤顶宽度：（最大值） (m)", width = 20)
    private String widthMax;

    @ApiModelProperty(value = "堤顶宽度：（最小值） (m)")
    @Excel(name = "堤顶宽度：（最小值） (m)", width = 20)
    private String widthMin;

    @ApiModelProperty(value = "工程任务")
    @Excel(name = "工程任务", width = 20)
    private String engineeringTask;

    @ApiModelProperty(value = "坝顶起点高程")
    @Excel(name = "堤顶高程起点高程 (m)", width = 20)
    private String startAltitude;

    @ApiModelProperty(value = "坝顶终点高程")
    @Excel(name = "堤顶高程终点高程 (m)", width = 20)
    private String endAltitude;

    @ApiModelProperty(value = "描述")
    @Excel(name = "描述", width = 20)
    private String description;

}
