package com.zh.enums;

import lombok.Getter;

/**
 * websocket地址枚举类
 *
 * @author  赵梦霞
 * @since 2018-11-20 15:42

 **/
@Getter
public enum WebsocketUrlsEnum {

    /**
     * 测试websocket地址
     */
    TEST("/topic/test","测试websocket地址");


    /**
     * websocket 地址
     */
    private String url;

    /**
     * 描述
     */
    private String desc;

    WebsocketUrlsEnum(String url, String desc){
        this.url = url;
        this.desc = desc;
    }
}
