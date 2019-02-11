package com.zh.enums;

import lombok.Getter;

/**
 * 水情状态枚举类
 *
 * @author  hahaha
 * @date 2019-01-25 9:35

 **/
@Getter
public enum DataStatusEnum {

    /**
     *
     */
    LOW(0, "下降"),
    FLAT(1, "持平"),
    UP(2, "上升");

    private Integer status;

    private String desc;

    DataStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
