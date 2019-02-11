package com.zh.enums;

/**
 * @author  hahaha
 * @date 2018-08-23 17:16

 **/
public interface IEnum {

    /**
     * 错误编码 0、失败 1、正常
     */
    Integer getCode();

    /**
     * 错误描述
     */
    String getMessage();

}
