package com.zh.dto.data;

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
public class NewWarningDataDTO implements Serializable {

    /**
     * 水位数据.
     */
    private List<WaterListDTO> waterListDTOS;

    /**
     * 雨情数据.
     */
    private List<RainListDTO> rainListDTOS;

    public NewWarningDataDTO(List<WaterListDTO> waterListDTOS, List<RainListDTO> rainListDTOS) {
        this.waterListDTOS = waterListDTOS;
        this.rainListDTOS = rainListDTOS;
    }
}
