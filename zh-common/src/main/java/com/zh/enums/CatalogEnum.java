package com.zh.enums;

import lombok.Getter;

/**
 * 目录枚举类
 *
 * @author  赵梦霞
 * @date 2018-08-22 10:03

 **/
@Getter
public enum CatalogEnum {

    /**
     * 图片
     */
    IMAGE("image"),

    /**
     * 视频文件
     */
    MEDIA("media"),

    /**
     * 办公相关文件
     */
    OFFICE("office"),

    /**
     * 其它文件类型
     */
    FILE("file")
    ;


    private String type;

    CatalogEnum(String type){
        this.type = type;
    }

}
