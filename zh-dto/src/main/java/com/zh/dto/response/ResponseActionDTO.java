package com.zh.dto.response;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.dto.basic.KeyValueDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class ResponseActionDTO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "行动名称")
    private String actionName;

    @ApiModelProperty(value = "所属行政区代码")
    private String areaCode;

    @ApiModelProperty(value = "所属行政区代码")
    private String areaName;

    @ApiModelProperty(value = "上传人员")
    private String people;

    @ApiModelProperty(value = "一级响应行动文件路径")
    @TableField("first_path")
    private String firstPath;

    @ApiModelProperty(value = "二级响应行动文件路径")
    private String secondPath;
    @ApiModelProperty(value = "三级响应行动文件路径")
    private String thirdPath;
    @ApiModelProperty(value = "四级响应行动文件路径")
    private String fourthPath;

    @ApiModelProperty(value = "上传时间")
    private Long uploadTime;

    @ApiModelProperty(value = "响应文件")
    List<KeyValueDTO> keyValueDTOS;
}
