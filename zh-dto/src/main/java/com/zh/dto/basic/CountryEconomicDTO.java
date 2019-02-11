package com.zh.dto.basic;

import com.zh.entity.basic.CountryEconomic;
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
public class CountryEconomicDTO implements Serializable {

    private Integer type;

    private String typeName;

    private List<CountryEconomic> countryEconomics;
}
