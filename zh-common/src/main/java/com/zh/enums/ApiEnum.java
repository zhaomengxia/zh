package com.zh.enums;

import lombok.Getter;

/**
 * Api枚举类
 *
 * @author  hahaha
 * @date 2018-08-24 9:31

 **/
@Getter
public enum ApiEnum implements IEnum {

    /**
     * 成功
     */
    SUCCESS(1,"成功"),
    /**
     * 失败
     */
    FAIL(0,"失败")
    ;

    private Integer code;

    private String message;

    ApiEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
