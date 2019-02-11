package com.zh.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/14 17:25
 */
@Data
@ApiModel
public class ResponseDepartmentIdDTO {
    private Long id;
    private String departmentName;
}
