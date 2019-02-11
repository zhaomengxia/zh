package com.zh.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/14 17:26
 */
@Data
@ApiModel
public class ResponseChargemanIdDTO {
    private Long id;
    private String name;
}
