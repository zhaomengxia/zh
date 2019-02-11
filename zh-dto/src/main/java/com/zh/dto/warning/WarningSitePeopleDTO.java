package com.zh.dto.warning;

import com.zh.dto.basic.KeyValueDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class WarningSitePeopleDTO implements Serializable {

    /**
     * 防汛部门列表.
     */
    @ApiModelProperty(value = "防汛部门列表.")
    private List<DepartmentUserDTO> departmentList;

    /**
     * 防汛负责人列表.
     */
    @ApiModelProperty(value = "防汛负责人列表")
    private List<DepartmentUserDTO> chargeManList;
}
