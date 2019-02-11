package com.zh.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/14 13:40
 */
@Data
@ApiModel
public class ResponseMapDTO {
    private List<ResponseStartMapDTO> responseStartMapDTOS;
    private List<ResponseFeedbackMapDTO> responseFeedbackMapDTOS;
    private List<ResponseDisaterMapDTO> responseDisaterMapDTOS;
}
