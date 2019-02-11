package com.zh.enums;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author hahaha
 * @description:
 */

public enum CountryEconomicEnum {

    /**
     * 县(市、区)社会经济
     */
    BASIC_INFO(1, "基本情况"),
    COMPREHENSIVE_ECONOMIC(2, "综合经济"),
    AGRICULTURE_INDUSTRY_INVEST(3, "农业、工业及投资"),
    EDUCATION_HEALTH_SOCIETY(4, "教育、卫生和社会保障")
    ;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer type;

    private String name;



    CountryEconomicEnum(Integer type, String name) {
        this.name = name;
        this.type = type;
    }

    public static String getName(Integer type){
        String name = null;
        switch (type){
            case 1:
                name = BASIC_INFO.name;
                break;
            case 2:
                name = COMPREHENSIVE_ECONOMIC.name;
                break;
            case 3:
                name = AGRICULTURE_INDUSTRY_INVEST.name;
                break;
            default:
                name = EDUCATION_HEALTH_SOCIETY.name;
                break;
        }
        return name;
    }



}
