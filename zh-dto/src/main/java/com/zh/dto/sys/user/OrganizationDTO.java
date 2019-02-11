package com.zh.dto.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织数据传输类
 *
 * @author  赵梦霞
 * @since 2018-12-21 17:39

 **/
@Data
@ApiModel
public class OrganizationDTO {

    @ApiModelProperty(value = "部门表主键")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String divisionName;

    @ApiModelProperty(value = "职务")
    private String position;

    @ApiModelProperty(value = "是否负责人")
    private Boolean charge;

}
