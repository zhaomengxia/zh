package com.zh.dto.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/7 11:41
 */
@Data
public class IdAndNameDTO {
    /**
     * 人员表 id
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 人员名称
     */
    @ApiModelProperty(value = "人员名称")
    private String name;

}
